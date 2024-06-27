package com.ahmedxmujtaba.Backend.IO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IO {

    // Save the object to the specified file location
    public static boolean saveFile(String fileLocation, Object object) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileLocation);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("IO exception: " + e.getMessage());
            return false;
        }
    }

    // Load the object from the specified file location with a generic type
    @SuppressWarnings("unchecked")
    public static <T> T loadFile(String fileLocation) {
        try (FileInputStream fileInputStream = new FileInputStream(fileLocation);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (T) objectInputStream.readObject();
        } catch (IOException e) {
            System.err.println("Generating new file at dir: " + fileLocation );
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
            return null;
        }
    }

    // Create a folder if it does not exist
    public static void createFolderIfNotExists(String folderPath) {
        Path path = Paths.get(folderPath);
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Folder created at: " + path);
            } catch (IOException e) {
                System.err.println("Failed to create folder: " + e.getMessage());
            }
        }
    }

    // Get the name of the current directory where the program is being executed
    public static String getCurrentDirectory() {
        Path currentDir = Paths.get("");
        return currentDir.toAbsolutePath().toString();
    }
}
