/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Malek
 */
public class AvionForm extends Form  {
    public AvionForm(Resources res) {
        super( new BorderLayout());
        Toolbar tb=new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("Liste Avion");
       getContentPane().setScrollVisible(false);
        
        Button btngerer = new Button("Afficher avions");
add(BorderLayout.CENTER,btngerer);

         Button btnajouter = new Button("Ajouter avion");
add(BorderLayout.SOUTH,btnajouter);

 btngerer.addActionListener((e) -> {
            
            new AfficherAvionForm(res).show();
        });
       btnajouter.addActionListener((e) -> {
            new AjouterAvion(res).show();
        });
    }
    
}
