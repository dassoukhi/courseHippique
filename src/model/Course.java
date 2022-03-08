package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.Parser;

public class Course {

	private String nom;
	private Date date;
	private Cheval chevalWinner;
	private List<Cheval> listeChevaux;
	
	public Course() {
		this.listeChevaux = new ArrayList<Cheval>();
	}
	

	public Course(String nom, String date, List<Cheval> listeChevaux) {
		this.nom = nom;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.date = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listeChevaux = listeChevaux;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Course(String nom, String date) throws ParseException {
		super();
		this.nom = nom;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.date = simpleDateFormat.parse(date);
		this.listeChevaux = new ArrayList<Cheval>();
	}

	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date);
		return strDate;
	}

	public void setDate(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.date = simpleDateFormat.parse(date);
	}

	public List<Cheval> getListeChevaux() {
		return listeChevaux;
	}

	public void setListeChevaux(List<Cheval> listeChevaux) {
		this.listeChevaux = listeChevaux;
	}
	
	public Cheval getChevalWinner() {
		return chevalWinner;
	}
	public void setChevalWinner(Cheval chevalWinner) {
		this.chevalWinner = chevalWinner;
	}
	public JSONObject getJsonCourse() {
		JSONObject obj = new JSONObject();
		obj.put("nom", getNom());
		obj.put("date", getDate());
		if (chevalWinner == null) {
			obj.put("chevalWinner", new Object());
		}
		obj.put("chevalWinner", getChevalWinner().getJsonCheval());
		
		JSONArray arrObj = new JSONArray();
		for (Cheval chev : getListeChevaux()) {
			arrObj.put(chev.getJsonCheval());
		}
		obj.put("chevaux", arrObj);
		return obj;
	}

	@Override
	public String toString() {
		return "Course [nom=" + nom + ", date=" + getDate() + ", chevalWinner="+ getChevalWinner()+", listeChevaux=" + listeChevaux + "]";
	}
	
	
}
