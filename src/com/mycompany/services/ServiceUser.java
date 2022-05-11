/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.AfficherAvionForm;
import com.mycompany.gui.AjoutReclamationForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.utils.Statics;
import java.util.Map;

import java.util.Vector;

/**
 *
 * @author asus
 */
public class ServiceUser {

    public static ServiceUser instance = null;
    public static boolean resultOk = true;
    private ConnectionRequest req;

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public ServiceUser() {
        req = new ConnectionRequest();
    }

    public void signup(TextField nom, TextField prenom, TextField email, TextField password, ComboBox<String> roles, TextField numtel, Resources res) {

        String url = Statics.BASE_URL + "/user/inscription?nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&email=" + email.getText().toString()
                + "&password=" + password.getText().toString() + "&role=" + roles.getSelectedItem().toString() + "&numtel=" + numtel.getText().toString();

        req.setUrl(url);
        if (nom.getText().equals("") && prenom.getText().equals("") && email.getText().equals("") && password.getText().equals("") && numtel.getText().equals("")) {
            Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
        }
        req.addResponseListener((e) -> {
            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.println("data===>" + responseData);
        }
        );

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void signin(TextField email, TextField password, Resources rs) {

        String url = Statics.BASE_URL + "/user/signin?email=" + email.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    //Session 
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager.setId((int) id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i

                    SessionManager.setPassowrd(user.get("password").toString());
                    SessionManager.setUserName(user.get("nom").toString());
                    SessionManager.setEmail(user.get("email").toString());
                     SessionManager.setRole(user.get("role").toString());

                    System.out.println("current user==>" + SessionManager.getEmail() + "," + SessionManager.getPassowrd() + "," + SessionManager.getUserName()+","+SessionManager.getRole());
                    if (user.size() > 0) // l9a user
                                     // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    { System.out.println(SessionManager.getRole());
                        if (SessionManager.getRole().equals("Agent-Aerien")) 
                            new AfficherAvionForm(rs).show();
                         else
                            new AjoutReclamationForm(rs).show();
                        
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    String json;

    public String getPasswordByEmail(String email, Resources rs) {

        String url = Statics.BASE_URL + "/user/getPasswordByEmail?email=" + email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            json = new String(req.getResponseData()) + "";

            try {

                System.out.println("data ==" + json);

                Map<String, Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;
    }

}
