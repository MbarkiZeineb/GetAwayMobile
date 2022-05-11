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
import com.mycompany.entities.Vol;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Malek
 */
public class VolService {
    
    public ArrayList<Vol> vol;
       public static boolean resultOk = true;
    
    public static  VolService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public VolService() {
         req = new ConnectionRequest();
    }

    public static VolService getInstance() {
        if (instance == null) {
            instance = new VolService();
        }
        return instance;
    }

  public List<Vol> getMyvols() {
         req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/vol/getallVol";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               vol =parsevoyages(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return vol;
    }
   
  
   public ArrayList<Vol> parsevoyages(String jsonText){
        try {
            vol=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> e : list){

                Vol r = new Vol();
                 r.setNum_vol((int)Float.parseFloat(e.get("numVol").toString()));
                r.setNbr_placedispo((int)Float.parseFloat(e.get("nbrPlacedispo").toString()));
                r.setVille_depart(e.get("villeDepart").toString());
                r.setVille_arrivee(e.get("villeArrivee").toString());
                r.setDate_depart(e.get("dateDepart").toString());
                r.setDate_arrivee(e.get("dateArrivee").toString());
                 r.setPrix((float)Float.parseFloat(e.get("prix").toString()));
                
             
                vol.add(r);
         
        
         
             
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return vol;
    
}
   
   
     public ArrayList<Vol> order_By_PrixJSON() 
     {
        ArrayList<Vol> result = new ArrayList<>();
         String url = Statics.BASE_URL+"/vol/order_By_PrixJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;                
                jsonp = new JSONParser();
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> mapVol = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapVol.get("root");
                      
                    for (Map<String, Object> obj : listOfMaps) {
                        Vol v = new Vol();
                        int num_vol = (int) Float.parseFloat(obj.get("numVol").toString());
                         int nbrplace = (int) Float.parseFloat(obj.get("nbrPlacedispo").toString());
                        String villeDepart = obj.get("villeDepart").toString();
                        String villeArrivee = obj.get("villeArrivee").toString();
                        String dateDepart = obj.get("dateDepart").toString();
                        String dateArrivee = obj.get("dateArrivee").toString();
                        float Prix=(float)Float.parseFloat(obj.get("prix").toString());  

                        
                        v.setNum_vol((int) num_vol);
                        v.setNbr_placedispo((int)nbrplace);
                        v.setVille_depart(villeDepart);
                        v.setVille_arrivee(villeArrivee);
                        v.setDate_depart(dateDepart);
                        v.setDate_arrivee(dateArrivee);
                        v.setPrix((float)Prix);
                        
                result.add(v);
                  
                    }
                } 

       catch(Exception e ){
                       e.printStackTrace();
                   }
            }           
                });
        
         NetworkManager.getInstance().addToQueueAndWait(req);
                             
           return result;
    }
    
    
}
