/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Asus
 */
public class BaseAgent  extends Form{
    
       public BaseAgent() {
    }

    public BaseAgent(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseAgent(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
   Toolbar tb = getToolbar();
 Image img = res.getImage("profile-background.jpg");
     if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
        img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
     }
     ScaleImageLabel sl = new ScaleImageLabel(img);
     sl.setUIID("BottomPad");
     sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
      
       tb.addMaterialCommandToSideMenu("Avion ", FontImage.MATERIAL_SETTINGS, e -> new AfficherAvionForm(res).show());
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new WalkthruForm(res).show());
    }
    
}
