package controler;

import java.util.Iterator;

import dao.ChevalDao;
import dao.CourseDao;
import dao.HyppodromeDao;
import model.Cheval;
import model.Course;

public class ChevalControler {
	
	ChevalDao cheDao = new ChevalDao();
	CourseDao courseDao = new CourseDao();
	HyppodromeDao hyppoDao = new HyppodromeDao();
 	
	public Cheval createCheval(String nom, int age) {
		for (Course course : hyppoDao.getHyppodrome().getListeCourses()) {
			for (Cheval cheval : course.getListeChevaux()) {
				if (cheval.getNom().equals(nom)) {
					System.out.println("Le nom du cheval choisit existe déja, Veuillez choisir un autre nom SVP.");
					return null;
				}
			}
		}
		return cheDao.createCheval(nom, age);
	}
	
	public boolean createChevalAndToCourse(String NomCourse, String NomCheval, int age) {
		Cheval cheval = new Cheval(NomCheval, age);
		Course course = hyppoDao.getCourseByName(NomCourse);
		try {
			return cheDao.addChevalToCourse(course, cheval); 

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean deleteChevalFromCourse(String NomCourse, String NomCheval, int age) {
		Cheval cheval = new Cheval(NomCheval, age);
		Course course = hyppoDao.getCourseByName(NomCourse);
		try {
			return cheDao.deleteChevalToCourse(course, cheval); 

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
