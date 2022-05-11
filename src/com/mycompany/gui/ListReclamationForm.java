
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ListReclamationForm  extends BaseForm {
     Form current;
   
       public ListReclamationForm(Resources res) {
       

//        SpanLabel sp = new SpanLabel();
//        sp.setText(ReservationService.getInstance().getMyReservations().toString());
//        add(sp);
  super.addSideMenu(res);
         setTitle("Liste Des reclamations ");
        setLayout(BoxLayout.y());
          Container cnt= new Container(BoxLayout.y());
        current = this ;
        super.addSideMenu(res);
         List<Reclamation> list=ServiceReclamation.getInstance().getMyReclam(SessionManager.getId());
          Container cnt3= new Container(BoxLayout.y());
        for(Reclamation p:list){
        Container cnt2= new Container(BoxLayout.x());
        Label lbi= new Label(res.getImage("r.png"));
        cnt2.add(lbi);
             Label Supprimer = new Label(" ");
        Supprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(Supprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0x0000C0);
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        Supprimer.setIcon(suprrimerImage);
        Supprimer.setTextPosition(RIGHT);
        
         Label Modifier = new Label(" ");
        Modifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(Modifier.getUnselectedStyle());
        modifierStyle.setFgColor(0x0000C0);
      
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        Modifier.setIcon(mFontImage);
        Modifier.setTextPosition(LEFT);
            Label objet=new Label("Objet : "+p.getObjet());
            Label description =new Label("Description :"+ p.getDescription());
           Button bnt=new Button("supprimer");
                 cnt.addAll(cnt2,objet,description,bnt);
                 //  cnt.add(Supprimer);
                   
                 
               
                 bnt.addPointerReleasedListener(l -> {
            
                           System.out.println(p.getIdR());
                             
                            if(ServiceReclamation.getInstance().deletereclam(p.getIdR())) {
                                System.out.println("aaaaaaaaaaaaaa");
                                 ToastBar.showInfoMessage("Reclamation est supprime").show();
                                  
                               new ListReclamationForm(res).show();                    
                          }
                            
                        
                       
                
           
        });
//                       Modifier.addPointerReleasedListener(l -> {
//            
//           
//                        if(p.getEtat().equals("Approuve"))
//                        {      
//                            if(ReservationService.getInstance().modifierReservationmaison(p)) {
//                                  ToastBar.showInfoMessage("Reservation est supprime").show();
//                               new  FormListReservation(res).show();                    
//                          }
//                            
//                        }
//                        else
//                            {
//                                ToastBar.showInfoMessage("Modification impossible ").show();
//                            }
//                
//           
//        });
                
      
           
        }

            addAll(cnt);
             
  
      

					
}
 



}
