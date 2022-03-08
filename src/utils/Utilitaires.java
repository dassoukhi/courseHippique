package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Cheval;
import model.Course;

public class Utilitaires {
	
	public static JSONArray readJsonFromBDD() throws IOException, JSONException {
		String url = "src/dao/BDD.json";
		 Reader reader = new FileReader(url);
		 BufferedReader br = null;
		  try {
		    br = new BufferedReader(reader);
		    String jsonText = readAll(br);
		    JSONArray json = new JSONArray(jsonText);
		    return json;
		  } finally {
		    br.close();
		  } 
		}
	
	public static void migrateDataOnFile(String data) {
		String pathFile = "src/dao/BDD.json";
		try
		{
		    FileWriter fw = new FileWriter(pathFile,false); 
		    fw.write(data);
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
	public static String readAll(BufferedReader br) {
		StringBuilder contentBuilder = new StringBuilder();
        try {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString(); 
	}
	
	public static List<Course> getListOfCourseFromJsonObject(JSONArray jCourses){
		List<Course> courses = new ArrayList<Course>();
		for (int i = 0; i < jCourses.length(); i++) {
			String name  = jCourses.getJSONObject(i).getString("nom");
			String date  = jCourses.getJSONObject(i).getString("date");
			JSONObject cw = jCourses.getJSONObject(i).getJSONObject("chevalWinner");
			Cheval chevWin = new Cheval(cw.getString("nom"), cw.getInt("age"), cw.getInt("nbVictoire"));
			List<Cheval> listeChevaux = getListOfChevauxFromJsonObject(jCourses.getJSONObject(i).getJSONArray("chevaux"));
			Course c = new Course(name, date, listeChevaux);
			c.setChevalWinner(chevWin);
			courses.add(c);
		}
		return courses;
	}
	
	public static List<Cheval> getListOfChevauxFromJsonObject(JSONArray jChevaux){
		List<Cheval> chevaux = new ArrayList<Cheval>();
		
		for (int i = 0; i < jChevaux.length(); i++) {
			String name  = jChevaux.getJSONObject(i).getString("nom");
			int age  = jChevaux.getJSONObject(i).getInt("age");
			int victoires  = jChevaux.getJSONObject(i).getInt("nbVictoire");
			chevaux.add(new Cheval(name, age, victoires));			
		}
		return chevaux;
	}

	public static String saisieString() {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
	

	public static int saisieInt() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	public static int randomScore(int k) {
		Random r = new Random();
		return r.nextInt(k) + 1;
	}
	public static List<Integer> generePodiumCourse(Course c) {
		if (c == null) {
			return null;
		}
		List<Integer> podium = new ArrayList<>(6);
		for (int i = 0; i < 6; i++) {
			int temp = randomScore(6);
			while (podium.contains(temp)) {
				temp = randomScore(6);
			}
			podium.add(temp);
		}
		return podium;
	}
	
	public static Cheval getVictoryHorse (List<Integer> podium, Course course) {
		// index 1 for winner
		if (podium == null || course == null) {
			return null;
		}
		try {
			int indexVictory = podium.indexOf(1);
			course.setChevalWinner(course.getListeChevaux().get(indexVictory));
			return course.getChevalWinner();

		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			return null;
		}
		
	}
}