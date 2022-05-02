package ioExamples;

import data.Course;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReader {

    public static void main(String[] args) {
        //Try with resources
        try (DataInputStream dIS = new DataInputStream(new FileInputStream("course.dat"))) {
            Course course = new Course(dIS.readUTF(),dIS.readInt());
            System.out.println(course);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
