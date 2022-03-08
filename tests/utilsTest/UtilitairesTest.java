package utilsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dao.HyppodromeDao;
import model.Cheval;
import model.Course;
import utils.Utilitaires;

class UtilitairesTest {

	@Test
	void randomScoreTestOk() {
		assertTrue(Utilitaires.randomScore(6) <= 6);
	}

	@Test
	void generePodiumCourseTestOk() throws ParseException {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course course = new Course("coursetTest", "01/01/2010");
		course.setListeChevaux(listBuild);
		List<Integer> listG = Utilitaires.generePodiumCourse(course);
		//verification d'un seul vainqueur
		for (int i = 0; i < listG.size(); i++) {
			for (int j = 0; j < listG.size(); j++) {
				if (j != i) {
					assertTrue(!listG.get(i).equals(listG.get(j)));
				}
				
			}
		}
	}
	
	@Test
	void getVictoryHorseTestOk() throws ParseException {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course course = new Course("coursetTest", "01/01/2010");
		course.setListeChevaux(listBuild);
		List<Integer> poduim = new ArrayList<Integer>();
		poduim.add(2); poduim.add(4); poduim.add(1); poduim.add(5); poduim.add(6); poduim.add(3);
		assertEquals(Utilitaires.getVictoryHorse(poduim, course), chevC);
	}
	
	@Test
	void getVictoryHorseTesKoPoduimIsNullOrEmpty() throws ParseException {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3);
		Cheval chevB = new Cheval("cheval_B", 2);
		Cheval chevC = new Cheval("cheval_C", 5);
		Cheval chevD = new Cheval("cheval_D", 1);
		Cheval chevE = new Cheval("cheval_E", 8);
		Cheval chevF = new Cheval("cheval_F", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course course = new Course("coursetTest", "01/01/2010");
		course.setListeChevaux(listBuild);
		List<Integer> poduim = new ArrayList<Integer>();
		assertNull(Utilitaires.getVictoryHorse(poduim, course));
		poduim = null;
		assertNull(Utilitaires.getVictoryHorse(poduim, course));
	}
	
	@Test
	void getVictoryHorseTesKoCourseIsNull() {
		Course course = null;
		List<Integer> poduim = new ArrayList<Integer>();
		poduim.add(2); poduim.add(4); poduim.add(1); poduim.add(5); poduim.add(6); poduim.add(3);
		assertNull(Utilitaires.getVictoryHorse(poduim, course));
	}
	
	@Test
	void getListOfChevauxFromJsonObjectTestOk() {
		JSONObject obj1 = new JSONObject(); obj1.put("nom", "chevalTest1"); obj1.put("age", 10); obj1.put("nbVictoire", 2);
		JSONObject obj2 = new JSONObject(); obj2.put("nom", "chevalTest2"); obj2.put("age", 1); obj2.put("nbVictoire", 1);
		JSONArray obArray = new JSONArray();
		obArray.put(obj1); obArray.put(obj2);
		List<Cheval> listChev = Utilitaires.getListOfChevauxFromJsonObject(obArray);
		assertTrue(listChev.size() > 0);
		assertEquals("chevalTest1", listChev.get(0).getNom());
		assertEquals("chevalTest2", listChev.get(1).getNom());
	}
	
	@Test
	void getListOfCourseFromJsonObjectTestOk() {
		List<Cheval> chevList = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3, 3);
		JSONObject obj1 = new JSONObject(); obj1.put("nom", "courseTest1"); obj1.put("date", "02/02/2022"); obj1.put("chevaux", chevList); obj1.put("chevalWinner", chevA.getJsonCheval());
		JSONObject obj2 = new JSONObject(); obj2.put("nom", "courseTest2"); obj2.put("date", "23/02/2022"); obj2.put("chevaux", chevList); obj2.put("chevalWinner", chevA.getJsonCheval());
		JSONArray obArray = new JSONArray();
		obArray.put(obj1); obArray.put(obj2);
		List<Course> listCourse = Utilitaires.getListOfCourseFromJsonObject(obArray);
		assertTrue(listCourse.size() > 0);
		assertEquals("courseTest1", listCourse.get(0).getNom());
		assertEquals("courseTest2", listCourse.get(1).getNom());
	}
	
	@Test
	void readJsonFromUrlTestOk() throws MalformedURLException, IOException {
		JSONArray jsonArray = new JSONArray();
		try {
			jsonArray = Utilitaires.readJsonFromBDD();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "src/dao/BDD.json";
		Reader reader = new FileReader(url);
		 BufferedReader br = null;
		  try {
		    br = new BufferedReader(reader);
		    String jsonText = Utilitaires.readAll(br);
		    JSONArray json = new JSONArray(jsonText);
		    assertEquals(jsonArray.toString(), json.toString());
		  } finally {
		    br.close();
		  }
	}
	
	@Test
	void migrateDataOnFileTestOk() throws MalformedURLException, IOException, ParseException {
		HyppodromeDao hyppoDao = new HyppodromeDao();
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_je", 3);
		Cheval chevB = new Cheval("cheval_te", 2);
		Cheval chevC = new Cheval("cheval_st", 5);
		Cheval chevD = new Cheval("cheval_oui", 1);
		Cheval chevE = new Cheval("cheval_non", 8);
		Cheval chevF = new Cheval("cheval_Fuck", 6);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course course = new Course("coursetTest", "01/01/2010");
		course.setListeChevaux(listBuild);
		course.setChevalWinner(chevE);
		hyppoDao.getHyppodrome().getListeCourses().add(course);
		Utilitaires.migrateDataOnFile(hyppoDao.getHyppodrome().getJsonHyppodrome().toString());
		
		String url = "src/dao/BDD.json";
		Reader reader = new FileReader(url);
		 BufferedReader br = null;
		  try {
		    br = new BufferedReader(reader);
		    String jsonText = Utilitaires.readAll(br);
		    JSONArray json = new JSONArray(jsonText);
		    assertEquals(hyppoDao.getHyppodrome().getJsonHyppodrome().toString(), json.toString());
		  } finally {
		    br.close();
		  }
	}
	
}
