/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.voyageOrganise;
import com.mycompany.services.voyageOrganiseService;

/**
 *
 * @author Amal Chibani
 */
public class ajoutVoyageForm extends BaseForm {
    
    
     Form current;
    public ajoutVoyageForm(Resources res) {
        
        super("Newsfeed",BoxLayout.y());
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout voyage");
        getContentPane().setScrollVisible(false);
        
       
        TextField villeDepart = new TextField("", "ville depart");
        TextField villeDest = new TextField("", "ville destination");
        
         Picker dateDepart = new Picker();
       

        Picker dateArrive = new Picker();
   
        
//        TextField dateDepart = new TextField("", "date depart");
//        TextField dateArrive = new TextField("", "date arrive");
        
        
        TextField nbrPlace= new TextField("", "nbr place");
        TextField idCat= new TextField("", "id categ");
        TextField prix= new TextField("", "prix");
        TextField description= new TextField("", "desc");

         Button ajouter =new  Button("Ajouter");
       
       Label ajout1=new Label("ajout");
       
      
     
        
Container content = BoxLayout.encloseY(
                villeDepart,
                villeDest,
                dateDepart,
                dateArrive,
                nbrPlace,
                idCat,
                prix,
                description,
                ajouter
        );
        content.setScrollableY(true);
        
        setScrollableY(true);
        add( content);

ajouter.addActionListener(e->{ 
          if(verifnum(nbrPlace.getText())&&verifstring(villeDepart.getText())&&verifstring(villeDest.getText())&&verifnum(idCat.getText())&&verifnum(prix.getText())&&verifstring(description.getText()))
         {
             voyageOrganise ann=new voyageOrganise();
         
        
             voyageOrganiseService sa=new voyageOrganiseService();
          String des=(new SimpleDateFormat("yyyy-MM-dd")).format(dateArrive.getDate());
       ann.setVilleDepart(villeDepart.getText());
       ann.setVilleDest(villeDest.getText());
       ann.setDateDepart((new SimpleDateFormat("yyyy-MM-dd")).format(dateDepart.getDate()));
       ann.setDateArrive(des);
       ann.setNbrPlace((int)Float.parseFloat(nbrPlace.getText()));
       ann.setIdCat((int)Float.parseFloat(idCat.getText()));
       ann.setPrix((int)Float.parseFloat(prix.getText()));
       ann.setDescription(description.getText());
      
            sa.ajoutvoy(ann);
            Dialog.show("Ajout", "Ajout éffectué avec succés", "ok", null);
             removeAll();
            //  new MesPropresHebergementss(res).show();
        
         }
         else{
            Dialog.show("erreur", "veuillez remplir tous les champs", "ok",null);
        }
       

            new AfficherVoyageForm(res).show();
        
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
private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
   
}
