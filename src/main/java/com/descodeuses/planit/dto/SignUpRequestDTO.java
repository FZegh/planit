package com.descodeuses.planit.dto;





public class SignUpRequestDTO {
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String genre;
    private String role;



 public SignUpRequestDTO() {}

 public SignUpRequestDTO(String username, String password, String lastname, String firstname, String genre, String role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.genre = genre;
        this.role = role;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }
    }


