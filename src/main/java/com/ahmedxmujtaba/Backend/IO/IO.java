package com.ahmedxmujtaba.Backend.IO;

import javax.management.ObjectInstance;
import java.io.*;

public class IO {

    // Save the object to the specified file location
    public static boolean saveFile (String fileLocation, Object object) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileLocation);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            return false;
        }
    }

    // Load the object from the specified file location
    public static Object loadFile (String fileLocation) {
        try (FileInputStream fileInputStream = new FileInputStream(fileLocation);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return objectInputStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            return null;
        }
    }
}

