package com.vnq.booknetwork.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileUtils {
    public static byte[] readFileFromLocation(String fileUrl) {
        // Implement the logic to read the file from the given path
        // and return its content as byte[].
        if(StringUtils.isBlank(fileUrl)) {
            return null;
        }
        try{
            Path filePath = new File(fileUrl).toPath();
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            log.warn("Failed to read file from location: {}", e.getMessage());
        }

        return new byte[0];
    }

    public static String getFileExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            return null;
        }
        int lastIndexOfDot = originalFilename.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return null; // No extension found
        }
        return originalFilename.substring(lastIndexOfDot + 1);
    }
}
