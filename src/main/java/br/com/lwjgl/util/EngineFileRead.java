package br.com.lwjgl.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;

@Slf4j
@NoArgsConstructor (access = AccessLevel.PRIVATE)
public class EngineFileRead {
    private static final String PATH = "src/main/resources/";
    private static StringBuilder data;

    public static final String read (final String source) {
        try (BufferedReader  reader = new BufferedReader(new FileReader(PATH + source))) {
            data = new StringBuilder();
            reader.lines().forEach(line -> data.append(line).append("\n"));
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return data.toString();
    }
}