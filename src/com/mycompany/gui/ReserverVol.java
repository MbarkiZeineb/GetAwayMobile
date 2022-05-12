/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Vol;
import com.mycompany.entities.voyageOrganise;
import com.mycompany.services.ReservationService;

/**
 *
 * @author Asus
 */
public class ReserverVol extends BaseForm {
  Form current;
    public ReserverVol(Resources res ,Vol p) {
         super("Ajouter",new FlowLayout(CENTER, CENTER));
     
        current = this ; 
      super.addSideMenu(res);
    setTitle("Reservation voyage organise  ");
   TextField tnbreplace = new TextField("","Nombres de places",20, TextField.ANY);
      tnbreplace.setUIID("TextFieldBlack");
   ComboBox<String> comboModalite= new ComboBox<String>("","Cache" ,"Cheque","Carte bancaire");
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
        
            if(!tnbreplace.getText().equals("") &&  onlyDigits(tnbreplace.getText(),tnbreplace.getText().length()) && ! comboModalite.getSelectedItem().equals("") )
            {  int nb=((int)Float.parseFloat(tnbreplace.getText()));
            System.out.println(nb+"jj"+p.getNbr_placedispo());
          if(   p.getNbr_placedispo() >= nb )
          { 
              ReservationService rs= new ReservationService();
              rs.addReservationVol(nb,p,SessionManager.getId(),comboModalite.getSelectedItem());
               new FormListReservation(res).show();
          }
          else
          { 
                ToastBar.showInfoMessage("il n'y a pas de place disponible. il reste seulement"+p.getNbr_placedispo()+"places").show();
          }
            }
            else
            {
               ToastBar.showInfoMessage("Verifier vos champs ").show(); 
            }
        
        }
        );


           
    }
   
 public boolean onlyDigits(String str, int n)
    {
        // Traverse the string from
        // start to end
        for (int i = 0; i < n; i++) {
  
            // Check if character is
            // digit from 0-9
            // then return true
            // else false
            if (str.charAt(i) >= '0'
                && str.charAt(i) <= '9') {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    
    
}
