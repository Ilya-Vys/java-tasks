package com.example.lec09.task2;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ClassBuilder {

    private final String path;
    private final String className;
    private List<String> code;

    public ClassBuilder(String path, String className,List<String> code) {
        this.path = path;
        this.className = className;
        this.code = code;
    }

    public Worker buildInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        createJavaFile();
        compileJavaFile();
        return (Worker) new CustomClassLoader(getCorrectNameToClassloader())
                .findClass(path + className).newInstance();
    }

    private void createJavaFile() {
        try {
            Files.write(Paths.get(String.format("%s%s.java", path, className)), code);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void compileJavaFile(){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, String.format("%s%s.java", path, className));
    }

    private String getCorrectNameToClassloader(){
        return code.get(0).substring(8).replace(";", ".").concat(className);
    }
}
