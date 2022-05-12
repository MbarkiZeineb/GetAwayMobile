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
import com.mycompany.entities.Activite;
import com.mycompany.services.ActiviteService;
import java.util.List;

/**
 *
 * @author Malek
 */
public class AfficherActiviteForm extends BaseForm{
 Form current;
    public AfficherActiviteForm(Resources res) {
 
              Button show = new Button("Etat des Activites");
         setTitle("Liste des Activites");
              setLayout(BoxLayout.y());
         Container cnt= new Container(BoxLayout.y());
         
             
         Container cnt4 =new Container(BoxLayout.xCenter());
        
         
         ActiviteService as = new ActiviteService();
          cnt4.add(show);
          add(cnt4);
         
         show.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        as.help();
        System.out.println(as.getHelmessage());
      Dialog.show("Etat des activites",as.getHelmessage(),"","OK");
    }
});
          current = this ;
          super.addSideMenu(res);
         List<Activite> list=ActiviteService.getInstance().getMyactivite();
         Container cnt3= new Container(BoxLayout.y());
        for(Activite a:list){
            
            
            
           
        Container cnt2= new Container(BoxLayout.x());
        Label img= new Label(res.getImage(a.getImage()), "NewsTopLine2");
        cnt2.add(img);
        
            Label nom=new Label("Nom Activite: "+a.getNom(), "NewsTopLine2");
            Label descrip =new Label("Description: "+ a.getDescrip(), "NewsTopLine2");
            Label Duree =new Label("Duree : "+ a.getDuree(), "NewsTopLine2");
            Label Nbrplace =new Label("Nombre de Place: "+ a.getNbrPlace(), "NewsTopLine2");
            Label Date =new Label("Date: "+ a.getDate(), "NewsTopLine2");
            Label Type =new Label("Type: "+ a.getType(), "NewsTopLine2");
            Label Location =new Label("Location: "+ a.getLocation(), "NewsTopLine2");
            Label prix =new Label("Prix : "+ a.getPrix(), "NewsTopLine2");
             Button btnajouter = new Button("Ajouter Avis");
            cnt.addAll(cnt2,nom,descrip,Nbrplace,Date,Type,Location,prix,btnajouter);
 btnajouter.addActionListener((e) -> {
            new AjouterAvis(res,a).show();
        });
            
                     
    }   
                  
                 addAll(cnt);
                 
      
     
    
}
    }
    

