package dao;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Course;
import model.Data;
import model.Hyppodrome;
import utils.Utilitaires;

public class HyppodromeDao {
	Hyppodrome mHypo = null;
	public HyppodromeDao() {
		createHyppodrome("YnovHyppodrome");
	}
	public Hyppodrome createHyppodrome(String nom) {
		mHypo = null;
		try {
			JSONArray array = Utilitaires.readJsonFromBDD();
			for (int i = 0; i < array.length(); i++) {
				JSONObject hyp = array.getJSONObject(i);
				mHypo = new Hyppodrome(hyp);
				if (nom != null) {
					mHypo.setNom(nom);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mHypo == null) {
			Data.setHyppodrome(new Hyppodrome(nom));
		}else {
			Data.setHyppodrome(mHypo);
		}
		return Data.getHyppodrome();
	}
	
	public Course getCourseByName(String nom) {
		for (Course course : Data.getHyppodrome().getListeCourses()) {
			if (course.getNom().equals(nom)) {
				return course;
			}
		}
		return null;
	}
	
	public Hyppodrome getHyppodrome(){
		return Data.getHyppodrome();
	}
	
}
