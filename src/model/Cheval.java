package model;

import org.json.JSONObject;

public class Cheval {
	private String nom;
	private int age;
	private int nbVictoire = 0;
	
	public Cheval() {
		// TODO Auto-generated constructor stub
	}
	public Cheval(String nom, int age) {
		super();
		this.nom = nom;
		this.age = age;
	}
	public Cheval(String nom, int age, int victoire) {
		super();
		this.nom = nom;
		this.age = age;
		this.nbVictoire = victoire;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getNbVictoire() {
		return nbVictoire;
	}
	public void setNbVictoire(int nbVictoire) {
		this.nbVictoire = nbVictoire;
	}
	
	public JSONObject getJsonCheval() {
		JSONObject obj = new JSONObject();
		obj.put("nom", getNom());
		obj.put("age", getAge());
		obj.put("nbVictoire", getNbVictoire());
		return obj; 
	}
	@Override
	public String toString() {
		return "Cheval [nom=" + nom + ", age=" + age + ", nbVictoire=" + nbVictoire +"]";
	}
	
}
