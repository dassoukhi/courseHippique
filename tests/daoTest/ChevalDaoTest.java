package daoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import dao.ChevalDao;
import model.Cheval;
import model.Course;

class ChevalDaoTest {

	ChevalDao chevDao = new ChevalDao();
	@Test
	void createChevalTestOk() {
		assertNotNull(chevDao.createCheval("cheval test", 10));
	}
	
	@Test
	void updateChevalTestOk() {
		Cheval chev = new Cheval("cheval test", 10);
		chevDao.updateCheval(chev, "chev update");
		assertEquals(chev.getNom(), "chev update");
	}
	
	@Test
	void updateChevalTestKoWhenChevalIsNull() {
		Cheval chev = null;
		assertFalse(chevDao.updateCheval(chev, "chev update"));
	}

	@Test
	void addChevalToCourseTestOk() throws ParseException {
		Course course = new Course("course test", "22/03/2022");
		Cheval chev = new Cheval("cheval test", 10);
		assertTrue(chevDao.addChevalToCourse(course, chev));
	}
	
	@Test
	void addChevalToCourseTestKoWhenCourseIsNull() {
		Course course = null;
		Cheval chev = new Cheval("cheval test", 10);
		assertThrows(NullPointerException.class, ()->chevDao.addChevalToCourse(course, chev));
	}
	
	@Test
	void addChevalToCourseTestKoWhenChevalIsNull() throws ParseException {
		Course course = new Course("course test", "22/03/2022");
		Cheval chev = null;
		assertThrows(NullPointerException.class, ()->chevDao.addChevalToCourse(course, chev));
	}
	
	@Test
	void deleteChevalToCourseTestOk() throws ParseException {
		Course course = new Course("course test", "22/03/2022");
		Cheval chev = new Cheval("cheval test", 10);
		course.getListeChevaux().add(chev);
		assertTrue(chevDao.deleteChevalToCourse(course, chev));
	}
	
	@Test
	void deleteChevalToCourseTestOkWhenCourseIsNull() {
		Course course = null;
		Cheval chev = new Cheval("cheval test", 10);
		assertFalse(chevDao.deleteChevalToCourse(course, chev));
	}
	
	@Test
	void deleteChevalToCourseTestOkWhenChevalIsNull() throws ParseException {
		Course course = new Course("course test", "22/03/2022");
		Cheval chev = null;
		assertFalse(chevDao.deleteChevalToCourse(course, chev));
	}
	
	
}
