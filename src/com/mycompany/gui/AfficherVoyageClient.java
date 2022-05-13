/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.voyageOrganise;
import com.mycompany.services.voyageOrganiseService;
import java.util.List;
import javax.mail.Quota;

/**
 *
 * @author Asus
 */
public class AfficherVoyageClient extends BaseForm{

    public AfficherVoyageClient(Resources res) {
        
        
                
        Form current;
 
             setTitle("Liste Des voyages ");
        setLayout(BoxLayout.y());
         getContentPane().setScrollVisible(false);
      
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->{});
       current = this ;
        super.addSideMenu(res);
         List<voyageOrganise> list=voyageOrganiseService.getInstance().getMyVoyages();
         Container cnt3= new Container(BoxLayout.y());
        for(voyageOrganise p:list){
        Container cnt2= new Container(BoxLayout.x());
        Label lbi= new Label(res.getImage("voy.jpg"));
        cnt2.add(lbi);
      
         //supp
         Label Supprimer = new Label(" ");
        Supprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(Supprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0x0000C0);
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        Supprimer.setIcon(suprrimerImage);
        Supprimer.setTextPosition(RIGHT);
        
        //aff
            Container cnt= new Container(BoxLayout.y());
            Label numvoy =new Label("numvoy :  "+ p.getIdVoy());
            Label villeDep =new Label("VilleDepart :  "+ p.getVilleDepart());
            Label villedest =new Label("VilleDest :  "+ p.getVilleDest());
            Label datedepart=new Label("Date depart : "+p.getDateDepart());
            Label DateArrive =new Label("Date arrive :"+ p.getDateArrive());
             Label nbrplace =new Label("Nombre de place :  "+ p.getNbrPlace());
                
             Label prix =new Label("prix :  "+ p.getPrix());
             Label desc =new Label("description :  "+ p.getDescription());
             Button bnt = new Button("Reserver");
                 cnt.addAll(cnt2,numvoy,villeDep,villedest,datedepart,DateArrive,nbrplace,prix,desc,bnt);
                 ////////////////////////// 
               bnt.addActionListener(e->
                         {
                             new ReserverVoyageOForm(res,p).show();
                         }
                 
                 );
               
                       
              add(cnt);      
                 
                 
    }    
    }
    
    
    
    
}
