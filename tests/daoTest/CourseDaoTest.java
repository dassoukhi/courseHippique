package daoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.xml.sax.Parser;

import dao.CourseDao;
import dao.HyppodromeDao;
import model.Cheval;
import model.Course;
import model.Data;
import model.Hyppodrome;

class CourseDaoTest {

	CourseDao courseDao = new CourseDao();
	HyppodromeDao hyppoDao = new HyppodromeDao(); 
	@Test
	void createCourseTestOk() throws ParseException {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = courseDao.createCourse("course_2","03/03/2022", listBuild);
		assertNotNull(c);
	}
	
	@Test
	void createCourseTestkOWhenDateIsMalFormed() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		assertThrows(ParseException.class, ()-> courseDao.createCourse("course_2","bad format", listBuild));
	}
	
	@Test
	void updateCourseTestOk() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Course c = new Course("course_2","03/03/2022", listBuild);
		courseDao.updateCourse(c, "Course update");
		assertEquals(c.getNom(), "Course update");
	}
	
	@Test
	void addCourseToHyppodromeTestOk() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_2","03/03/2022", listBuild);
		Hyppodrome hyppo = hyppoDao.getHyppodrome();
		assertTrue(courseDao.addCourseToHyppodrome(hyppo, c));
	}
	
	@Test
	void addCourseToHyppodromeTestKoWhenHyppoIsNull() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_2","03/03/2022", listBuild);
		Hyppodrome hyppo = null;
		assertThrows(NullPointerException.class, () -> courseDao.addCourseToHyppodrome(hyppo, c));
	}
	
	@Test
	void addCourseToHyppodromeTestKoWhenCourseIsNull() {
		Course c = null;
		Hyppodrome hyppo = hyppoDao.getHyppodrome();
		assertThrows(NullPointerException.class, () -> courseDao.addCourseToHyppodrome(hyppo, c));
	}
	

	@Test
	void deleteCourseToHyppodromeTestOk() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_2","03/03/2022", listBuild);
		Data.getHyppodrome().getListeCourses().add(c);
		Hyppodrome hyppo = Data.getHyppodrome();
		assertTrue(courseDao.deleteCourseToHyppodrome(hyppo, c));
	}
	
	@Test
	void deleteCourseToHyppodromeTestKoWhenHyppoIsNull() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_2","03/03/2022", listBuild);
		Data.getHyppodrome().getListeCourses().add(c);
		Hyppodrome hyppo = null;
		assertThrows(NullPointerException.class, () -> courseDao.deleteCourseToHyppodrome(hyppo, c));
	}
	
	@Test
	void deleteCourseToHyppodromeTestKoWhenCourseIsNull() {	
		Course c = null;
		Data.getHyppodrome().getListeCourses().add(c);
		Hyppodrome hyppo = Data.getHyppodrome();
		assertThrows(NullPointerException.class, () -> courseDao.deleteCourseToHyppodrome(hyppo, c));
	}
	
	
	@Test
	void getChevalByNameFromCourseTestOk() {	
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_2","03/03/2022", listBuild);
		assertNotNull(courseDao.getChevalByNameFromCourse("cheval_A", c));
	}
	
	@Test
	void getChevalByNameFromCourseTestkOWhenChevalNotExist() {	
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_2","03/03/2022", listBuild);
		assertNull(courseDao.getChevalByNameFromCourse("cheval_Incounu", c));
	}
	
	@Test
	void getChevalByNameFromCourseTestkOWhenCourseisNull() {	
		Course c = null;
		assertNull(courseDao.getChevalByNameFromCourse("cheval_Incounu", c));
	}
	

}
