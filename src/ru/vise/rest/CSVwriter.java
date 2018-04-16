package ru.vise.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javenue.csv.Csv;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

@Path("/csv")
public class CSVwriter {

    @POST
    @Path("saveCSV")
    public void saveCSV(@FormParam("res") String res) {

        Type itemsMapType = new TypeToken<Map<String, Double[]>>() {}.getType();
        Map<String, Double[]> mapData = new Gson().fromJson(res, itemsMapType);

        Set<String> keys = mapData.keySet();
        String[] array = keys.toArray(new String[keys.size()]);

        Csv.Writer writer;
        writer = new Csv.Writer(String.format("/Users/maksimovvladislav/Downloads/glassfish5/glassfish/domains/domain1/applications/__internal/ViseFrontEnd_war_exploded/result.csv")).delimiter(';');


        for (int i = -1; i < mapData.get(array[0]).length; i++) {
            if (i == -1) {
                for (int j = 0; j < keys.size(); j++) {
                    writer.value(array[j]);
                }
                writer.newLine();
            } else {
                for (int j = 0; j < keys.size(); j++) {
                    writer.value(Double.toString(mapData.get(array[j])[i]));
                }
                writer.newLine();
            }
        }

        writer.close();

    }
}