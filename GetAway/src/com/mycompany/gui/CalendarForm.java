/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import static com.codename1.io.Log.e;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.company.entities.Reservation;
import com.mycompany.servies.ReservationService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class CalendarForm extends Form {
     private CalendarForm current;
     private Container events;
    public CalendarForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public CalendarForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        setLayout(BoxLayout.y());
        setScrollableY(true);
        getContentPane().setScrollVisible(false);
        getToolbar().setUIID("Container");
        Button b = new Button(" ");
        b.setUIID("Container");
        getToolbar().setTitleComponent(b);
        getTitleArea().setUIID("Container");
          ReservationService rs = new ReservationService();
         List<Reservation> list=ReservationService.getInstance().getMyReservations(6);
        //installSidemenu(resourceObjectInstance);
       Map<Integer,Container> cntm =new HashMap<>();
             current = this;
        events = new Container();
     gui_Calendar_1.setTwoDigitMode(true);
    
         for(Reservation r:list){
               
          
                 Picker p = new Picker();
        b.addActionListener(e -> {
            p.pressed(); 
            p.released();
        });
                cntm.put(r.getId(),createEntry(resourceObjectInstance, true, r.getDate_debut().toString(), r.getDate_fin().toString(), r.getType()));
        //gui_Calendar_1.setCurrentDate(p.getDate());
//            gui_Calendar_1.setDate(p.getDate());
            gui_Calendar_1.highlightDate(r.getDate_debut(),""+r.getId());
            	 
             
        p.setFormatter(new SimpleDateFormat("MMMM"));
        p.setDate(p.getDate());
        p.setUIID("CalendarDateTitle");
          
        Container cnt = BoxLayout.encloseY(
                p,
                new Label("Calendar Reservation","")
                
        );
         
        //add(createEntry(resourceObjectInstance, false, r.getDate_debut().toString(), r.getDate_fin().toString(), r.getType(), "", "", "", "", ""));
//        add(createEntry(resourceObjectInstance, true, "12:20", "13:20", "Taco Bell", "Lunch", "Detra Mcmunn, Ami Koehler", "contact-b.png", "contact-c.png"));
//        add(createEntry(resourceObjectInstance, false, "16:15", "17:10", "3B, 2nd Floor", "Design Meeting", "Bryant Ford, Ami Koehler, Detra Mcmunn", "contact-a.png"));
        
        BorderLayout bl = (BorderLayout)gui_Calendar_1.getLayout();
 Component combos = bl.getNorth();
     
        gui_Calendar_1.replace(combos, cnt, null);
       
        }
         gui_Calendar_1.addActionListener(e -> {
              
  
        
         Container cnt1 = new  Container(BoxLayout.y());
                         
           this.removeComponent(events);
           events.removeAll();
           events = new Container();
            
         for(Reservation f:list){
            
                  SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-MM-dd");
                  System.out.println(dt1.format(f.getDate_debut()));
                   System.out.println(dt1.format(gui_Calendar_1.getDate()));
          if(dt1.format(f.getDate_debut()).equals(dt1.format(gui_Calendar_1.getDate())))
          {  
          
        events.add(cntm.get(f.getId()));
         
              
          }
           
         }
       add(events);
       //add(createEntry(resourceObjectInstance, false, p.getDate().toString(), r.getDate_fin().toString(), r.getType(), "", "", "", "", ""));
       
        });
        
    }

    private Container createEntry(Resources res, boolean selected, String startTime, String endTime, String title) {
        Component time = new Label(startTime, "CalendarHourUnselected");
        if(selected) {
            time.setUIID("CalendarHourSelected");
        }
        
        Container circleBox = BoxLayout.encloseY(new Label(res.getImage("label_round-selected.png")),
                new Label("-", "OrangeLine"),
                new Label("-", "OrangeLine")
        );
        
        Container cnt = new Container(BoxLayout.x());
       
        Container mainContent = BoxLayout.encloseY(
                BoxLayout.encloseX(
                        new Label("-", "SmallThinLabel"), 
                        new Label(startTime, "SmallThinLabel"), 
                        new Label("-", "SmallThinLabel"),
                        new Label(endTime, "SmallThinLabel")),
               
                cnt
        );
           Label redLabel = new Label("", "RedLabelRight");
        FontImage.setMaterialIcon(redLabel, FontImage.MATERIAL_LABEL_IMPORTANT);
        Container loc = BoxLayout.encloseY(
                redLabel,
                new Label("Type:", "TinyThinLabelRight"),
                new Label(title, "TinyBoldLabel")
        );
   
    
        
        
        mainContent= BorderLayout.center(mainContent).
                add(BorderLayout.WEST, circleBox);
        
        return BorderLayout.center(mainContent).
                add(BorderLayout.WEST, FlowLayout.encloseCenter(time)).
                  add(BorderLayout.EAST, loc);
               
    }
    
//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Calendar gui_Calendar_1 = new com.codename1.ui.Calendar();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.GridLayout(2, 1));
        setTitle("");
        setName("CalendarForm");
        addComponent(gui_Calendar_1);
        gui_Calendar_1.setName("Calendar_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    protected boolean isCurrentCalendar() {
        return true;
    }

    @Override
    protected void initGlobalToolbar() {
        setToolbar(new Toolbar(true));
    }

    
}
