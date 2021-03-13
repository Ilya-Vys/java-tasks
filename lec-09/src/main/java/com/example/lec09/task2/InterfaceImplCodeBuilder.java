package com.example.lec09.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class InterfaceImplCodeBuilder {

    private final String path;
    private final String className;
    private List<String> method;

    public InterfaceImplCodeBuilder(String path, String className) {
        this.path = path;
        this.className = className;
    }

    public List<String> getNewClassCode() throws IOException {
        readCode();
        createNewClassCode();
        return method;
    }

    private void readCode() throws IOException {
        method = Files.readAllLines(new File(path).toPath());
        method.removeIf(item -> item == null || "".equals(item));
    }

    private void createNewClassCode(){
        String head = method.get(1);
        String changedHead = head.replace("interface", String.format("class %s implements", className));
        method.set(1, changedHead);
        String methodSignature = method.get(2);
        String changedMethod = "    public " + methodSignature.replace(
                ";", "{System.out.println(\"Work has done!\");}").trim();

        method.set(2, changedMethod);
    }
}
