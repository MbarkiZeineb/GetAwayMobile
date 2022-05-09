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
import com.mycompany.entities.Avion;
import com.mycompany.services.AvionService;

/**
 *
 * @author Malek
 */
public class AjouterAvion extends Form{
Form current;
    public AjouterAvion(Resources res) {
        
        super("Newsfeed",BoxLayout.y());
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Avion");
        getContentPane().setScrollVisible(false);
        
        
     
       
        TextField nbr_place = new TextField("", "Nombre de place");
        TextField nom_avion= new TextField("", "Nom Avion");
        
         Button ajouter =new  Button("Ajouter");
       
       Label ajout1=new Label("ajout");
       
      
     
        
Container content = BoxLayout.encloseY(
                nbr_place,
                nom_avion,
                ajouter
        );
        content.setScrollableY(true);
        
        setScrollableY(true);
        add( content);

ajouter.addActionListener(e->{ 
          if(verifnum(nbr_place.getText())&&verifstring(nom_avion.getText()))
         {
             Avion ann=new Avion();
         
        
       ann.setNom_avion(nom_avion.getText());
       ann.setNbr_place((int)Float.parseFloat(nbr_place.getText()));
      
      
      
        
             AvionService sa=new AvionService();
          
            sa.AjouterAvion(ann);
            Dialog.show("Ajout", "Ajout éffectué avec succés", "ok", null);
             removeAll();
            //  new MesPropresHebergementss(res).show();
        
         }
         else{
            Dialog.show("erreur", "veuillez remplir tous les champs", "ok",null);
        }
       

            new AfficherAvionForm(res).show();
        
         });
                                    
    }

    public  boolean verifstring(String entry)
    {         if (entry.equals(""))
        return false; 
 
         return true;   }
 
public  boolean verifnum(String entry)
    {
        if (entry.equals(""))
        {return false; }
        if(entry.toLowerCase().equals(entry.toUpperCase()))
        {
            return true;
        }
        else{
            return false;
        }
    }
   
    
}
