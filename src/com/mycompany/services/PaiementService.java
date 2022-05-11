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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Paiement;
import com.mycompany.entities.Reservation;
import com.mycompany.entities.voyageOrganise;
//import com.mycompany.utlis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class PaiementService {
//      public ArrayList<Paiement> Paiements;
//    
//    public static  PaiementService instance=null;
//    public boolean resultOK;
//    private ConnectionRequest req;
//
//Paiement pp;
//    public static PaiementService getInstance() {
//        if (instance == null) {
//            instance = new PaiementService();
//        }
//        return instance;
//    }
//    public PaiementService() {
//         req = new ConnectionRequest();
//    }
//    
// 
//           
//  public List<Paiement> getMyPaiement() {
//         req=new ConnectionRequest();
//        String url = Statics.BASE_URL+"/paiement"+"/GetPaiement";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                 Paiements =parsePaiement(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return  Paiements;
//    }
//    public Paiement PaiementR(int id) {
//        
//         req=new ConnectionRequest();
//        String url = Statics.BASE_URL+"/paiement"+"/paiementdetailsr";
//        req.setUrl(url);
//           req.setPost(false);
//          req.addArgument("id",id+"");
//     
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                 pp = parsePaiement1(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return  pp;
//    }
//    
//    
//  
//   public ArrayList<Paiement> parsePaiement(String jsonText){
//        try {
//            Paiements=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = 
//               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            for(Map<String,Object> e : list){
//                Paiement r = new Paiement();
//                r.setModalite(e.get("modalitePaiement").toString());
//                
//                  
//                   r.setMontant((int) Float.parseFloat(e.get("montant").toString()));
//                   
//                    //r.setId_reservation((int) idr);
//               try
//       {     SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
//              java.util.Date parsedd = formatt.parse(e.get("date").toString());
//                  r.setDate(parsedd);
//       }
//       catch(ParseException ep)
//               {
//                    System.out.println(ep);
//               }
//
//          Paiements.add(r);
//             
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return  Paiements;
//    }
//   public boolean addPaiement(Paiement p ) {
//    
//      
//       
//       String url = Statics.BASE_URL + "/paiement/addpaimentmobile/";
//    
//       req.setUrl(url);
//       req.setPost(false);
//        System.out.println(url);
//       req.addArgument("idR",p.getId_reservation()+"");
//       req.addArgument("montant",+p.getMontant()+"");
//       req.addArgument("modep",p.getModalite()+"");
//       req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//                 String str = new String(req.getResponseData());
//            System.out.println(str);
//            if(resultOK)
//            {
//                
//            }
//                
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
//
//    
//      
//   public Paiement parsePaiement1(String jsonText){
//       Paiement pp = new Paiement();
//        try {
//           
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = 
//               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            for(Map<String,Object> e : list){
//               
//                pp.setModalite(e.get("modalitePaiement").toString());
//                
//        
//                  pp.setMontant((int) Float.parseFloat(e.get("montant").toString()));
//                   
//                    //r.setId_reservation((int) idr);
//               try
//       {     SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
//              java.util.Date parsedd = formatt.parse(e.get("date").toString());
//                  pp.setDate(parsedd);
//       }
//       catch(ParseException ep)
//               {
//                    System.out.println(ep);
//               }
//
//        
//             
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return  pp;
//    }
//    
}
