package com.vnq.booknetwork.file;

import com.vnq.booknetwork.book.Book;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageService {
    @Value("${appication.security.file.upload.photo-output-path}")
    private String fileUploadPath;
    public String saveFile(@NonNull MultipartFile file,
                           @NonNull Book book,
                           @NonNull Integer userId) {
        final String fileUploadSubPath = "users" + File.separator + userId;
        return uploadFile(file, fileUploadSubPath);
    }

    private String uploadFile(@NonNull MultipartFile file,
                              @NonNull String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + File.separator + fileUploadSubPath;
        File targetDir = new File(finalUploadPath);
        if (!targetDir.exists()) {
            boolean folderCreated = targetDir.mkdirs();
            if (!folderCreated) {
                log.warn("Failed to create directory: {}", targetDir.getAbsolutePath());
                return null;
            }
        }

        final String fileExtension = getFileExtension(file.getOriginalFilename());
        String targetFilePath =  finalUploadPath + File.separator + System.currentTimeMillis() + "." + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, file.getBytes());
            log.info("File saved successfully: {}", targetFilePath);
            return targetFilePath;
        } catch (IOException e) {
            log.error("Failed to save file: {}", e.getMessage());
            return null;
        }
    }

    private String getFileExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            return null;
        }
        // something like "abc.txt" => ".txt"
        int lastIndexOfDot = originalFilename.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return null; // No extension found
        }
        return originalFilename.substring(lastIndexOfDot + 1).toLowerCase();
    }
}
