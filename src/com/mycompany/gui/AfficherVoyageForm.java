/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.company.entities.voyageOrganise;
import com.mycompany.servies.voyageOrganiseService;
import java.util.List;

/**
 *
 * @author Asus
 */
public class AfficherVoyageForm extends BaseForm {

    public AfficherVoyageForm(Resources res) {
             setTitle("Liste Des reservation ");
        setLayout(BoxLayout.y());
         getContentPane().setScrollVisible(false);
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->{});
         List<voyageOrganise> list=voyageOrganiseService.getInstance().getMyReservations();
        for(voyageOrganise p:list){
        Container cnt2= new Container(BoxLayout.x());
        Label lbi= new Label(res.getImage("r.png"));
        cnt2.add(lbi);
      
            Container cnt= new Container(BoxLayout.y());
            Label type=new Label("Date depart : "+p.getDateDepart());
            Label DateDebut =new Label("Date arrive :"+ p.getDateArrive());
             Label DateFin =new Label("Nombre de place :  "+ p.getNbrPlace());
                 Button bnt = new Button("reserver");
             Label etat =new Label("prix :  "+ p.getPrix());
                 cnt.addAll(type,DateDebut,DateFin,etat);
                  
                 add(cnt);
              
//               
//                 bnt.addActionListener(e->
//                         {
//                             new AjouterVoyageOForm(res,p).show();
//                         }
                 
//                 );
    }
     
    
}
}