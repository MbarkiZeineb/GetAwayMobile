/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.company.entities.Reservation;
import com.company.entities.voyageOrganise;
import com.mycompany.servies.ReservationService;
import com.mycompany.servies.voyageOrganiseService;
import java.util.List;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author Asus
 */
public class AjouterVoyageOForm   extends BaseForm {
  
    Form current;
    public AjouterVoyageOForm(Resources res,voyageOrganise p ) {
    
     super("Ajouter",new FlowLayout(CENTER, CENTER));
     
        current = this ; 
      super.addSideMenu(res);
    setTitle("Reservation voyage organise  ");
   TextField tnbreplace = new TextField("","Nombres de places",20, TextField.ANY);
      tnbreplace.setUIID("TextFieldBlack");
   ComboBox<String> comboModalite= new ComboBox<String>("Cache" ,"Cheque","Carte bancaire");
     Button btnreservervoy = new Button("reserver");
    Container cnt;
        cnt = BoxLayout.encloseY(
                tnbreplace,createStatusBar(), comboModalite,createStatusBar(),btnreservervoy,createStatusBar()
        );
           cnt.setScrollableY(true);
        setScrollableY(true);
        add( cnt);

       System.out.println("zz"+p);
         btnreservervoy.addActionListener(
        
        (ActionEvent e)->{
          int nb=((int)Float.parseFloat(tnbreplace.getText()));
            System.out.println(nb+"jj"+p.getNbrPlace());
          if(   p.getNbrPlace() >= nb )
          {
              ReservationService rs= new ReservationService();
              rs.addReservationVoy(nb,p,6,comboModalite.getSelectedItem());
               new FormListReservation(res).show();
          }
          else
          { 
                ToastBar.showInfoMessage("il n'y a pas de place disponible. il reste seulement"+p.getNbrPlace()+"places").show();
          }
        
        
        }
        );


           
    }
   

   
    
   
   
    

}

    
    
    

