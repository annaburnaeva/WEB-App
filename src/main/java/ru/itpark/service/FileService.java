package ru.itpark.service;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileService {
    private final String uploadPath;

    public FileService() throws IOException {
        // FIXME: добавил только для тестирования
        if (System.getenv("UPLOAD_PATH") != null) {
            uploadPath = System.getenv("UPLOAD_PATH");
            Files.createDirectories(Paths.get(uploadPath));
        } else {
            uploadPath = Files.createTempDirectory("upload").toString();
        }
    }
    public void readFile(String id, ServletOutputStream os) throws IOException {
        var path = Paths.get(uploadPath).resolve(id);
        Files.copy(path, os);
    }

    public  String writeFile(Part part) throws IOException {
        var id = UUID.randomUUID().toString();
        part.write(Paths.get(uploadPath).resolve(id).toString());
        return id;
    }
}