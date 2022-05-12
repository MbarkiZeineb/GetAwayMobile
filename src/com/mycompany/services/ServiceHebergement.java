/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Hebergement;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author louay
 */
public class ServiceHebergement {

    public static ServiceHebergement instance = null;
    String json;
    private boolean resultat;
    public ArrayList<Hebergement> Categories;
    private MultipartRequest request;

    private ConnectionRequest req;

    public static ServiceHebergement getInstance() {
        if (instance == null) {
            instance = new ServiceHebergement();
        }
        return instance;
    }

    public ServiceHebergement() {
        req = new ConnectionRequest();
        request = new MultipartRequest();
    }

    public boolean addHeb(Hebergement heb) {
        try {
            String url = Statics.BASE_URL + "/hebergement/mobile/add?paye=" + heb.getPaye()
                    + "&adress=" + heb.getAdress()
                    + "&prix=" + heb.getPrix()
                    + "&description=" + heb.getDescription()
                    + "&photo=" + heb.getPhoto()
                    + "&dateStart=" + heb.getDateStart()
                    + "&dateEnd=" + heb.getDateEnd()
                    + "&contact=" + heb.getContact()
                    + "&nbrDetoile=" + heb.getNbrDetoile()
                    + "&nbrSuite=" + heb.getNbrSuite()
                    + "&nbrParking=" + heb.getNbrParking()
                    + "&modelCaravane=" + heb.getModelCaravane()
                    + "&idCateg=" + heb.getIdCateg()
                          + "&ido=" + heb.getOffreur();;
            System.out.println(url);
            req.setPost(true);

            req.setUrl(url);
            req.setFailSilently(true);

            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    System.out.println("action performed");

                    resultat = req.getResponseCode() == 200;
                    System.out.println(req.getResponseCode());
                    System.out.println(req.getRequestBody());
                    req.removeResponseListener(this);

                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultat;

    }

    public void editCategory(int id, Hebergement heb) {
        System.out.println(heb);
        String url = Statics.BASE_URL + "/hebergement/mobile/edit?reference=" + heb.getReference()
                + "&paye=" + heb.getPaye()
                + "&adress=" + heb.getAdress()
                + "&prix=" + heb.getPrix()
                + "&description=" + heb.getDescription()
                + "&photo=" + heb.getPhoto()
                + "&dateStart=" + heb.getDateStart()
                + "&dateEnd=" + heb.getDateEnd()
                + "&contact=" + heb.getContact()
                + "&nbrDetoile=" + heb.getNbrDetoile()
                + "&nbrSuite=" + heb.getNbrSuite()
                + "&nbrParking=" + heb.getNbrParking()
                + "&modelCaravane=" + heb.getModelCaravane();
          
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean deleteCategory(int id) {
         String url = Statics.BASE_URL + "/hebergement/mobile/delete?reference=" + id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultat = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }

    public ArrayList<Hebergement> parse(String jsonTxt) {
        try {
            Categories = new ArrayList<>();

            JSONParser parser = new JSONParser();

            Map<String, Object> CategoriesJSON;
            CategoriesJSON = parser.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
            List<Map<String, Object>> listeRec;
            listeRec = (List<Map<String, Object>>) CategoriesJSON.get("root");

            for (Map<String, Object> item : listeRec) {
                Hebergement cat = new Hebergement();

                float reference = Float.parseFloat(item.get("reference").toString());
                float prix = Float.parseFloat(item.get("prix").toString());
                float contact = Float.parseFloat(item.get("contact").toString());
                float nbrDetoile = Float.parseFloat(item.get("nbrDetoile").toString());
                float nbrSuite = Float.parseFloat(item.get("nbrSuite").toString());
                float nbrParking = Float.parseFloat(item.get("nbrParking").toString());
                cat.setReference((int) reference);
                cat.setPrix(prix);
                cat.setContact((int) contact);
                cat.setNbrDetoile((int) nbrDetoile);
                cat.setNbrParking((int) nbrParking);
                cat.setNbrSuite((int) nbrSuite);
                cat.setPaye(item.get("paye").toString());
                cat.setAdress(item.get("adress").toString());
                cat.setDescription(item.get("description").toString());
                cat.setPhoto(item.get("photo").toString());
                cat.setDateStart(item.get("dateStart").toString());
                cat.setDateEnd(item.get("dateEnd").toString());
                cat.setModelCaravane(item.get("modelCaravane").toString());
             
                Categories.add(cat);
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("aaa");
        }
        return Categories;

    }
    
    public ArrayList<Hebergement> affichageHebergementsOffreur(int id) {
           req=new ConnectionRequest();
                String url = Statics.BASE_URL+"/user/gethebbyido/"+id;
        req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
  
      
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Categories =parse(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  Categories;
    }

    public ArrayList<Hebergement> affichageHebergements() {
               req=new ConnectionRequest();
               String url = Statics.BASE_URL + "/hebergement/mobile/getAll";
        req.setUrl(url);
       req.setPost(false);
        System.out.println(url);
  
      
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Categories =parse(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  Categories;
    }
}
