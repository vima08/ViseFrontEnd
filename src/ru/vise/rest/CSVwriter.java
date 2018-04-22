package ru.vise.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javenue.csv.Csv;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import static java.lang.Thread.sleep;

@Path("/csv")
public class CSVwriter {

    @GET
    @Path("/saveCSV")
    public String saveCSV(@QueryParam("data_for_csv") String res, @QueryParam("csvUrl") String url1) {

        Type itemsMapType = new TypeToken<Map<String, Double[]>>() {}.getType();
        Map<String, Double[]> mapData = new Gson().fromJson(res, itemsMapType);

        Set<String> keys = mapData.keySet();
        String[] array = keys.toArray(new String[keys.size()]);

        Date date = new Date();

        Long millis = date.getTime();


        String name = "result_" + millis.toString() + ".csv";

        String url = url1 + name;

        Csv.Writer writer;
        writer = new Csv.Writer(url).delimiter(';');


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
//       // try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return name;


    }
}