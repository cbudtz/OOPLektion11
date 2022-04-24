import data.Course;

import java.io.*;

public class ObjectWriter {

    public static void main(String[] args) {
        Course oop = new Course("oop", 62514);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("objects.dat"));
             ObjectInputStream is = new ObjectInputStream(new FileInputStream("objects.dat"))
        ){
            os.writeObject(oop);
            Course loaded = (Course) is.readObject();
            System.out.println(loaded);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
