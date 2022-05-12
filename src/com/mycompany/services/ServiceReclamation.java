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
import com.mycompany.entities.Reclamation;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Lenovo
 */
public class ServiceReclamation {
    
    //singleton 
    public static ServiceReclamation instance = null ;
    
    public boolean resultOK ;

    //initilisation connection request 
    private ConnectionRequest req = new ConnectionRequest();
    
    
    public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }
    
    
    
    public ServiceReclamation() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutReclamation(Reclamation reclamation) {
        
        String url =Statics.BASE_URL+"/reclamation/addRecmobile";
         req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
       req.addArgument("objet",reclamation.getObjet());
       req.addArgument("description",reclamation.getDescription());
         req.addArgument("idclient",reclamation.getIdC()+"");
       System.out.println(req);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
       
      
    }
    
    
    
//    //affichage
//    
//    public ArrayList<Reclamation>affichageReclamations() {
//        ArrayList<Reclamation> result = new ArrayList<>();
//        
//        String url = Statics.BASE_URL+"/reclamation/displayReclamations";
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp ;
//                jsonp = new JSONParser();
//                
//                try {
//                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    
//                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
//                    
//                    for(Map<String, Object> obj : listOfMaps) {
//                        Reclamation re = new Reclamation();
//                        
//                        //dima id fi codename one float 5outhouha
//                        float id = Float.parseFloat(obj.get("idR").toString());
//                        
//                        String objet = obj.get("objet").toString();
//                        
//                        String description = obj.get("description").toString();
//                        float etat = Float.parseFloat(obj.get("etat").toString());
//                        
//                        re.setIdR((int)id);
//                        re.setObjet(objet);
//                        re.setDescription(description);
//                        re.setEtat((int)etat);
//                        
//                     
//                        
//                        //insert data into ArrayList result
//                        result.add(re);
//                       
//                    
//                    }
//                    
//                }catch(Exception ex) {
//                    
//                    ex.printStackTrace();
//                }
//            
//            }
//        });
//        
//      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//        return result;
//        
//        
//    }

    public ArrayList<Reclamation> rec;
     public ArrayList<Reclamation> parseRecs(String json){
          ArrayList<Reclamation> rec=new ArrayList<>();
        try {
         
            JSONParser j = new JSONParser();
            Map<String,Object> RecsListJson = 
               j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)RecsListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation r = new Reclamation();
                r.setIdR((int)Float.parseFloat(obj.get("idr").toString()));
                if (obj.get("objet")==null)
              r.setObjet("null");
                else
                    r.setObjet(obj.get("objet").toString());
                if (obj.get("description")==null)
              r.setDescription("null");
                else
                    r.setDescription(obj.get("description").toString());
                
                rec.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
        return rec;
    }
    
    public ArrayList<Reclamation> getAllrec(){
        //String url = Statics.BASE_URL+"/tasks/";
       
        String url = Statics.BASE_URL+"/reclamation/displayReclamations";
         req=new ConnectionRequest();
         req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                rec = parseRecs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rec;
    }
    
    
    
    
     public List<Reclamation> getMyReclam(int id) {
         req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/user/getrecbyidc";
        
        req.setUrl(url);
       req.setPost(false);
         System.out.println(id);
          req.addArgument("idc",""+id);
        System.out.println(url);
  
//       req.addArgument("id",+SessionManager.getId()+"");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                rec =parseRecs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rec;
     }
    
    
    
    
    
    
    
    
    
    
//    
//    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
//    
//    public Reclamation DetailRecalamation( int id , Reclamation reclamation) {
//        
//        String url = Statics.BASE_URL+"/detailReclamation?"+id;
//        req.setUrl(url);
//        
//        String str  = new String(req.getResponseData());
//        req.addResponseListener(((evt) -> {
//        
//            JSONParser jsonp = new JSONParser();
//            try {
//                
//                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
//                
//                reclamation.setObjet(obj.get("obj").toString());
//                reclamation.setDescription(obj.get("description").toString());
//                reclamation.setEtat(Integer.parseInt(obj.get("etat").toString()));
//                
//            }catch(IOException ex) {
//                System.out.println("error related to sql :( "+ex.getMessage());
//            }
//            
//            
//            System.out.println("data === "+str);
//            
//            
//            
//        }));
//        
//              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//              return reclamation;
//        
//        
//    }
//    
//    
//    //Delete 
//    public boolean deleteReclamation(int id ) {
//        String url = Statics.BASE_URL +"/deleteReclamation?id="+id;
//        
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                    
//                    req.removeResponseCodeListener(this);
//            }
//        });
//        
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return  resultOk;
//    }
//    
//    
//    
//    //Update 
//    public boolean modifierReclamation(Reclamation reclamation) {
//        String url = Statics.BASE_URL +"/updateReclamation?id="+reclamation.getId()+"&objet="+reclamation.getObjet()+"&description="+reclamation.getDescription()+"&etat="+reclamation.getEtat();
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
//                req.removeResponseListener(this);
//            }
//        });
//        
//    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//    return resultOk;
//        
//    }
    
 public boolean deletereclam(int id){
           String url= Statics.BASE_URL+"/reclamation/DeleteReclam/"+id;
        req.setUrl(url);
      
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                                req.removeResponseListener(this);

                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        System.out.println(resultOK);
        return resultOK;
           
           
       }
    
}
