package com.descodeuses.planit.dto;

public class AuthRequestDTO {
     private String username;
    private String password;
     private String lastname;
    private String firstname;
   private String genre;

    

     public AuthRequestDTO(){}
    public AuthRequestDTO(String username, String password, String lastname, String firstname, String genre){
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.genre = genre;
    



        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    




    


}
