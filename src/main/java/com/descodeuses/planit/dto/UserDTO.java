package com.descodeuses.planit.dto;

public class UserDTO {
    private Long id;
    private String username;
    private String role;
    private String firstname;
    private String lastname;




    public UserDTO() {}

    public UserDTO(Long id, String username, String role,  String firstname, String lastname  ) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        


    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


     public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
         this.firstname = firstname;
    }

     public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}
