package dao;

import model.Cheval;
import model.Course;

public class ChevalDao {

	public Cheval createCheval(String nom, int age) {
		return new Cheval(nom, age, 0);
	}
	
	public boolean updateCheval(Cheval cheval, String name) {
		try {
			cheval.setNom(name);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean addChevalToCourse(Course course, Cheval cheval) throws NullPointerException{
		if(cheval == null || course == null)
			throw new NullPointerException();
		return course.getListeChevaux().add(cheval);
	}
	
	public boolean deleteChevalToCourse(Course course, Cheval cheval) {
		try {
			return course.getListeChevaux().remove(cheval);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
