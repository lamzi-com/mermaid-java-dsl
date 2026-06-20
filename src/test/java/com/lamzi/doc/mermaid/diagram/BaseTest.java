package com.lamzi.doc.mermaid.diagram;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class BaseTest {

    protected String read(String fileName) {
        try {
            File file = new File(BaseTest.class.getResource(fileName).getFile());
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
