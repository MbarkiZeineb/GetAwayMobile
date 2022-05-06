package com.mycompany.servies;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.company.entities.Reservation;
import com.company.entities.voyageOrganise;
import com.mycompany.utlis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class voyageOrganiseService {
       public ArrayList<voyageOrganise> voyages;
    
    public static  voyageOrganiseService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private voyageOrganiseService() {
         req = new ConnectionRequest();
    }

    public static voyageOrganiseService getInstance() {
        if (instance == null) {
            instance = new voyageOrganiseService();
        }
        return instance;
    }

  public List<voyageOrganise> getMyReservations() {
         req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/voyageorganise"+"/getallVoyage";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               voyages =parsevoyages(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return voyages;
    }
    
    
  
   public ArrayList<voyageOrganise> parsevoyages(String jsonText){
        try {
            voyages=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> e : list){
                voyageOrganise r = new voyageOrganise();
                 r.setIdVoy((int)Float.parseFloat(e.get("idvoy").toString()));
                r.setVilleDepart(e.get("villedepart").toString());
                r.setVilleDest(e.get("villedest").toString());
                r.setVilleDepart(e.get("villedepart").toString());
                 r.setDateDepart(e.get("datedepart").toString());
                 r.setDateArrive(e.get("datearrive").toString());
                 r.setNbrPlace((int)Float.parseFloat(e.get("nbrplace").toString()));
                   r.setPrix(Float.parseFloat(e.get("prix").toString()));
             
         voyages.add(r);
         
             
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return voyages;
    
}}
