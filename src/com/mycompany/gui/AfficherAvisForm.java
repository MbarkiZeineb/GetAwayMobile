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

import java.util.ArrayList;
import java.util.List;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.entities.Avis;
import com.mycompany.services.AvisService;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author Malek
 */
public class AfficherAvisForm extends BaseForm{
    Form current;
     public AfficherAvisForm(Resources res) {

 
         setTitle("Liste de vos avis ");
              setLayout(BoxLayout.y());
         Container cnt= new Container(BoxLayout.y());
         
           

        current = this ;
        super.addSideMenu(res);
         List<Avis> list=AvisService.getInstance().getMyavis(SessionManager.getId());
         Container cnt3= new Container(BoxLayout.y());
        
        for(Avis av:list){
        Container cnt2= new Container(BoxLayout.x());
        Label lbi= new Label(res.getImage("avis.png"));
        cnt2.add(lbi);
        
         Label Supprimer = new Label(" ");
        Supprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(Supprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0x0000C0);
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        Supprimer.setIcon(suprrimerImage);
        Supprimer.setTextPosition(RIGHT);
          
            Label name=new Label("Nom Client: "+av.getNomClient(), "NewsTopLine2");
            Label act=new Label("Nom Activite: "+av.getNomAct(), "NewsTopLine2");
            Label message=new Label("Message: "+av.getMessage(), "NewsTopLine2");
            Label date=new Label("Date: "+av.getDate(), "NewsTopLine2");
            Label rating=new Label("Rating: "+av.getRating(), "NewsTopLine2");
            
            Button bnt = new Button("Supprimer");
             cnt.addAll(cnt2,name,act,message,date,rating,bnt);
                         
                bnt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                 if(Dialog.show("Confirmation", "Confirmer la suppression? ", "Oui", "Annuler")){
                        AvisService as = new AvisService();
                        as.deleteAvis(av.getRefAvis());
                        System.out.println("Suppression réussie");
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Deleting ");
            status.setShowProgressIndicator(true);
           //status.setIcon(createIcon(FontImage.MATERIAL_DELETE));
            status.show();
            Display.getInstance().invokeAndBlock(()->{
                Util.sleep(2000);

            });
                 }    
                           new AfficherAvisForm(res).show();
                                    }
                });
               
                       
                 
                 
                 
    }   
                  
                 addAll(cnt);
                 
      
     
    
}
}