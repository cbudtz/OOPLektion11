package ex1;

import java.util.Map;

public interface GradeDAOInterface {
    public void saveGrade(String studentId, String courseID, Double grade) throws FaultyGradeException;
    public Map<String, Double> loadGrades(String studentId);
    public Double getAverage(String studentId);

}
