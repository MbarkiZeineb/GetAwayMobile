/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Avis;

import com.mycompany.services.AvisService;


/**
 *
 * @author Malek
 */
public class AjouterAvis extends Form{
Form current;
    public AjouterAvis(Resources res) {
        
        super("Newsfeed",BoxLayout.y());
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Avis");
        getContentPane().setScrollVisible(false);
        
        
        TextField message = new TextField("","Votre Avis");
        TextField rating = new TextField("", "Evaluer l'activite");
        
        
         Button ajouter =new  Button("Ajouter");
       
       Label ajout1=new Label("ajout");
       
      
     
        
Container content = BoxLayout.encloseY(
                message,
                rating,
                ajouter
        );
        content.setScrollableY(true);
        
        setScrollableY(true);
        add( content);

ajouter.addActionListener(e->{ 
//          if(verifnum(nbr_place.getText())&&verifstring(nom_avion.getText()))
//         {
             Avis av=new Avis();
         
       
       av.setId(73);
       av.setRefActivite(42);
       av.setMessage(message.getText());
       av.setRating((int)Float.parseFloat(rating.getText()));
      
      
      
        
             AvisService as=new AvisService();
          
            as.AjouterAvis(av);
            Dialog.show("Ajout", "Ajout éffectué avec succés", "ok", null);
             removeAll();
            //  new MesPropresHebergementss(res).show();
        
//         }
//         else{
//            Dialog.show("erreur", "veuillez remplir tous les champs", "ok",null);
//        }
       

            new AfficherAvisForm(res).show();
        
         });
                                    
    }

//    public  boolean verifstring(String entry)
//    {         if (entry.equals(""))
//        return false; 
// 
//         return true;   }
// 
//public  boolean verifnum(String entry)
//    {
//        if (entry.equals(""))
//        {return false; }
//        if(entry.toLowerCase().equals(entry.toUpperCase()))
//        {
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
   
    
}
