/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Activite;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author TheBoss'07
 */
public class ActiviteService {
    
    
    
     public ArrayList<Activite> act;
       public static boolean resultOk = true;
    public static String val="";
     public static String helmessage="";
    public static  ActiviteService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ActiviteService() {
         req = new ConnectionRequest();
    }

    public static ActiviteService getInstance() {
        if (instance == null) {
            instance = new ActiviteService();
        }
        return instance;
    }

    public static String getHelmessage() {
        return helmessage;
    }

    
    public boolean help(){
        
        req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/activite/help";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                 val = new String(req.getResponseData());
                   
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        helmessage=new String(req.getResponseData());
        System.out.println(helmessage+"aaaa");
        return resultOk;
    }
    

  public List<Activite> getMyactivite() {
         req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/activite/actgetting";
        req.setUrl(url);
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               act =parseActivite(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return act;
    }
   
  
   public ArrayList<Activite> parseActivite(String jsonText){
        try {
            act=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> e : list){
                
                Activite a = new Activite();
                a.setRefAct((int)Float.parseFloat(e.get("refact").toString()));
                a.setNom(e.get("nom").toString());
                a.setDescrip(e.get("descrip").toString());
                a.setDuree(e.get("duree").toString());
                a.setNbrPlace((int)Float.parseFloat(e.get("nbrplace").toString()));
                a.setDate(e.get("date").toString());
                a.setType(e.get("type").toString());
                a.setLocation(e.get("location").toString());
                a.setPrix((float)Float.parseFloat(e.get("prix").toString()));
                a.setImage(e.get("image").toString());
                
             
                act.add(a);
         

                    
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return act;
    
}
   
    
}