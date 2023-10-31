package com.example.dataencryption.utils;

import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;

public class Encryption {
    public static void encrypt(String path, int key) throws IOException {
        FileInputStream fis;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        byte[] data = new byte[fis.available()];


        fis.read(data);
        int i = 0;


        for (byte b : data) {
            data[i] = (byte) (b ^ key);
            i++;
        }
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(data);
        System.out.println("Encryption done");
        fos.close();
        fis.close();
    }

    public static void decrypt(String path, int key) throws IOException {
        FileInputStream fis = new FileInputStream(path);


        byte[] data = new byte[fis.available()];



        fis.read(data);
        int i = 0;


        for (byte b : data) {
            data[i] = (byte)(b ^ key);
            i++;
        }


        FileOutputStream fos = new FileOutputStream(
                path);


        fos.write(data);
        fos.close();
        fis.close();
        System.out.println("Decryption Done...");
    }
}
