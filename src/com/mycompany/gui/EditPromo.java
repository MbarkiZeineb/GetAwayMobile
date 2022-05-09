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
import com.mycompany.entities.Promostion;
import com.mycompany.services.ServicePromo;
import java.text.SimpleDateFormat;

/**
 *
 * @author louay
 */
public class EditPromo extends BaseForm {

    Form current;

    public EditPromo(Resources res, Promostion r) {
        super("Newsfeed", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Edit Promo");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        TextField pourcentage = new TextField(String.valueOf(r.getPourcentage()), "Pays..");
        pourcentage.setUIID("TextFieldBlack");

        Picker dateStart = new Picker();
        dateStart.setUIID("TextFieldBlack");
        dateStart.setType(Display.PICKER_TYPE_DATE);

        Picker dateEnd = new Picker();
        dateEnd.setUIID("TextFieldBlack");
        dateEnd.setType(Display.PICKER_TYPE_DATE);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer
        btnModifier.addPointerPressedListener(l -> {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Promostion pr = new Promostion(
                    r.getIdRef(),
                    Integer.parseInt(pourcentage.getText()),
                    format.format(dateStart.getDate()),
                    format.format(dateEnd.getDate()),
                    r.getRefHebergement()
            );
            ServicePromo.getInstance().editPromo(r.getIdRef(), r);
            new ShowPromo(res).show();
        });
        Button btnAnnuler = new Button("Cancel");
        btnAnnuler.addActionListener(e -> {
            new ShowPromo(res).show();
        });

        Label l2 = new Label("");

        Label l3 = new Label("");

        Label l4 = new Label("");

        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(pourcentage),
                dateStart,
                dateEnd,
                createLineSeparator(),
                createLineSeparator(),
                btnModifier,
                btnAnnuler
        );

        add(content);
        show();

    }
}
