package controler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dao.HyppodromeDao;
import model.Cheval;
import model.Course;
import model.Hyppodrome;
import utils.Utilitaires;
import view.Menu;

public class Main {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		menu.bonjour();				
		while(true) {
			menu.choix();
		}
		
	}

}
