package controler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import dao.HyppodromeDao;
import model.Cheval;
import model.Course;
import model.Hyppodrome;

public class HyppodromeControler {
	
	HyppodromeDao hyppoDao = new HyppodromeDao();
	public int nombreCourseInscrit() {
		return hyppoDao.getHyppodrome().getListeCourses().size();
	}
	public void createHyppodrome(String nom) {
		hyppoDao.createHyppodrome(nom);
	}
	public List<Course> getDixLastCourseOuMoins(){
		int size = hyppoDao.getHyppodrome().getListeCourses().size();
		List<Course> listTemp = hyppoDao.getHyppodrome().getListeCourses();
		int index = 0;
		List<Course> resList = new ArrayList<>();
		for (int i = size-1; i >= 0; i--) {
			if (index == 10) {
				break;
			}
			else {
				resList.add(listTemp.get(i));
			}
			index ++;
		}
		return resList;
	}
	public List<Cheval> getAllChevauxFromHyppodrom(){
		List<Cheval> listAllCheveaux = new ArrayList<Cheval>();
		for (Course course : hyppoDao.getHyppodrome().getListeCourses()) {
			for (Cheval cheval : course.getListeChevaux()) {
				listAllCheveaux.add(cheval);
			}
		}
		return listAllCheveaux;
	}
	public Cheval getChevalByNameFromHyppodrom(String name){
		List<Cheval> listAllCheveaux = new ArrayList<Cheval>();
		for (Course course : hyppoDao.getHyppodrome().getListeCourses()) {
			for (Cheval cheval : course.getListeChevaux()) {
				if (cheval.getNom().equals(name)) {
					return cheval;
				}
			}
		}
		return null;
	}
	
	public Course getCourseByNameFromHyppodrom(String name){
		return hyppoDao.getCourseByName(name);
	}
	public List<Course> getAllCourseFromHyppodrom(){
		return hyppoDao.getHyppodrome().getListeCourses();
	}
	
	public void printAllChevauxFromHyppodrome() {
		List<Cheval> list = getAllChevauxFromHyppodrom();
		System.out.println(list.size() > 0 ? "Liste des chevaux disponible par nom : " : "Pas des chevaux existant, vous devez en créez pour faire la course.");
		
		if (list.size() > 0) {
			System.out.print("[ ");
		}
		for (int i = 0; i < list.size(); i++)  {
			System.out.print(list.get(i).getNom());
			
			if (i + 1 == list.size()) {
				System.out.println(" ]");
			}else {
				System.out.print(", ");
			}
			
		}
		
	}
	public List<Cheval> getCinqChevauxVictorieux(){
		List<Cheval> chevList = getAllChevauxFromHyppodrom();
		if (chevList.size() < 5) {
			return null;
		}
		Collections.sort(chevList, (o1, o2) -> Integer.compare(o1.getNbVictoire() , o2.getNbVictoire()));
		Collections.reverse(chevList);
		return chevList.subList(0, 5);
	}
	public Hyppodrome getHyppodrome() {
		return hyppoDao.getHyppodrome();
	}

}
