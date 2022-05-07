/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servies;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.company.entities.Hebergement;
import com.company.entities.Paiement;
import com.company.entities.Reservation;
import com.company.entities.Vol;
import com.company.entities.voyageOrganise;
import com.mycompany.utlis.Statics;
import static com.mycompany.utlis.Statics.BASE_URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.*;
import java.util.concurrent.*;
import java.util.Map;


/**
 *
 * @author Asus
 */
public class ReservationService {
    
     public ArrayList<Reservation> reservations;
         public static String val="";
          public static String testhebergement="";
    public static  ReservationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ReservationService() {
         req = new ConnectionRequest();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

  public List<Reservation> getMyReservations(int id) {
         req=new ConnectionRequest();
        String url = Statics.BASE_URL+"/reservation"+"/GetReservation";
        req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
  
       req.addArgument("id",+id+"");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations =parseReservation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
    
    
  
   public ArrayList<Reservation> parseReservation(String jsonText){
        try {
            reservations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> e : list){
                Reservation r = new Reservation();
                r.setType(e.get("type").toString());
                   r.setEtat(e.get("etat").toString());
                    r.setId((int) Float.parseFloat(e.get("id").toString()));
                   r.setNbr_place((int) Float.parseFloat(e.get("nbrPlace").toString()));
               try
       {     SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
              java.util.Date parsedd = formatt.parse(e.get("dateDebut").toString());
               System.out.println(parsedd);
                 r.setDate_debut(parsedd);
                  java.util.Date parsedf= formatt.parse(e.get("dateFin").toString());
                   java.util.Date parsedr = formatt.parse(e.get("dateReservation").toString());
                          r.setDate_fin(parsedf);
                      r.setDate_reservation(parsedr);
                     
       }
       catch(ParseException ep)
               {
                    System.out.println(ep);
               }

         reservations.add(r);
             
            }
            
            
        } catch (IOException ex) {
            
        }
        return reservations;
    }
  
public boolean addReservationVoy(int nb , voyageOrganise v, int idc , String modalite) {
    
      
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/reservation/addReservationVoy/";
 
       req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
       req.addArgument("nbplace",nb+"");
       req.addArgument("idclient",+idc+"");
       req.addArgument("idh",v.getIdVoy()+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                 val = new String(req.getResponseData());  
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        PaiementService ps = new PaiementService();
               Paiement p = new Paiement(modalite,nb*v.getPrix(),(int) Float.parseFloat(val),null);
                ps.addPaiement(p);
        return resultOK;
    }

public boolean addReservationVol(int nb , Vol v, int idc , String modalite) {
    
      
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/reservation/addReservationVol/";
 
       req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
       req.addArgument("nbplace",nb+"");
       req.addArgument("idclient",+idc+"");
       req.addArgument("idv",v.getId_vol()+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                 val = new String(req.getResponseData());  
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        PaiementService ps = new PaiementService();
               Paiement p = new Paiement(modalite,nb*v.getPrix(),(int) Float.parseFloat(val),null);
                ps.addPaiement(p);
        return resultOK;
    }
    
   public boolean verfierDatedispo(String dd,String df ,int idh) {
    
      
       System.out.println(dd);
        
       System.out.println(df);
       String url = Statics.BASE_URL + "/reservation/verifierdateH";
 
       req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
       req.addArgument("dated",dd);
       req.addArgument("datef",df);
       req.addArgument("idh",idh+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                 val = new String(req.getResponseData());  
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        testhebergement=new String(req.getResponseData());  
      
        return resultOK;
    }
    
    public boolean addHbergement(String dd,String df, Hebergement h, int idc , String modalite) throws ParseException {
    
      
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "/reservation/addReservationHeb/";
 
       req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
        req.addArgument("dated",dd);
       req.addArgument("datef",df);
       req.addArgument("idh",h.getReferance()+"");
          req.addArgument("idclient",+idc+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                 val = new String(req.getResponseData());  
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        PaiementService ps = new PaiementService();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedd = format.parse(dd);
              java.util.Date parseda = format.parse(df);
          long diffInMillies = Math.abs(parseda.getTime() - parsedd.getTime() );
             System.out.println(diffInMillies);
             long diff=0;
            diff= (int) (diffInMillies/(1000 * 60 * 60 * 24));
             System.out.println(diff);
              Float prixTT=diff* h.getPrix();
               Paiement p = new Paiement(modalite, prixTT,(int) Float.parseFloat(val),null);
                ps.addPaiement(p);
        return resultOK;
    }
    
    
        public boolean deleteReservation(int id){
           String url= Statics.BASE_URL+"/reservation/DeleteReservationmaisonJSON/";
        req.setUrl(url);
         req.setPost(false);
        System.out.println(url);
        req.addArgument("id",""+id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                                req.removeResponseListener(this);

                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
           
           
       }
            public boolean modifierReservationmaison(Reservation r) {
        String url = Statics.BASE_URL +"/reservation/UpdateReservationmaisonJSON/";
        req.setUrl(url);
           req.setUrl(url);
         req.setPost(false);
        System.out.println(url);
        req.addArgument("id",""+r.getId());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
        
    }
}
