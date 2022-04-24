import data.Course;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataWriter {
    public static void main(String[] args) {
        Course course = new Course("OOP", 62514);
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(
                    new FileOutputStream("course.dat ",true)
            );
            dataOutputStream.writeUTF(course.name());
            dataOutputStream.writeInt(course.number());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
