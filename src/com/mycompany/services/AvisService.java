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
import com.mycompany.entities.Avis;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Malek
 */
public class AvisService {
    
      public ArrayList<Avis> avis;
       public static boolean resultOk = true;
    
    public static  AvisService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public AvisService() {
         req = new ConnectionRequest();
    }

    public static AvisService getInstance() {
        if (instance == null) {
            instance = new AvisService();
        }
        return instance;
    }

  public List<Avis> getMyavis(int id) {
         req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/avis/avisgetting";
        req.setUrl(url);
        req.setPost(false);
         req.addArgument("id",+id+"");
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               avis =parseavis(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return avis;
    }
    
     public boolean AjouterAvis(Avis av ){
    String url =Statics.BASE_URL+"/avis/addavis"; 
         req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
       req.addArgument("message",av.getMessage()+"");
       req.addArgument("rating",av.getRating()+"");
       req.addArgument("id",av.getId()+"");
        req.addArgument("refactivite", av.getRefActivite()+"");
       
       System.out.println(req);
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        System.out.println(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
       
        return resultOK;
        
    }
    
  
   public ArrayList<Avis> parseavis(String jsonText){
        try {
            avis=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> e : list){

                Avis av = new Avis();
                 av.setRefAvis((int)Float.parseFloat(e.get("refavis").toString()));
                av.setNomClient((e.get("id").toString()));
                av.setNomAct((e.get("refactivite").toString()));
                av.setMessage(e.get("message").toString());
                av.setDate((e.get("date").toString()));
                av.setRating((int)Float.parseFloat(e.get("rating").toString()));
                
                
             
                avis.add(av);
         
             
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return avis;
    
}
   
    public boolean deleteAvis(int id) {
        String url = Statics.BASE_URL +"/avis/deleteavis/"+id;
        
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
