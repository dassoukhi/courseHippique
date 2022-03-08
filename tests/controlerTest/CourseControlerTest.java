package controlerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controler.ChevalControler;
import controler.CourseControler;
import model.Cheval;
import model.Course;
import model.Data;

class CourseControlerTest {

	CourseControler cc = new CourseControler();
	ChevalControler chevCont = new ChevalControler();
	
	@Test
	public void createCourseAndAddToHyppodromeTestOk() throws ParseException {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		boolean b = cc.createCourseAndAddToHyppodrome("course_2","03/03/2022", listBuild);
		assertTrue(b);
	}
	
	@Test
	public void dontcreateCourseAndAddWhenListChevauxInfASixToHyppodromeTest() throws ParseException {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE);
		boolean b = cc.createCourseAndAddToHyppodrome("course_2","03/03/2022", listBuild);
		assertFalse(b);
	}
	
	@Test
	public void dontCreateCourseAndAddToHyppodromeWhenNameExistingYetTest() throws ParseException {
		Course course = new Course("course test", "22/02/2022");
		Data.getHyppodrome().getListeCourses().add(course);
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		boolean b = cc.createCourseAndAddToHyppodrome("course test","03/03/2022", listBuild);
		assertFalse(b);
	}
	
	@Test
	public void deleteCourseFromHyppodromeTestOk() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_2", "03/03/2022", listBuild);
		Data.getHyppodrome().getListeCourses().add(c);
		cc.deleteCourseFromHyppodrome("course_2");
		assertFalse(Data.getHyppodrome().getListeCourses().contains(c));
	}
	
}
