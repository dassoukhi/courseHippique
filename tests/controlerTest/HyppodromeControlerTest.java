package controlerTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controler.HyppodromeControler;
import model.Cheval;
import model.Course;
import model.Data;
import model.Hyppodrome;

class HyppodromeControlerTest {

	HyppodromeControler hc = new HyppodromeControler();
	@BeforeAll
	public static void initBdd(){
		Data.setHyppodrome(new Hyppodrome("Test"));
	}
	@Test
	public void createyppodromeFestivalTestOk() {
		hc.createHyppodrome("hyppodrommeYnov");
		assertTrue(Data.getHyppodrome().getNom().equals("hyppodrommeYnov"));
	}
	
	@Test
	public void getAllChevauxFromHyppodromTestOk() {
		List<Cheval> listChev = hc.getAllChevauxFromHyppodrom();
		List<Cheval> listAllCheveaux = new ArrayList<Cheval>();
		for (Course course : Data.getHyppodrome().getListeCourses()) {
			for (Cheval cheval : course.getListeChevaux()) {
				listAllCheveaux.add(cheval);
			}
		}
		assertEquals(listAllCheveaux.size(), listChev.size());
	}
	
	@Test
	public void getAllCourseFromHyppodromTestOk() {
		List<Course> listCourse = hc.getAllCourseFromHyppodrom();
			assertEquals(listCourse.size(), Data.getHyppodrome().getListeCourses().size());
	}
	
	@Test
	public void getDixLastCourseTestOk() {
		for (int i = 0; i < 11; i++) {
			List<Cheval> listBuild = new ArrayList<Cheval>();
			Cheval chevA = new Cheval("cheval_A"+i, 3);
			Cheval chevB = new Cheval("cheval_B"+i, 2);
			Cheval chevC = new Cheval("cheval_C"+i, 5);
			Cheval chevD = new Cheval("cheval_D"+i, 1);
			Cheval chevE = new Cheval("cheval_E"+i, 8);
			Cheval chevF = new Cheval("cheval_F"+i, 6);
			listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
			Course c = new Course("course_"+i, "02/02/202"+i, listBuild);
			Data.getHyppodrome().getListeCourses().add(c);
		}
		
		List<Course> listCourse = hc.getDixLastCourseOuMoins();
		assertTrue(listCourse.size() <= 10);
	} 
	
	@Test
	public void nombreCourseInscritTestOk() {
		int size = hc.nombreCourseInscrit();
		assertEquals(size, Data.getHyppodrome().getListeCourses().size());
	}
	
	@Test
	public void getChevalByNameFromHyppodromTestOk() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3, 3);
		Cheval chevB = new Cheval("cheval_B", 2, 5);
		Cheval chevC = new Cheval("cheval_C", 5, 1);
		Cheval chevD = new Cheval("cheval_D", 1, 4);
		Cheval chevE = new Cheval("cheval_E", 8, 6);
		Cheval chevF = new Cheval("cheval_F", 6, 3);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_Z", "02/02/202", listBuild);
		Data.getHyppodrome().getListeCourses().add(c);
		Cheval chev = hc.getChevalByNameFromHyppodrom("cheval_E");
		assertEquals(chev.getNom(), "cheval_E");
	}
	
	@Test
	public void getChevalByNameFromHyppodromTestkOWhenChevalDontExist() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3, 3);
		Cheval chevB = new Cheval("cheval_B", 2, 5);
		Cheval chevC = new Cheval("cheval_C", 5, 1);
		Cheval chevD = new Cheval("cheval_D", 1, 4);
		Cheval chevE = new Cheval("cheval_E", 8, 6);
		Cheval chevF = new Cheval("cheval_F", 6, 3);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_Z", "02/02/202", listBuild);
		Data.getHyppodrome().getListeCourses().add(c);
		Cheval chev = hc.getChevalByNameFromHyppodrom("cheval inconnu");
		assertNull(chev);
	}
	
	@Test
	public void getCourseByNameFromHyppodromTestKo() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3, 3);
		Cheval chevB = new Cheval("cheval_B", 2, 5);
		Cheval chevC = new Cheval("cheval_C", 5, 1);
		Cheval chevD = new Cheval("cheval_D", 1, 4);
		Cheval chevE = new Cheval("cheval_E", 8, 6);
		Cheval chevF = new Cheval("cheval_F", 6, 3);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_Z", "02/02/202", listBuild);
		Data.getHyppodrome().getListeCourses().add(c);
		Course course = hc.getCourseByNameFromHyppodrom("course_Z");
		assertEquals("course_Z", course.getNom());
	}
	
	
	@Test
	public void getCinqChevauxVictorieuxTestOk() {
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3, 3);
		Cheval chevB = new Cheval("cheval_B", 2, 5);
		Cheval chevC = new Cheval("cheval_C", 5, 1);
		Cheval chevD = new Cheval("cheval_D", 1, 4);
		Cheval chevE = new Cheval("cheval_E", 8, 6);
		Cheval chevF = new Cheval("cheval_F", 6, 3);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD); listBuild.add(chevE); listBuild.add(chevF);
		Course c = new Course("course_Z", "02/02/202", listBuild);
		Data.getHyppodrome().getListeCourses().add(c);
		List<Cheval> listChev = new ArrayList<Cheval>();
		for (Course course : Data.getHyppodrome().getListeCourses()){
			for (Cheval cheval : course.getListeChevaux()) {
				listChev.add(cheval);
			}
		}
		Collections.sort(listChev, (o1, o2) -> Integer.compare(o1.getNbVictoire() , o2.getNbVictoire()));
		Collections.reverse(listChev);
		listChev = listChev.subList(0, 5);
		List<Cheval> listChevWinners = hc.getCinqChevauxVictorieux();
		for (int i = 0; i < listChevWinners.size(); i++){
			assertEquals(listChevWinners.get(i), listChev.get(i));
		}
		
	}
	@Test
	public void getCinqChevauxVictorieuxTestKoWhenChevauxInfToCinq() {
		Data.setHyppodrome(new Hyppodrome("test"));
		List<Cheval> listBuild = new ArrayList<Cheval>();
		Cheval chevA = new Cheval("cheval_A", 3, 3);
		Cheval chevB = new Cheval("cheval_B", 2, 5);
		Cheval chevC = new Cheval("cheval_C", 5, 1);
		Cheval chevD = new Cheval("cheval_D", 1, 4);
		listBuild.add(chevA); listBuild.add(chevB); listBuild.add(chevC); listBuild.add(chevD);
		Course c = new Course("course_Z", "02/02/202", listBuild);
		Data.getHyppodrome().getListeCourses().add(c);
		
		assertNull(hc.getCinqChevauxVictorieux());
		
	}
	
}
