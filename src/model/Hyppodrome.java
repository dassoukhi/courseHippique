package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.Utilitaires;

public class Hyppodrome {
	private String nom;
	private List<Course> listeCourses;

	public Hyppodrome() {
		// TODO Auto-generated constructor stub
		this.listeCourses = new ArrayList<Course>();
	}

	public Hyppodrome(String nom) {
		this.nom = nom;
		this.listeCourses = new ArrayList<Course>();
	}
	public Hyppodrome(String nom, List<Course> courses) {
		this.nom = nom;
		this.listeCourses = courses;
	}
	
	public Hyppodrome(JSONObject hyp) {
		// TODO Auto-generated constructor stub
		this.nom = hyp.getString("nom");
		this.listeCourses = Utilitaires.getListOfCourseFromJsonObject(hyp.getJSONArray("courses"));
	}

	public List<Course> getListeCourses() {
		return this.listeCourses;
	}

	public void setListeCourses(List<Course> listeCourses) {
		this.listeCourses = listeCourses;
	}
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public JSONArray getJsonHyppodrome() {
		JSONObject obj = new JSONObject();
		obj.put("nom", getNom());
		
		JSONArray arrObj = new JSONArray();
		for (Course course : getListeCourses()) {
			arrObj.put(course.getJsonCourse());
		}
		obj.put("courses", arrObj);
		JSONArray arr = new JSONArray();
		arr.put(obj);
		return arr;
	}
	@Override
	public String toString() {
		return "Hyppodrome [nom=" + nom + ", listeCourses=" + listeCourses + "]";
	}
	
	

}
