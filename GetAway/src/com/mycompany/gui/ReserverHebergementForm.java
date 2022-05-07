/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.company.entities.Hebergement;
import com.company.entities.voyageOrganise;
import com.mycompany.servies.ReservationService;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Asus
 */
public class ReserverHebergementForm extends BaseForm {
ReservationService rs = new ReservationService();
    public ReserverHebergementForm(Resources res) {
     
     super("Ajouter",new FlowLayout(CENTER, CENTER));
    setTitle("Reservation Hebeergement");
     int id=15;
        Picker dated = new Picker();
         Picker datef= new Picker();
              super.addSideMenu(res);
   ComboBox<String> comboModalite= new ComboBox<String>("Cache" ,"Cheque","Carte bancaire");
     Button btnreserverH = new Button("reserver");
    Container cnt = new Container(BoxLayout.y());
      cnt.addAll(dated,datef,comboModalite,btnreserverH);
      btnreserverH.addActionListener(
       e->{
               
              rs.verfierDatedispo((new SimpleDateFormat("yyyy-MM-dd")).format(dated.getDate()),(new SimpleDateFormat("yyyy-MM-dd")).format(datef.getDate()),id);
             String test= rs.testhebergement;
             if(test.equals("true"))
             {   Hebergement hb = new Hebergement();
             hb.setReferance(id);
            hb.setPrix(850);
                  try {
                      rs.addHbergement((new SimpleDateFormat("yyyy-MM-dd")).format(dated.getDate()),(new SimpleDateFormat("yyyy-MM-dd")).format(datef.getDate()), hb, SessionManager.getId(),comboModalite.getSelectedItem());
                  } catch (ParseException ex) {
                      System.out.println(ex);
                  }
                 
                   new FormListReservation(res).show();
             }
             else
             {
                  ToastBar.showInfoMessage(" les dates selectionees ne sont pas  disponible ").show();
             }
       
       }
      );
      
        add( cnt);

      


           
    }
    
    
}
