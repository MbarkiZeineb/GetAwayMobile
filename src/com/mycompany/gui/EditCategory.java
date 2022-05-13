/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Category;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceCategory;

/**
 *
 * @author louay
 */
public class EditCategory extends BaseFormOff {

    Form current;

    public EditCategory(Resources res, Category r) {
        super("Newsfeed", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Edit Category");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        TextField name = new TextField(r.getNomCateg(), "Name");

        name.setUIID("NewsTopLine");

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer
        btnModifier.addPointerPressedListener(l -> {
            ServiceCategory.getInstance().editCategory(r.getIdCateg(), name);
            new ShowCategory(res).show();
        });
        Button btnAnnuler = new Button("Cancel");
        btnAnnuler.addActionListener(e -> {
            new ShowCategory(res).show();
        });

        Label l2 = new Label("");

        Label l3 = new Label("");

        Label l4 = new Label("");

        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(name),
                createLineSeparator(),
                createLineSeparator(),
                btnModifier,
                btnAnnuler
        );

        add(content);
        show();

    }
}
