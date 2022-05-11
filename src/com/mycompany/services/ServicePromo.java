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
import com.mycompany.entities.Promostion;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author louay
 */
public class ServicePromo {

    public static ServicePromo instance = null;
    String json;
    private boolean resultat;
    public ArrayList<Promostion> Categories;
    private MultipartRequest request;

    private final ConnectionRequest req;

    public static ServicePromo getInstance() {
        if (instance == null) {
            instance = new ServicePromo();
        }
        return instance;
    }

    public ServicePromo() {
        req = new ConnectionRequest();
        request = new MultipartRequest();
    }

    public boolean addPromo(Promostion heb) {
        try {
            String url = Statics.BASE_URL + "/promostion/mobile/add?pourcentage=" + heb.getPourcentage()
                    + "&dateStart=" + heb.getDateStart()
                    + "&dateEnd=" + heb.getDateEnd()
                    + "&refHebergement=" + heb.getRefHebergement();
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

    public void editPromo(int id, Promostion heb) {
        System.out.println(heb);
        String url = Statics.BASE_URL + "/promostion/mobile/edit?idRef=" + id
                + "&pourcentage=" + heb.getPourcentage()
                + "&dateStart=" + heb.getDateStart()
                + "&dateEnd=" + heb.getDateEnd();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean deletePromo(int id) {
        String url = Statics.BASE_URL + "/promostion/mobile/delete?idRef=" + id;
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

    public ArrayList<Promostion> parse(String jsonTxt) {
        try {
            Categories = new ArrayList<>();

            JSONParser parser = new JSONParser();

            Map<String, Object> CategoriesJSON;
            CategoriesJSON = parser.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
            List<Map<String, Object>> listeRec;
            listeRec = (List<Map<String, Object>>) CategoriesJSON.get("root");

            for (Map<String, Object> item : listeRec) {
                Promostion cat = new Promostion();

                float idRef = Float.parseFloat(item.get("idRef").toString());
                float pourcentage = Float.parseFloat(item.get("pourcentage").toString());
                cat.setIdRef((int) idRef);
                cat.setPourcentage((int) pourcentage);
                cat.setDateStart(item.get("dateStart").toString());
                cat.setDateEnd(item.get("dateEnd").toString());
                Categories.add(cat);
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("aaa");
        }
        return Categories;

    }

    public ArrayList<Promostion> affichagePromo() {
        request = new MultipartRequest();
        String url = Statics.BASE_URL + "/promostion/mobile/getAll";
        request.setUrl(url);
        System.out.println("hello2");

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    System.out.println("hello3");

                    String dataaa = new String(request.getResponseData(), "utf-8");
                    System.out.println("our dataaa " + dataaa);
                    Categories = parse(dataaa);
                    request.removeResponseListener(this);
                } catch (Exception ex) {

                }
            }

        });
        System.out.println("hello4");

        NetworkManager.getInstance().addToQueueAndWait(request);
        return Categories;
    }
}
