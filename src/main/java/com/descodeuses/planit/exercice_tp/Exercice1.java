package com.descodeuses.planit.exercice_tp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

class Tache {

    private String nom;
    private Boolean faite;
    private LocalDate dateEcheance;

    public Tache(String nom, LocalDate dateEcheance) {
        this.nom = nom;
        this.dateEcheance = dateEcheance;
        this.faite = false;  // car pour l'instant elle n'a pas encore été faite
    }

    public String getNom(){
        return nom;
    }

    public Boolean getFaite(){
        return faite;
    }

    public  void tacheFaite() {      //methode pour changer la tache non faite en une tache réalisée
        this.faite = true;
    }

    public LocalDate getDateEcheance() {
    return dateEcheance;
}


}


public class Exercice1 {
   public static ArrayList<Tache> taches = new ArrayList<>();
public static void main (String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choix = 0;
    

    while (choix != 5) {
        System.out.println("\n----------------------------");
        System.out.println("         MENU PLANIT        ");
        System.out.println("----------------------------");
        System.out.println("1. Ajouter une tâche");
        System.out.println("2. Voir les tâches");
        System.out.println("3. Marquer comme faite");
        System.out.println("4. Supprimer une tâche");
        System.out.println("5. Quitter");
        System.out.print("Votre choix : ");

        if (scanner.hasNextInt()) {
        choix = scanner.nextInt();
        scanner.nextLine(); // vider le buffer  
        }
        else {
            System.out.println("⚠️ Entrée invalide. Veuillez taper un nombre.");
            scanner.nextLine(); // Nettoyer l’entrée
            continue;
        }

        if (choix == 1) {
            ajouterTache(scanner);
        } else if (choix == 2) {
            voirTaches();
        } else if (choix == 3) {
            marquerCommeFaite(scanner);
        } else if (choix == 4) {
            supprimerTache(scanner); 
        } else if (choix == 5) {
            quitterTache();
        } else {
            System.out.println("Choix invalide.");
        }

        if (choix != 5) {
            System.out.println("\nAppuyez sur Entrée pour continuer...");
            scanner.nextLine();
        }
    }

    scanner.close();


};

public static void ajouterTache(Scanner scanner) {

    System.out.println("Entrer le nom de la tache");
    String nom = scanner.nextLine();

    LocalDate date = null;
    boolean dateValide = false;
    
    while (!dateValide) {
    System.out.println("Entrer la date d'échéance (format : AAAA-MM-JJ) :");
    String saisie = scanner.nextLine();

    try {
            date = LocalDate.parse(saisie);
            dateValide = true;
        } catch (DateTimeParseException e) {
            System.out.println("❌ Format invalide. Exemple correct : 2025-07-30");
        }
    }


    Tache nouvelleTache = new Tache(nom, date);
    taches.add(nouvelleTache);

    System.out.println("Tâche ajouté avec succès");
}

public static void voirTaches(){

    if (taches.isEmpty()) {
        System.out.println("Aucune tâche enregistré");
        return;
    }
int i = 1;
    for (Tache t : taches) {
        String statut = t.getFaite() ? "faite" : "à faire";  //condition ternaire 
        System.out.println(i+ "." + t.getNom() + "["+ statut +"] - echeance : " + t.getDateEcheance()); 
        i++;// 3
    }
    
   
}

// 3. Marquer comme faite
public static void marquerCommeFaite(Scanner scanner){

    if(taches.isEmpty()) {
        System.out.println("Aucune tâche enregistrée");
        return;
    }


    voirTaches();

    System.out.println("Entrer le numéro de la tâches effectuée");

    int numero = 0;


    if (scanner.hasNextInt()) {
        numero = scanner.nextInt();
        scanner.nextLine();
    } else {
        System.out.println("Veuillez entrer un nombre valide.");
        scanner.nextLine();
        return;
    }

    
    if(numero < 1 || numero > taches.size()) {
        System.out.println("Numéro non valide");
        return;
    }

    Tache tache = taches.get(numero -1);
    tache.tacheFaite();

    System.out.println("La tâche \"" + tache.getNom() + "\" a été marquée comme faite !");

}

public static void supprimerTache(Scanner scanner) {

    if (taches.isEmpty()) {
        System.out.println("Aucune tâche à supprimer.");
        return;
}

voirTaches();

System.out.print("Numéro de la tâche à supprimer : ");
    int numero = 0;
    if (scanner.hasNextInt()) {
        numero = scanner.nextInt();
        scanner.nextLine();
    }

        else {
        System.out.println("Veuillez entrer un nombre valide.");
        scanner.nextLine(); // consommer la saisie incorrecte
        return;
    }


if(numero >= 1 && numero <= taches.size()){

    System.out.println();
    Tache tacheSupprimee = taches.remove(numero - 1);
System.out.println("Tâche supprimée : " + tacheSupprimee.getNom() + " ❌");



} else {

    System.out.println("Numéro invalide !");

}

}

public static void quitterTache(){  //pas besoin de Scanner scanner 

    System.out.println("Merci d'avoir utilisé l'appli PlanIt !");
}
}

