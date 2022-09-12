package br.com.lwjgl.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class EngineFileRead {
    private static final String PATH = "src/main/resources/";
    private static String data;

    public static final String read (final String source) {
        try {
            data = new String(Files.readAllBytes(Path.of(PATH + source)));
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return data;
    }
}