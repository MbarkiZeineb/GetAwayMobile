/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.list.GenericListCellRenderer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author louay
 */
public class CustomComboBox {

    private static Map<String, Object> createListEntry(String name, String desc) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", desc);

        return entry;
    }

    public static myComboBox createComboBox(String link, String titleField, String descField, String uniqueField) {
        myComboBox theBox = new myComboBox();
        ConnectionRequest r = new ConnectionRequest();
        r.setPost(false);
        r.setUrl(link);
        NetworkManager.getInstance().addToQueueAndWait(r);
        Map<String, Object> result;
        ComboBox<Map<String, Object>> combo = new ComboBox<>();
        theBox.Box = combo;
        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            System.out.println(result);
            ArrayList<Object> response = (ArrayList<Object>) result.get("root");
            System.out.println(response);

            int x;

            // iterating over an array
            for (Object loisir : response) {

                ObjectMapper oMapper = new ObjectMapper();
                Map<String, Object> myObjMap = oMapper.convertValue(loisir, Map.class);
                // accessing each element of array
                Map<String, Object> obj = createListEntry(myObjMap.get(titleField).toString(), myObjMap.get(descField).toString());
                theBox.uniqueId.add((new Double((double) myObjMap.get(uniqueField)).intValue()));
                combo.addItem(obj);
                System.out.print(loisir + " ");

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));

        return theBox;
    }

    public static class myComboBox {

        public class myComboBoxElement {

            public String title;
            public String Description;
            public int uniqueId;

            myComboBoxElement(String t, String d, int id) {
                title = t;
                Description = d;
                uniqueId = id;
            }
        }

        public ComboBox<Map<String, Object>> Box;
        List<Integer> uniqueId = new ArrayList<>();

        public myComboBoxElement getSelected() {
            if (Box.getSelectedItem() == null) {
                return null;
            }
            return new myComboBoxElement(Box.getSelectedItem().get("Line1").toString(), Box.getSelectedItem().get("Line2").toString(), uniqueId.get(Box.getSelectedIndex()));
        }

    }
}
