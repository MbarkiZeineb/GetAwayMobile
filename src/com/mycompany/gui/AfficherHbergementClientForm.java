/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
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
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Hebergement;
import com.mycompany.services.ServiceHebergement;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class AfficherHbergementClientForm extends BaseForm{
    
    Form current;

    public AfficherHbergementClientForm(Resources res) {
         setTitle("Liste Des reservation ");
        setLayout(BoxLayout.y());
         getContentPane().setScrollVisible(false);
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev->{});

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();


        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
  
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Hebergement", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Add", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

            new ShowHebergement(res).show();
            refreshTheme();
        });
       

        ArrayList< Hebergement> list = ServiceHebergement.getInstance().affichageHebergements();

        for (Hebergement rec : list) {

            addButton(rec, res);
        }

    }

    

    private void addButton(Hebergement rec, Resources res) {

        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth() / 2, this.getWidth() / 5, 0xffff0000), true);
        URLImage background = URLImage.createToStorage(placeholder, rec.getPhoto(), rec.getPhoto());
        background.fetch();
        ImageViewer image = new ImageViewer(background);
        Label reference = new Label("reference : " + rec.getReference(), "NewsTopLine2");
        Label paye = new Label("pays : " + rec.getPaye(), "NewsTopLine2");
        Label adress = new Label("address : " + rec.getAdress(), "NewsTopLine2");
        Label prix = new Label("price : " + rec.getPrix(), "NewsTopLine2");
        Label description = new Label("description : " + rec.getDescription(), "NewsTopLine2");
        Label photo = new Label("photo : " + rec.getPhoto(), "NewsTopLine2");
        Label dateStart = new Label("Start Date : " + rec.getDateStart(), "NewsTopLine2");
        Label dateEnd = new Label("End Date : " + rec.getDateEnd(), "NewsTopLine2");
        Label contact = new Label("contact : " + rec.getContact(), "NewsTopLine2");
        Label nbrDetoile = new Label("nbr Stars : " + rec.getNbrDetoile(), "NewsTopLine2");
        Label nbrSuite = new Label("nbr Suits : " + rec.getNbrSuite(), "NewsTopLine2");
        Label nbrParking = new Label("nbr Parking : " + rec.getNbrParking(), "NewsTopLine2");
        Label modelCaravane = new Label("model : " + rec.getModelCaravane(), "NewsTopLine2");
              Button bnt = new Button("reserver");
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);

       
        //click delete icon
    
     
        Container post = BoxLayout.encloseY(
                GridLayout.encloseIn(2, reference, paye),
                GridLayout.encloseIn(2, adress, prix),
                GridLayout.encloseIn(2, dateStart, dateEnd),
                GridLayout.encloseIn(2, description, modelCaravane),
                GridLayout.encloseIn(2, contact, nbrDetoile),
                GridLayout.encloseIn(2, nbrParking, nbrSuite), GridLayout.encloseIn(1,bnt));
    
        Container pub = BoxLayout.encloseY(
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                image
                        )
                ),//.add(BorderLayout.WEST, pubImage),
                BoxLayout.encloseY(post)
        );
         bnt.addActionListener(e->
                         {
                             new ReserverHebergementForm(res,rec).show();
                         }
                 
                 );

        pub.getStyle().setFgColor(0xffffff);
        pub.getStyle().setBgColor(0xadd8e6);
        pub.getStyle().setBgTransparency(255);
        pub.getStyle().setPadding(7, 7, 7, 7);
        pub.getStyle().setMargin(20, 20, 30, 30);
        pub.getStyle().setBorder(Border.createRoundBorder(50, 50));

        add(pub);
    }
    
}
