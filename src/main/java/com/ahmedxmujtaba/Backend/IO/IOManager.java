package com.ahmedxmujtaba.Backend.IO;
import java.io.*;
import java.security.PublicKey;


public class IOManager {

    //what do we need.
    // output the class of the skill as ser.
    // output tasks also

    public void saveFile(String fileLocation, Object object) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(fileLocation);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
    }
    public Object loadFile(String fileLocation) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileLocation);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }
}