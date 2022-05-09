/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Hebergement;
import com.mycompany.services.ServiceHebergement;
import com.mycompany.utils.CustomComboBox;
import com.mycompany.utils.Statics;
import java.text.SimpleDateFormat;

/**
 *
 * @author louay
 */
public class AddHebergement extends BaseForm {

    Form current;

    public AddHebergement(Resources res) {
        super("Newsfeed", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Add Hebergement");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {

        });

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe, s1, res.getImage("Logo.png"), "", "", res);

        //
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Hebergements", barGroup);
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
        partage.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

            new AddHebergement(res).show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        TextField pays = new TextField("", "Pays..");
        pays.setUIID("TextFieldBlack");
        addStringValue("Pays", pays);

        TextField adress = new TextField("", "Address..");
        adress.setUIID("TextFieldBlack");
        addStringValue("Address", adress);

        TextField prix = new TextField("", "Price..");
        prix.setUIID("TextFieldBlack");
        addStringValue("Price", prix);

        TextField description = new TextField("", "Description..");
        description.setUIID("TextFieldBlack");
        addStringValue("Description", description);

        TextField photo = new TextField("", "Photo..");
        photo.setUIID("TextFieldBlack");
        addStringValue("Photo", photo);

        Picker dateStart = new Picker();
        dateStart.setUIID("TextFieldBlack");
        dateStart.setType(Display.PICKER_TYPE_DATE);
        addStringValue("start date", dateStart);

        Picker dateEnd = new Picker();
        dateEnd.setUIID("TextFieldBlack");
        dateEnd.setType(Display.PICKER_TYPE_DATE);
        addStringValue("end date", dateEnd);

        TextField contact = new TextField("", "Contact..");
        contact.setUIID("TextFieldBlack");
        addStringValue("Contact", contact);

        TextField nbrDetoile = new TextField("", "Stars..");
        nbrDetoile.setUIID("TextFieldBlack");
        addStringValue("Stars", nbrDetoile);

        TextField nbrSuite = new TextField("", "Suits..");
        nbrSuite.setUIID("TextFieldBlack");
        addStringValue("Suits", nbrSuite);

        TextField nbrParking = new TextField("", "Parking..");
        nbrParking.setUIID("TextFieldBlack");
        addStringValue("Parking", nbrParking);

        TextField modelCaravane = new TextField("", "Model..");
        modelCaravane.setUIID("TextFieldBlack");
        addStringValue("Model", modelCaravane);

        CustomComboBox.myComboBox CategorieBox = CustomComboBox.createComboBox(Statics.BASE_URL + "/category/mobile/getAll", "id", "name", "id");
        addStringValue("Category", CategorieBox.Box);
        TextField categorie = new TextField("", "choose category!");
        categorie.setUIID("textFieldBlack");

        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);

        btnAjouter.addActionListener((e) -> {

            try {

                if (pays.getText().equals("") || adress.getText().equals("") || prix.getText().equals("") || description.getText().equals("") || photo.getText().equals("") || dateEnd.getText().equals("") || dateStart.getText().equals("") || contact.getText().equals("") || nbrDetoile.getText().equals("") || nbrSuite.getText().equals("") || nbrParking.getText().equals("") || modelCaravane.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();

                    final Dialog iDialog = ip.showInfiniteBlocking();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    Hebergement r = new Hebergement(
                            pays.getText(),
                            adress.getText(),
                            Float.valueOf(prix.getText()),
                            description.getText(),
                            photo.getText(),
                            format.format(dateStart.getDate()),
                            format.format(dateEnd.getDate()),
                            Integer.parseInt(contact.getText()),
                            Integer.parseInt(nbrDetoile.getText()),
                            Integer.parseInt(nbrSuite.getText()),
                            Integer.parseInt(nbrParking.getText()),
                            modelCaravane.getText(),
                            CategorieBox.getSelected().uniqueId
                    );

                    ServiceHebergement.getInstance().addHeb(r);

                    iDialog.dispose();
                    new ShowHebergement(res).show();
                    refreshTheme();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }

    private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }

        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        imageScale,
                        overLay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                                )
                        )
                );

        swipe.addTab("", res.getImage("back-logo.jpeg"), page1);

    }

    public void bindButtonSelection(Button btn, Label l) {

        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {

        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }

}
