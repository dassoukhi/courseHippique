package controlerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import controler.ChevalControler;
import controler.CourseControler;
import model.Cheval;
import model.Course;
import model.Data;

class ChevalControlerTest {
	
	
	CourseControler cc = new CourseControler();
	ChevalControler chevCont = new ChevalControler();

	@Test
	public void createChevalTestOk() {
		assertNotNull(chevCont.createCheval("chevalTest", 12));
	}
	
	@Test
	public void createChevalTestKoNameExisted() {
		Cheval chev = new Cheval("chevalTest", 12);
		Course course = null;
		try {
			course = new Course("test", "22/02/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		course.getListeChevaux().add(chev);
		Data.getHyppodrome().getListeCourses().add(course);
		assertNull(chevCont.createCheval("chevalTest", 12));
	}
	
	@Test
	public void createChevalAndAddToCourseTestOK() {
		Course course = null;
		try {
			course = new Course("Course test", "22/02/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data.getHyppodrome().getListeCourses().add(course);
		boolean b = chevCont.createChevalAndToCourse("Course test", "chevalTest", 10);
		assertTrue(b);
	}
	
	@Test
	public void createChevalAndNotAddToCourseWhenCourseNull() {
		boolean b = chevCont.createChevalAndToCourse("course not found", "chevalTest", 10);
		assertFalse(b);
	}

}
