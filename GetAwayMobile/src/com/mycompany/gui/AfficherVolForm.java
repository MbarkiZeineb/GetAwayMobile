/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Vol;
import com.mycompany.services.VolService;
import java.util.List;

/**
 *
 * @author Malek
 */
public class AfficherVolForm extends Form{
 Form current;
    public AfficherVolForm(Resources res) {
 
         setTitle("Liste Des vols");
              setLayout(BoxLayout.y());
         Container cnt= new Container(BoxLayout.y());
         Button btnmap = new Button("map");
             add(btnmap);
            btnmap.addActionListener((evt) -> {
                         
                       new MapForm(res,current);
                     });
            
          
          current = this ;
         List<Vol> list=VolService.getInstance().getMyvols();
         Container cnt3= new Container(BoxLayout.y());
        for(Vol p:list){
            
            
            
           
        Container cnt2= new Container(BoxLayout.x());
        Label lbi= new Label(res.getImage("ff.png"));
        cnt2.add(lbi);
        
         
            
            Label nomVol=new Label("Num vol : "+p.getNum_vol(), "NewsTopLine2");
            Label nbrplace =new Label("Nbr place dispo : "+ p.getNbr_placedispo(), "NewsTopLine2");
            Label villedepart =new Label("Ville Depart : "+ p.getVille_depart(), "NewsTopLine2");
            Label villearrivee =new Label("Ville Arrivee : "+ p.getVille_arrivee(), "NewsTopLine2");
            Label datedep =new Label("Date Depart : "+ p.getDate_depart(), "NewsTopLine2");
            Label datearr =new Label("Date Arrivee : "+ p.getDate_arrivee(), "NewsTopLine2");
            Label prix =new Label("Prix : "+ p.getPrix(), "NewsTopLine2");
           
             cnt.addAll(cnt2,nomVol,nbrplace,villedepart,villearrivee,datedep,datearr,prix);
             
             
             
                     
    }   
                  
                 addAll(cnt);
                 
      
     
    
}
    }
    

