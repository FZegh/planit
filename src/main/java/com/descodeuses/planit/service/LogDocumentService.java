package com.descodeuses.planit.service;

//import java.sql.Date;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.entity.LogDocument;
import com.descodeuses.planit.repository.LogDocumentRepository;

@Service
public class LogDocumentService {

    @Autowired
    private LogDocumentRepository repo;
    private Map<String, Object> extras = new HashMap<>();   //<String, Object>  = <Clé, Valeur>   = <Nom de la propriété  ,Contenu

    public LogDocumentRepository getRepo() {
        return this.repo;
    }

    public void setRepo(LogDocumentRepository repo) {
        this.repo = repo;
    }

    public Map<String,Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String,Object> extras) {
        this.extras = extras;
    }


    public void addLog(String title, Boolean completed, String className) {
        LogDocument doc = new LogDocument();
        doc.setText("Todo called");
        doc.setTimestamp(new Date());

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("title", title);
        requestMap.put("completed", completed);
        requestMap.put("_class", className);

        Map<String, Object> extrasMap = new HashMap<>();
    extrasMap.put("request", requestMap);



        doc.setExtras(extrasMap);
            repo.save(doc);

           
        System.out.println("🎯 LogDocument enregistré !");
System.out.println(doc);

    }


}
