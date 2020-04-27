package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class OutputGenerator {
    private String filePath;
    private String fileName;

    public String save(String dataBuffer, String fileName) {
        setFilePath(fileName);
        final Path path = Paths.get(this.filePath);
        try (
                final BufferedWriter writer = Files.newBufferedWriter(path,
                        StandardCharsets.UTF_8, StandardOpenOption.CREATE)
        ) {
            writer.write(dataBuffer);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Failed to save data output into file!");
        }
        return this.filePath;
    }

    private void setFilePath(String fileName) {
        Path currentRelativePath;
        String filePath;
        File file;
        int fileIndex;

        currentRelativePath = Paths.get("");
        filePath = currentRelativePath.toAbsolutePath().toString();
        file = new File(filePath + "\\" + fileName + ".txt");

        if ( !file.exists() || file.isDirectory()) {
            this.filePath = filePath + "\\" + fileName + ".txt";
            this.fileName = fileName + ".txt";
            return;
        }

        fileIndex = 0;
        while ( file.exists() && !file.isDirectory() ) {
            fileIndex++;
            this.fileName = fileName + "(" + fileIndex + ")" + ".txt";
            file = new File(filePath + "\\" + this.fileName);
        }
        this.filePath = filePath + "\\" + this.fileName;
    }
}
