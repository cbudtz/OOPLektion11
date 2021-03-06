package ioExamples;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BinaryFileWriter {
    private final String dataDir = "./resources/data";

    public void writeBytes(byte[]bytes, String fileName){
        try {
            Files.createDirectories(Paths.get(dataDir)); //Create dir if not exists
            FileOutputStream fileOutputStream = new FileOutputStream(dataDir + "/" + fileName,true);
            fileOutputStream.write(bytes);
            fileOutputStream.close(); //Problem?
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BinaryFileWriter().writeBytes("Æbler og pærer\r\n".getBytes(StandardCharsets.UTF_8),"test.dat");
        new BinaryFileWriter().writeBytes(new byte[]{65, 98, 101},"test.dat");
    }
}
