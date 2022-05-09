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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Category;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author louay
 */
public class ServiceCategory {

    public static ServiceCategory instance = null;
    String json;
    private boolean resultat;
    public ArrayList<Category> Categories;
    private MultipartRequest request;

    private final ConnectionRequest req;

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;
    }

    public ServiceCategory() {
        req = new ConnectionRequest();
        request = new MultipartRequest();
    }

    public boolean addCategory(TextField name) {
        try {
            String url = Statics.BASE_URL + "/category/mobile/add?name=" + name.getText().toString();
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

    public void editCategory(int id, TextField name) {
        String url = Statics.BASE_URL + "/category/mobile/edit?id=" + id + "&name=" + name.getText();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean deleteCategory(int id) {
        String url = Statics.BASE_URL + "/category/mobile/delete?id=" + id;

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

    public ArrayList<Category> parse(String jsonTxt) {
        try {
            Categories = new ArrayList<>();

            JSONParser parser = new JSONParser();

            Map<String, Object> CategoriesJSON;
            CategoriesJSON = parser.parseJSON(new CharArrayReader(jsonTxt.toCharArray()));
            List<Map<String, Object>> listeRec;
            listeRec = (List<Map<String, Object>>) CategoriesJSON.get("root");

            for (Map<String, Object> item : listeRec) {
                Category cat = new Category();

                float id = Float.parseFloat(item.get("id").toString());
                cat.setIdCateg((int) id);
                cat.setNomCateg(item.get("name").toString());
                Categories.add(cat);
            }
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("aaa");
        }
        return Categories;

    }

    public ArrayList<Category> affichageCategorie() {
        request = new MultipartRequest();
        ArrayList<Category> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/category/mobile/getAll";
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

    public ArrayList<Category> showAll() {
        ArrayList<Category> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/category/mobile/getAll";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapProducts = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapProducts.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Category cat = new Category();

                        float id = Float.parseFloat(obj.get("id").toString());

                        String name = obj.get("name").toString();
                        String description = obj.get("description").toString();

                        cat.setIdCateg((int) id);
                        cat.setNomCateg(name);

                        result.add(cat);

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }
}
