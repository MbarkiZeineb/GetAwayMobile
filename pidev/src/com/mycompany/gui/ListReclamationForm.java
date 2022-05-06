/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.push.PushContent;
import com.codename1.ui.Form;
import com.codename1.ui.FontImage;
import com.mycompany.services.ServiceReclamation;
/**
 *
 * @author asus
 */
public class ListReclamationForm extends Form {

 
    public ListReclamationForm(Form previous) {
        setTitle("Liste des reclamations");
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceReclamation.getInstance().getAllrec().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
 
    }

  
   
    
    
    
}
