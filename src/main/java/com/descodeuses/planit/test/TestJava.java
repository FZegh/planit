package com.descodeuses.planit.test;

class Apprenante {
    private  Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String prenom;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


}

public class TestJava {

    public static void main (String[] args) {
        Apprenante descodeuses = new Apprenante();
		descodeuses.setPrenom("Princesse");

        int somme = 1+1;
        System.out.println("Resultat : " + somme);
        System.out.println(descodeuses.getAge());
		System.out.println(descodeuses.getPrenom());
    }

}
