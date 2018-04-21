package ru.vise.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Path("/formbuilder")
public class FormBuilder {
    @GET
    @Path("/form")
    public String BuildForm(@QueryParam("formname") String formname) {

//        return new Gson().toJson(BuildMapList());
        return readFromJson("/Users/maksimovvladislav/IdeaProjects/ViseFrontEnd/forms/SimpleEgo.json");
//        return "";
    }

    private String readFromJson(String fileName) {
//        String fileName = "D:\\Repository\\java vise\\ViseFrontEnd\\forms\\SimpleEgo.json";
        //String fileName = "SimpleEgo.json";
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            String json;
            json = scanner.useDelimiter("//advcdgv").next();
            return json;
        } catch (FileNotFoundException e) {
            return "File not found";
        }
    }



    private List<Map<String, String>> BuildMapList() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;

        // Create capital node
        map = new HashMap<String, String>();
        map.put("name", "capital");
        map.put("id", "initCapital");
        map.put("attr_id", "1");
        map.put("placeholder", "Initial capital...");
        map.put("title", "Initial Capital");
        map.put("type", "text");
        map.put("value", "10");
        map.put("label", "Initial Capital");
        list.add(map);

        // Create mu node
        map = new HashMap<String, String>();
        map.put("name", "mu");
        map.put("id", "mu");
        map.put("attr_id", "2");
        map.put("placeholder", "Mu...");
        map.put("title", "Mu");
        map.put("type", "text");
        map.put("value", "0.1");
        map.put("label", "Mu");
        list.add(map);

        // Create select variation node
        map = new HashMap<String, String>();
        map.put("name", "var");
        map.put("id", "var");
        map.put("attr_id", "9");
        map.put("title", "Variation");
        map.put("label", "Variation");
        map.put("options", "mu,sigma");
        map.put("inputType", "select");
        list.add(map);


        return list;

    }

}
