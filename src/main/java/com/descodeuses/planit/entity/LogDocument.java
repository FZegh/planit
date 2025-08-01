package com.descodeuses.planit.entity;

import org.springframework.data.mongodb.core.mapping.Document;

//import java.sql.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

@Document(collection = "LogDocument")
public class LogDocument {

    @Id
    private String id;
    
    private String text;

    private Date timestamp;

    private Map<String, Object> extras = new HashMap<>(); 

   
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

     public Map<String,Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String,Object> extras) {
        this.extras = extras;
    }
   


}
