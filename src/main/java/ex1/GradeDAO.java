package ex1;

import data.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

public class GradeDAO implements GradeDAOInterface {
    private static String dataDir = "./grades";
    public void saveGrade(String studentId, String courseID, Double grade) throws FaultyGradeException {
        //Sanity checks!
        if (courseID.isEmpty()) throw new FaultyGradeException();
        try {
            Student student = new Student();
            Files.createDirectories(Path.of(dataDir));
            if (Files.exists(Path.of(getFileName(studentId)))){
                student = loadStudent(studentId);
            }
            FileOutputStream fos = new FileOutputStream(getFileName(studentId));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            if (student != null) {
                student.getGrades().put(courseID,grade);
            }
            oos.writeObject(student);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileName(String studentId) {
        return dataDir + "/" + studentId + ".grades";
    }

    @Override
    public Map<String, Double> loadGrades(String studentId) {
            Student student = loadStudent(studentId);
        if (student != null) {
            return student.getGrades();
        }
        return null;
    }

    private Student loadStudent(String studentId) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(getFileName(studentId));
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student student = (Student) ois.readObject();
            fis.close();
            return student;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Double getAverage(String studentId) {
        Map<String, Double> stringDoubleMap = loadGrades(studentId);
        Double reduce = stringDoubleMap.values().stream().reduce(0.0, (acc, val) -> acc += val);
        return reduce/stringDoubleMap.size();
//        Collection<Double> values = stringDoubleMap.values();
//        double sum = 0.0;
//        for (Double grade: values) {
//            sum+= grade;
//        }
//        return sum/values.size();
    }
}
