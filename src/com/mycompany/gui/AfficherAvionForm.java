/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Avion;
import com.mycompany.services.AvionService;
import java.util.ArrayList;
import java.util.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Malek
 */
public class AfficherAvionForm extends BaseAgent{
    Form current;
     public AfficherAvionForm(Resources res) {
         Button btnajouter = new Button("Ajouter avion");
add(btnajouter);
 btnajouter.addActionListener((e) -> {
            new AjouterAvion(res).show();
        });
 
         setTitle("Liste Des avions ");
              setLayout(BoxLayout.y());
         Container cnt= new Container(BoxLayout.y());
         
           

        current = this ;
        super.addSideMenu(res);
         System.out.println(SessionManager.getId());
         List<Avion> list=AvionService.getInstance().getMyavions(SessionManager.getId());
         Container cnt3= new Container(BoxLayout.y());
        for(Avion p:list){
        Container cnt2= new Container(BoxLayout.x());
        Label lbi= new Label(res.getImage("f.png"));
        cnt2.add(lbi);
        
         Label Supprimer = new Label(" ");
        Supprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(Supprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0x0000C0);
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        Supprimer.setIcon(suprrimerImage);
        Supprimer.setTextPosition(RIGHT);
      
            
            Label place=new Label("Nombre de place : "+p.getNbr_place(), "NewsTopLine2");
            Label nomavion =new Label("Nom avion : "+ p.getNom_avion(), "NewsTopLine2");
            Button bnt = new Button("Supprimer");
             cnt.addAll(cnt2,place,nomavion,bnt);
              
            
             
                bnt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                 if(Dialog.show("Confirmation", "Are you Sure you Want to Delete ", "Yes", "Cancel")){
                        AvionService as = new AvionService();
                        as.deleteAvion(p.getId_avion());
                        System.out.println("delete avec succes ");
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Deleting ");
            status.setShowProgressIndicator(true);
           //status.setIcon(createIcon(FontImage.MATERIAL_DELETE));
            status.show();
            Display.getInstance().invokeAndBlock(()->{
                Util.sleep(2000);

            });
                 }    
                           new AfficherAvionForm(res).show();
                                    }
                });
               
                       
                 
                 
                 
    }   
                  
                 addAll(cnt);
                 
      
     
    
}
}
