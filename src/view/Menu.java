package view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.vintage.engine.support.UniqueIdStringifier;

import controler.ChevalControler;
import controler.CourseControler;
import controler.HyppodromeControler;
import model.Cheval;
import model.Course;
import utils.Utilitaires;

public class Menu {
	
	HyppodromeControler hyppoC = new HyppodromeControler();
	CourseControler courseC = new CourseControler();
	ChevalControler chevalC = new ChevalControler();
	
	public void bonjour() {
		System.out.println("Bienvenue dans notre hyppodrome");
	}
	
	
	public void choix() throws ParseException {
		System.out.println("Que voulez vous faire ? ");
		System.out.println("1 Faire une course");
		System.out.println("2 Afficher le resultat des dix(10) derniers courses");
		System.out.println("3 afficher les cinq(5) chevaux le plus victorieux");
		
		switch(Utilitaires.saisieString()){
		case "1":
			faireUneCourse();
			break;
		case "2":
			afficheDixDerniersCourse();
			break;
		case "3":
			afficherCinqChevauxVictorieux();
			break;
		default:
			System.out.println("Choix non compris, merci de réessayez SVP !!");
		}
	}
	
	public void faireUneCourse() throws ParseException {
		List<Cheval> listChevaux = new ArrayList<Cheval>();
		System.out.println("Donnez un nom à la course");
		String nom = Utilitaires.saisieString();
		System.out.println("Donnez une date de la course au format DD/MM/AAAA svp");
		String dte = Utilitaires.saisieString();
		while(true) {
			if (listChevaux.size() == 6) {
				break;
			}
			System.out.println("Vous devez ajoutez 6 chevaux à la course.");
			hyppoC.printAllChevauxFromHyppodrome();
			if (hyppoC.getAllCourseFromHyppodrom().size() > 0 ) {
				System.out.println("1 entrez le nom du cheval que vous voulez ajouter à la course");
			}
			System.out.println("2 créez un cheval pour l'ajouter à la course");
			if (listChevaux.size() > 0) {
				System.out.println("3 modifer un cheval de la course");
				System.out.println("4 supprimer un cheval de la course");
			}
			
			switch(Utilitaires.saisieString()){
			case "1":
				getChevalByNameUserEntry(listChevaux);
				break;
			case "2":
				createChevalAndAddToList(listChevaux);
				break;
			case "3":
				modiferChevalToList(listChevaux);
				break;
			case "4":
				supprimerChevalToList(listChevaux);
				break;
			default:
				System.out.println("Choix non compris, merci de réessayez SVP !!");
			}
		}
		courseC.createCourseAndAddToHyppodrome(nom, dte, listChevaux);
		Course c = hyppoC.getCourseByNameFromHyppodrom(nom);

		
		while (true) {
			
			Boolean exit = false;
			System.out.println("1 Commencez la course");
			System.out.println("2 Modifez la course");
			System.out.println("3 Supprimer la course");
			System.out.println("4 revenir au menu principal");
			switch(Utilitaires.saisieString()){
			case "1":
				commencerLaCourse(listChevaux, c);
				System.out.println("le cheval gagnant est :");
				Utilitaires.migrateDataOnFile(hyppoC.getHyppodrome().getJsonHyppodrome().toString());
				System.out.println(c.getChevalWinner());
				break;
			case "2":
				List<String> lStr = editCurrentCourse();
				if (lStr.get(0) != null) {
					c.setNom(lStr.get(0));
				}
				if (lStr.get(1) != null) {
					c.setDate(lStr.get(1));
				}
				break;
			case "3":
				afficheDixDerniersCourse();
				break;
			case "4":
				exit = true;
				break;
			default:
				System.out.println("Choix non compris, merci de réessayez SVP !!");
			}
			if (c.getChevalWinner() != null || exit) {
				break;
			}
		}
	}
	
	public void getChevalByNameUserEntry(List<Cheval> listChev) {
		System.out.println("entrez le nom du cheval");
		String name = Utilitaires.saisieString();
		Cheval chev = hyppoC.getChevalByNameFromHyppodrom(name);
		while(chev == null) {
			System.out.println("Le nom du cheval entré est introuvable, veuillez entrez un nom parmi la liste affichée SVP.");
			name = Utilitaires.saisieString();
			chev = hyppoC.getChevalByNameFromHyppodrom(name);
		}
		listChev.add(chev);
		String resFirst = listChev.size() == 1 ? " cheval a été ajouté à la course," : " chevaux ont été ajoutés à la course,";
		String reslast = listChev.size() == 6 ? "vous pouvez commencer la course" : " il en reste "+(6 - listChev.size());
		System.out.println("*** " + Integer.toString(listChev.size()) + resFirst + reslast + " ***");
		
	}
	public void commencerLaCourse(List<Cheval> listChev, Course c) {
		Cheval chev = Utilitaires.getVictoryHorse(Utilitaires.generePodiumCourse(c), c);
		chev.setNbVictoire(chev.getNbVictoire() + 1);
		c.setChevalWinner(chev);
	}
	
	public void createChevalAndAddToList(List<Cheval> listChev) {
		System.out.println("Entrez le nom du cheval");
		String nomChev = Utilitaires.saisieString();
		System.out.println("Entrez l'age du cheval");
		int ageChev = Utilitaires.saisieInt();
		Cheval chev = new Cheval(nomChev, ageChev);
		listChev.add(chev);
		String resFirst = listChev.size() == 1 ? " cheval a été ajouté à la course," : " chevaux ont été ajoutés à la course,";
		String reslast = listChev.size() == 6 ? "vous pouvez commencer la course" : " il en reste "+(6 - listChev.size());
		System.out.println("*** " + Integer.toString(listChev.size()) + resFirst + reslast + " ***");
	}
	public void afficheDixDerniersCourse() {
		for (Course course : hyppoC.getDixLastCourseOuMoins()) {
			System.out.println(course);
		}
		
	}
	
	public void afficherCinqChevauxVictorieux() {
		List<Cheval> chevList =  hyppoC.getCinqChevauxVictorieux();
		for (Cheval cheval : chevList) {
			System.out.println(cheval);
		}
		
	}
	public void supprimerChevalToList(List<Cheval> listChev) {
		System.out.println("Entrez le nom du cheval que vous voulez supprimer");
		String nom = Utilitaires.saisieString();
		Cheval find = null;
		for (Cheval cheval : listChev) {
			if (cheval.getNom().equals(nom)) {
				find = cheval;
			}
		}
		if (find != null) {
			listChev.remove(find);
			System.out.println("le cheval '"+ find.getNom() + "' a été bien supprimé de la course");
			
		}else {
			System.out.println("Le nom du cheval entrez n'a pas été trouvé, veuillez entrzle bon nom SVP");

		}
	}
	
	public void modiferChevalToList(List<Cheval> listChev) {
		System.out.println("Entrez le nom du cheval que vous voulez modifer");
		String nom = Utilitaires.saisieString();
		Cheval find = null;
		for (Cheval cheval : listChev) {
			if (cheval.getNom().equals(nom)) {
				find = cheval;
			}
		}
		if (find != null) {
			System.out.println("Que voulez-vous modifer ?");
			System.out.println("1 Modifier le nom du cheval");
			System.out.println("2 Modifier l'age du cheval");
			switch (Utilitaires.saisieString()) {
			case "1":
				String newName = Utilitaires.saisieString();
				find.setNom(newName);
				System.out.println("le nom du cheval a été bien modifié");
				break;
			case "2":
				int newAge = Utilitaires.saisieInt();
				find.setAge(newAge);
				System.out.println("l'age du chaval a été bien modifié");
				break;

			default:
				System.out.println("choix incompris, veuillez réessayer SVP!");
				break;
			}
			
		}else {
			System.out.println("Le nom du cheval entrez n'a pas été trouvé, veuillez entrez le bon nom SVP");

		}
	}
	
	public List<String> editCurrentCourse() {
		String nom = null; String date=null;
		List<String> listModif = new ArrayList<String>(2);
		listModif.add(nom); listModif.add(date);
		System.out.println("Que voulez-vous modifer ?");
		System.out.println("5 Modifier le nom de la course");
		System.out.println("6 Modifier la date de la course");
		switch (Utilitaires.saisieString()) {
		case "5":
			System.out.println("Entrez le nouveau nom de la course");
			nom = Utilitaires.saisieString();
			listModif.set(0, nom);
			System.out.println("le nom de la course a été bien modifié");
			break;
		case "6":
			System.out.println("Entrez la nouvelle date de la course");
			date = Utilitaires.saisieString();
			listModif.set(1, date);
			System.out.println("la date de la course a été bien modifié");
			break;

		default:
			System.out.println("choix incompris, veuillez réessayer SVP!");
			break;
		}
		
		return listModif;
	}

	
}
