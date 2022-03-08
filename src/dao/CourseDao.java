package dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Cheval;
import model.Course;
import model.Data;
import model.Hyppodrome;

public class CourseDao {

	public Course createCourse(String nom, String date,List<Cheval> listeChevaux) throws ParseException {
		Course c = new Course(nom, date);
		c.setListeChevaux(listeChevaux);
		return c;
	}
	
	public boolean updateCourse(Course course, String name) {
		try {
			course.setNom(name);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean addCourseToHyppodrome(Hyppodrome hyppo,Course course) throws NullPointerException{
		if(course == null || hyppo == null)
			throw new NullPointerException();
		return hyppo.getListeCourses().add(course);
	}
	
	public boolean deleteCourseToHyppodrome(Hyppodrome hyppo,Course course) {
		if(course == null || hyppo == null)
			throw new NullPointerException();
		return hyppo.getListeCourses().remove(course);
		
	}
	
	public Cheval getChevalByNameFromCourse(String nom, Course course) {
		if (course == null) {
			return null;
		}
		for (Cheval cheval : course.getListeChevaux()) {
			if (cheval.getNom().equals(nom)) {
				return cheval;
			}
		} 
		return null;
	}

}
