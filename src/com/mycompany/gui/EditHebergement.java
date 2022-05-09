/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Hebergement;
import com.mycompany.services.ServiceHebergement;

/**
 *
 * @author louay
 */
public class EditHebergement extends BaseForm {

    Form current;

    public EditHebergement(Resources res, Hebergement r) {
        super("Newsfeed", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Edit Hebergement");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        TextField pays = new TextField(r.getPaye(), "Pays..");
        pays.setUIID("TextFieldBlack");

        TextField adress = new TextField(r.getAdress(), "Address..");
        adress.setUIID("TextFieldBlack");

        TextField prix = new TextField(String.valueOf(r.getPrix()), "Price..");
        prix.setUIID("TextFieldBlack");

        TextField description = new TextField(r.getDescription(), "Description..");
        description.setUIID("TextFieldBlack");

        Picker dateStart = new Picker();
        dateStart.setUIID("TextFieldBlack");
        dateStart.setType(Display.PICKER_TYPE_DATE);

        Picker dateEnd = new Picker();
        dateEnd.setUIID("TextFieldBlack");
        dateEnd.setType(Display.PICKER_TYPE_DATE);

        TextField contact = new TextField(String.valueOf(r.getContact()), "Contact..");
        contact.setUIID("TextFieldBlack");

        TextField nbrDetoile = new TextField(String.valueOf(r.getNbrDetoile()), "Stars..");
        nbrDetoile.setUIID("TextFieldBlack");

        TextField nbrSuite = new TextField(String.valueOf(r.getNbrSuite()), "Suits..");
        nbrSuite.setUIID("TextFieldBlack");

        TextField nbrParking = new TextField(String.valueOf(r.getNbrParking()), "Parking..");
        nbrParking.setUIID("TextFieldBlack");

        TextField modelCaravane = new TextField(r.getModelCaravane(), "Model..");
        modelCaravane.setUIID("TextFieldBlack");

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer
        btnModifier.addPointerPressedListener(l -> {
            ServiceHebergement.getInstance().editCategory(r.getReference(), r);
            new ShowHebergement(res).show();
        });
        Button btnAnnuler = new Button("Cancel");
        btnAnnuler.addActionListener(e -> {
            new ShowHebergement(res).show();
        });

        Label l2 = new Label("");

        Label l3 = new Label("");

        Label l4 = new Label("");

        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(pays),
                new FloatingHint(adress),
                new FloatingHint(prix),
                new FloatingHint(description),
                dateStart,
                dateEnd,
                new FloatingHint(contact),
                new FloatingHint(nbrDetoile),
                new FloatingHint(nbrSuite),
                new FloatingHint(nbrParking),
                new FloatingHint(modelCaravane),
                createLineSeparator(),
                createLineSeparator(),
                btnModifier,
                btnAnnuler
        );

        add(content);
        show();

    }
}
