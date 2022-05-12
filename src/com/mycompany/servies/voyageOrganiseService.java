package com.mycompany.servies;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
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
    public boolean result;
    private ConnectionRequest req;

    public voyageOrganiseService() {
         req = new ConnectionRequest();
    }

    public static voyageOrganiseService getInstance() {
        if (instance == null) {
            instance = new voyageOrganiseService();
        }
        return instance;
    }

  public List<voyageOrganise> getMyVoyages() {
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
                r.setVilleDepart((String) e.get("villedepart"));
                 r.setDateDepart((String) e.get("datedepart"));
                 r.setDateArrive((String) e.get("datearrive"));
                 r.setNbrPlace((int)Float.parseFloat(e.get("nbrplace").toString()));
                   r.setPrix(Float.parseFloat(e.get("prix").toString()));
             
         voyages.add(r);
         
             
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return voyages;
    
}



  public boolean ajoutvoy(voyageOrganise voy) {
    
     String url =Statics.BASE_URL+"/voyageorganise/addRecJson/";
       req.setUrl(url);
       req.setPost(false);
       req.addArgument("villedepart",voy.getVilleDepart());
       req.addArgument("villedest",voy.getVilleDest());
       req.addArgument("datedep",voy.getDateDepart());
         req.addArgument("datearrive",voy.getDateArrive());
          req.addArgument("nbrplace",voy.getNbrPlace()+"");
           req.addArgument("idcat",voy.getIdCat()+"");
            req.addArgument("prix",voy.getPrix()+"");
            req.addArgument("description",voy.getDescription());
        System.out.println(url);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
               
            }
        });
          NetworkManager.getInstance().addToQueueAndWait(req);
       
        return result;
       
      
    }

public boolean deleteVoy(int idvoy ) {
        String url = Statics.BASE_URL +"/voyageorganise/deletevoy/"+idvoy;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  result;
    }




}
