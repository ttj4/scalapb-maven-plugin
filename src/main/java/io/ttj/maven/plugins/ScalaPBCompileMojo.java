package io.ttj.maven.plugins;

import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mojo(name = "compile")
public class ScalaPBCompileMojo extends AbstractScalaPBMojo {

    /**
     * Input directory containing *.proto files.
     * Defaults to <code>${project.basedir}/src/main/proto</code>.
     *
     * @parameter property="inputDirectory"
     */
    @Parameter(defaultValue = "${project.basedir}/src/main/proto")
    private File inputDirectory;

    /**
     * Output directory for Scala generated classes.
     * Defaults to <code>${project.build.directory}/generated-sources/protobuf</code>.
     *
     * @parameter property="outputDirectory"
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources/protobuf")
    private File outputDirectory;

    /**
     * Output directory for Java generated test classes.
     * Defaults to <code>${project.build.directory}/generated-sources/protobuf</code>.
     *
     * @parameter property="javaOutputDirectory"
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources/protobuf")
    private File javaOutputDirectory;

    @Override
    protected Path getInputDirectory() {
        return Paths.get(inputDirectory.toURI());
    }

    @Override
    protected Path getOutputDirectory() {
        return  Paths.get(outputDirectory.toURI());
    }

    @Override
    protected Path getJavaOutputDirectory() {
        return Paths.get(javaOutputDirectory.toURI());
    }

    @Override
    protected String getInputDirectoryAbsPath() {
        return inputDirectory.getAbsolutePath();
    }
}
