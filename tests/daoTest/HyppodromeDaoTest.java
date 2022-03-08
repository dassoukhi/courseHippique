package daoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import dao.HyppodromeDao;
import model.Course;
import model.Data;

class HyppodromeDaoTest {

	HyppodromeDao hyppDao = new HyppodromeDao();
	@Test
	void createHyppodromeTest() {
		assertNotNull(hyppDao.createHyppodrome("hyppodromeTest"));
	}
	
	@Test
	void getCourseByNameTestOk() throws ParseException {
		hyppDao.getHyppodrome().getListeCourses().add(new Course("course_test", "22/03/2022"));
		assertNotNull(hyppDao.getCourseByName("course_test"));
	}
	
	@Test
	void getCourseByNameTestKOWhenCourseDontExist() {
		assertNull(hyppDao.getCourseByName("course_test"));
	}
	
	@Test
	void getHyppodrommeTestOk() {
		assertNotNull(hyppDao.getHyppodrome());
	}
	
	@Test
	void getHyppodrommeTestKoWhenHyppoIsNull() {
		Data.setHyppodrome(null);
		assertNull(hyppDao.getHyppodrome());
	}

}
