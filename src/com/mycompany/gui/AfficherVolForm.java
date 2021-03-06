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
public class AfficherVolForm extends BaseForm{
 Form current;
 int i=0;
    public AfficherVolForm(Resources res,int test) {
 i=test;
         setTitle("Liste Des vols");
              setLayout(BoxLayout.y());
         Container cnt= new Container(BoxLayout.y());
         super.addSideMenu(res);
         Button btnmap = new Button("map");
             add(btnmap);
            btnmap.addActionListener((evt) -> {
                         
                       new MapForm(res,current);
                     });
            
            Button BUTTriePrix = new Button("Trie selon Prix ");
        //BUTTriePrix.addActionListener((evt) -> new AfficherVolForm(res).show());
         addAll(BUTTriePrix);
          BUTTriePrix.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                  VolService as = new VolService();
                       
                        as.order_By_PrixJSON();
                
                           new AfficherVolForm(res,1).show();
                    }     });
              
         
          
          current = this ;
          
          if(i==0)
          {List<Vol> list=VolService.getInstance().getMyvols();
          
         Container cnt3= new Container(BoxLayout.y());
        for(Vol p:list){
            
            
           
           
        Container cnt2= new Container(BoxLayout.xCenter());
        Label lbi= new Label(res.getImage("ff.png"));
        cnt2.add(lbi);
        
         
            
           
            Label nbrplace =new Label("Nbr place dispo : "+ p.getNbr_placedispo(), "NewsTopLine2");
            Label villedepart =new Label("Ville Depart : "+ p.getVille_depart(), "NewsTopLine2");
            Label villearrivee =new Label("Ville Arrivee : "+ p.getVille_arrivee(), "NewsTopLine2");
            Label datedep =new Label("Date Depart : "+ p.getDate_depart(), "NewsTopLine2");
            Label datearr =new Label("Date Arrivee : "+ p.getDate_arrivee(), "NewsTopLine2");
            Label prix =new Label("Prix : "+ p.getPrix(), "NewsTopLine2");
               Button bnt = new Button("reserver");
             cnt.addAll(cnt2,nbrplace,villedepart,villearrivee,datedep,datearr,prix,bnt);
             
                bnt.addActionListener(e->
                         {
                             new ReserverVol(res, p).show();
                         }
                 
                 );
             
                     
    }   
                  
                 addAll(cnt);}
          if(i==1)
          {List<Vol> list=VolService.getInstance().order_By_PrixJSON();
          
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
               Button bnt = new Button("reserver");
             cnt.addAll(cnt2,nomVol,nbrplace,villedepart,villearrivee,datedep,datearr,prix,bnt);
             
                bnt.addActionListener(e->
                         {
                             new ReserverVol(res, p).show();
                         }
                 
                 );
             
                     
    }   
                  
                 addAll(cnt);
              
          }
                 
      
     
    
}
    }
    

