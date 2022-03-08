package controler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.CourseDao;
import dao.HyppodromeDao;
import model.Cheval;
import model.Course;

public class CourseControler {

	CourseDao courseDao = new CourseDao();
	HyppodromeDao hyppoDao = new HyppodromeDao();
	public boolean createCourseAndAddToHyppodrome(String nom, String date, List<Cheval> listeChevaux) throws ParseException {
		if (listeChevaux.size() < 6) {
			return false;
		}
		for (Cheval cheval : listeChevaux) {
			if (cheval == null) {
				return false;
			}
		}
		for (Course course : hyppoDao.getHyppodrome().getListeCourses()) {
			if (course.getNom().equals(nom)) {
				System.out.println("Le nom de la course existe déja, veuillez choisir un autre nom SVP.");
				return false;
			}
		}
		Course c = courseDao.createCourse(nom, date, listeChevaux);
		return courseDao.addCourseToHyppodrome(hyppoDao.getHyppodrome(), c);
		
	}
	public boolean deleteCourseFromHyppodrome(String NomCourse) {
		Course course = hyppoDao.getCourseByName(NomCourse);
		try {
			return courseDao.deleteCourseToHyppodrome(hyppoDao.getHyppodrome(), course); 

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
