package io.github.rahulrajsonu.springsecurityjwt.modules.fileutils;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class FileWriter {

    /*public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = null;
//        File file =  new File(System.getProperty("user.home"), "Desktop");
//        write("THIS IS DEMO NEW",file.getAbsolutePath()+"/Demo.txt");
//        File file = new File("installConfig/Demo.txt");
        String directoryName = "installation";
        String fileName = "service"+System.currentTimeMillis()+ ".json";

        File directory = new File(directoryName);
        if (!directory.exists()){
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        File file = new File(directoryName + "/" + fileName);
        write("THIS IS DEMO NEW",file.getAbsolutePath());
    }*/

//    public static void write(String str, String fileName) throws IOException {
//        FileOutputStream outputStream = null;
//        try{
//            outputStream = new FileOutputStream(fileName);
//            byte[] strToBytes = str.getBytes();
//            outputStream.write(strToBytes);
//        }finally {
//            outputStream.close();
//        }
//    }

    public static void write(Object str, String fileName) throws IOException {

        FileOutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(fileName);
            byte[] strToBytes = ((String)str).getBytes();
            outputStream.write(strToBytes);
        }catch (IOException ioException){
            ioException.printStackTrace();
        } finally {
            outputStream.close();
        }
    }

    public static void WriteObjectToFile(Object serObj, String filepath) {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
