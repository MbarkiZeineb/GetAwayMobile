/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Paiement;
import com.mycompany.entities.Reservation;
import com.mycompany.services.PaiementService;
import java.util.List;

/**
 *
 * @author Asus
 */
public class AfficherPaiementForm extends Form  {
  Form current;

    public AfficherPaiementForm(Resources res , int id  ) {
         current=this;
        PaiementService ps= new PaiementService();
         setTitle(" Paiment ");
        setLayout(BoxLayout.y());
         getContentPane().setScrollVisible(false);
          Container cnt= new Container(BoxLayout.y());
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new FormListReservation(res).show());
               Paiement pn= new Paiement();
               setLayout(BoxLayout.y());
                    pn=ps.getInstance().PaiementR(id);
        System.out.println(pn.getModalite());
             Label Montant = new Label("Montant: "+pn.getMontant());
           Label Modep =  new Label("Modalite de paiement : "+pn.getModalite());
          
          Label Datep =  new Label("Date de paiement : "+pn.getDate().toString());
          cnt.addAll(Montant,Modep,Datep);
           add(cnt);
        
        
         
        
    }
    
}
