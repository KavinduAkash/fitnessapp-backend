package com.sliit.fitnessbackend.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {

    private static String uploadPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\";
    private static String dirProfilePic = "uploads\\";

    public static String uploadProfilePic(MultipartFile file) throws IOException {
        try {
            // Get the file bytes
            byte[] bytes = file.getBytes();
            // Ensure the directory exists, if not, create it
            File uploadDir = new File(uploadPath + dirProfilePic);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            // Create a unique file name to avoid naming conflicts
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            // Set the file path including the file name
            String filePath = uploadPath + dirProfilePic + fileName;
            // Create the file
            File imageFile = new File(filePath);
            // Write the file bytes to the created file
            Files.write(imageFile.toPath(), bytes);
            return fileName;
        } catch (IOException e) {
            throw e;
        }
    }

}
