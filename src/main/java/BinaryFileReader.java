import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BinaryFileReader {
    private final String dataDir = "./resources/data";

    byte[] readFile(String fileName){
        try {
            FileInputStream fis = new FileInputStream(dataDir+"/"+fileName);
            return fis.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        byte[] bytes = new BinaryFileReader().readFile("test.dat");
        for (byte aByte : bytes) {
            System.out.print((char) aByte);
        }
    }

}
