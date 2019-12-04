package io.ttj.maven.plugins

import java.io.File
import java.nio.file.{Path, Paths}
import java.util.StringJoiner

import scalapb.ScalaPBC

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

object ProtoCompiler {

  private def getAllProtoFiles(path : Path) : Array[Path] = {
    val file : File = new File(path.toUri)

    if(!file.exists()) {
      throw new IllegalArgumentException(s"inputDirectoryPath ($path) does not exist")
    }

    listPaths(file, ".*\\.proto".r)
  }

  private def listPaths(file: File, regex: Regex) : Array[Path] = {
    val list = file.listFiles
    val files = list.filter(name => regex.findFirstIn(name.getName).isDefined)
      .map(protoFile => Paths.get(protoFile.toURI))

    files ++ list.filter(_.isDirectory).flatMap(listPaths(_, regex))
  }

  def compile(
             protocVersion: String,
             inputDirectoryPath: Path,
             includeDirectoriesPaths: Array[Path],
             outputDirectoryPath: Path,
             flatPackage: Boolean,
             grpc: Boolean,
             javaConversions: Boolean,
             javaOutput: Boolean,
             javaOutputPath: Path): Unit = {

    val files: Array[Path] = getAllProtoFiles(inputDirectoryPath)

    if (files.isEmpty) {
      throw new IllegalArgumentException(s"$inputDirectoryPath does not contain .proto files")
    }

    val protoPathsArgs: Array[String] = (inputDirectoryPath +: includeDirectoriesPaths).map {
      path => "--proto_path=" + path
    }

    val scalaOutOptionsJoiner = new StringJoiner(",")
    if (flatPackage) {
      scalaOutOptionsJoiner.add("flat_package")
    }
    if (grpc) {
      scalaOutOptionsJoiner.add("grpc")
    }
    if (javaOutput && javaConversions) {
      scalaOutOptionsJoiner.add("java_conversions")
    }

    val scalaOutOptions = scalaOutOptionsJoiner.toString

    val scalaOutArgs = s"$scalaOutOptions:$outputDirectoryPath"

    val javaArgs = if (javaOutput) {
      Array(s"--java_out=$javaOutputPath")
    } else {
      Array.empty[String]
    }

    val argsBuilder = new ArrayBuffer[String]()
    argsBuilder.append(s"-$protocVersion")
    argsBuilder.append("--throw")
    argsBuilder.append(protoPathsArgs: _*)
    argsBuilder.append(javaArgs: _*)
    argsBuilder.append(s"--scala_out=$scalaOutArgs")
    argsBuilder.append(files.map(_.toString): _*)

    val args = argsBuilder.toArray

    ScalaPBC.main(args)
  }



}
