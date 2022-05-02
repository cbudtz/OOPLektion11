import ex1.FaultyGradeException;
import ex1.GradeDAO;
import ex1.GradeDAOInterface;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GradeDaoTest {
    static GradeDAOInterface gradeDao = null;

    @BeforeAll
    static void setup(){
        gradeDao = new GradeDAO();
    }

    @Test
    public void testSaveAndLoad(){
        try {
            gradeDao.saveGrade("test","62514",7.0);
            gradeDao.saveGrade("test","62001",10.0);
        } catch (FaultyGradeException e) {
            e.printStackTrace();
            fail();
        }

        Map<String, Double> test = gradeDao.loadGrades("test");
        assertEquals(7.0,test.get("62514"));
        assertEquals(10.0,test.get("62001"));
        assertEquals(2,test.size());
        assertEquals(8.5,gradeDao.getAverage("test"));
    }

    @Test
    public void testDoubleGrades(){
        try {
            gradeDao.saveGrade("test","62514",7.0);
            gradeDao.saveGrade("test","62514",10.0);
        } catch (FaultyGradeException e) {
            e.printStackTrace();
            fail();
        }

        //Then what? - should you have 2 grades in same course? Shouldnt the old grade be saved?
        Map<String, Double> test = gradeDao.loadGrades("test");
        assertEquals(10.0,test.get("62514"));
    }

    @Test
    public void testFaultyGrades(){
        try {
            gradeDao.saveGrade("test","",12.0);
        } catch (FaultyGradeException e) {
          return;
        }
        fail("saved faulty grade!");
    }

    @AfterAll
    public static void voidTearDown(){
        //Delete files
        try {
            Files.deleteIfExists(Path.of("./grades/test.grades"));
            Files.deleteIfExists(Path.of("./grades"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
