# scalapb-maven-plugin
A simple maven plugin for scalapb


## Sample usage :

            <plugin>
                <groupId>io.github.ttj4</groupId>
                <artifactId>scalapb-maven-plugin</artifactId>
                <version>1.0.2</version>
                <configuration>
                    <includeDirectories>/usr/local/include</includeDirectories>
                    <grpc>true</grpc>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>testCompile</goal>
                        </goals>
                        <phase>generate-sources</phase>
                    </execution>
                </executions>
            </plugin>

## Supported parameters are :
    - protocVersion
    - includeDirectories
    - outputDirectory
    - javaOutputDirectory
    - addProtoSources
    - flatPackage
    - grpc
    - javaOutput
    - javaConversions
    
