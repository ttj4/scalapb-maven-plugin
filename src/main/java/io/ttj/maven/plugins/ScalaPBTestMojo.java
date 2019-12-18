package io.ttj.maven.plugins;

import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mojo(name = "testCompile")
public class ScalaPBTestMojo extends AbstractScalaPBMojo {

    /**
     * Input directory containing *.proto files for test case.
     * Defaults to <code>${project.basedir}/src/test/proto</code>.
     *
     * @parameter property="inputDirectoryTest"
     */
    @Parameter(defaultValue = "${project.basedir}/src/test/proto")
    private File inputDirectoryTest;

    /**
     * Output directory for Scala generated test classes.
     * Defaults to <code>${project.build.directory}/generated-test-sources/protobuf</code>.
     *
     * @parameter property="outputDirectoryTest"
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-test-sources/protobuf")
    private File outputDirectoryTest;

    /**
     * Output directory for Java generated test classes.
     * Defaults to <code>${project.build.directory}/generated-test-sources/protobuf</code>.
     *
     * @parameter property="javaOutputDirectoryTest"
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-test-sources/protobuf")
    private File javaOutputDirectoryTest;

    @Override
    protected Path getInputDirectory() {
        return Paths.get(inputDirectoryTest.toURI());
    }

    @Override
    protected Path getOutputDirectory() {
        return  Paths.get(outputDirectoryTest.toURI());
    }

    @Override
    protected Path getJavaOutputDirectory() {
        return Paths.get(javaOutputDirectoryTest.toURI());
    }

    @Override
    protected String getInputDirectoryAbsPath() {
        return inputDirectoryTest.getAbsolutePath();
    }
}
