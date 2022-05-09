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
import com.mycompany.entities.Avion;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Malek
 */
public class AvionService {
    
      public ArrayList<Avion> avion;
       public static boolean resultOk = true;
    
    public static  AvionService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public AvionService() {
         req = new ConnectionRequest();
    }

    public static AvionService getInstance() {
        if (instance == null) {
            instance = new AvionService();
        }
        return instance;
    }

  public List<Avion> getMyavions() {
         req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/avion/getallAvion";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               avion =parsevoyages(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return avion;
    }
    
     public boolean AjouterAvion(Avion he ){
    String url =Statics.BASE_URL+"/avion/addavion"; 
         req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
       req.addArgument("nbrPlace",he.getNbr_place()+"");
       req.addArgument("nomAvion",he.getNom_avion()+"");
       System.out.println(req);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
       
        return resultOK;
        
    }
    
  
   public ArrayList<Avion> parsevoyages(String jsonText){
        try {
            avion=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> e : list){
//                int id_avion=(int)Float.parseFloat(e.get("id_avion").toString());
//                int nbr_place=(int)Float.parseFloat(e.get("nbr_place").toString());
//                int id_agence=(int)Float.parseFloat(e.get("id_agence").toString());
//                String nom_avion=e.get("nom_avion").toString();
//                 
//                avion.add(new Avion(id_avion, nbr_place,id_agence, nom_avion));

                Avion r = new Avion();
                 r.setId_avion((int)Float.parseFloat(e.get("idAvion").toString()));
                r.setNbr_place((int)Float.parseFloat(e.get("nbrPlace").toString()));
                r.setNom_avion(e.get("nomAvion").toString());
                
             
                avion.add(r);
         
        
         
             
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return avion;
    
}
   
    public boolean deleteAvion(int id ) {
        String url = Statics.BASE_URL +"/avion/deleteavion/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
  
    
}
