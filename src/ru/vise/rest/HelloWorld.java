package ru.vise.rest;

import javax.swing.*;
import javax.ws.rs.*;

import altr.BackEnd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Session;

import ru.vise.entities.AttributeEntity;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;
import ru.vise.utils.HibernateSessionFactory;

import java.lang.reflect.Type;
import java.util.*;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    @Path("/plot")
    public String getClichedMessage(@QueryParam("test") Integer test, @QueryParam("q") String q,
                                    @QueryParam("capital") Double capital,
                                    @QueryParam("mu") Double mu,
                                    @QueryParam("sigma") Double sigma,
                                    @QueryParam("k") Double k,
                                    @QueryParam("stepNumber") Integer stepNumber,
                                    @QueryParam("iteration") Integer iteration,
                                    @QueryParam("peopleCount") Integer peopleCount,
                                    @QueryParam("majorityThreshold") Double majorityThreshold,
                                    @QueryParam("var") String var,
                                    @QueryParam("distrib") String distrib,
                                    @QueryParam("start") Integer start,
                                    @QueryParam("finish") Integer finish,
                                    @QueryParam("step")Integer step,
                                    @QueryParam("multiple") Double multiple) {
        // Return some cliched textual content
        return new Gson().toJson(BackEnd.runSimpleEgo(capital, distrib, mu, sigma,
                k, iteration, stepNumber, peopleCount,
                majorityThreshold, start, finish, step, var));
//        return new Gson().toJson(BackEnd.runSimpleEgo(10, "ParetoDistribution", 0.1, 1,
//                20, 100, 10, 101,
//                0.5, -250, 100, 10));
//        return "Hi";//TestGetter.get().toString() + test.toString() + " " + q;
    }

    @GET
    @Path("/percentage")
    public String getPercentage() {
        return Double.toString(BackEnd.getPercentage());
    }

    @POST
    @Path("/save")
    public void getInputObj(@FormParam("data") String data){
        Type itemsMapType = new TypeToken<Map<Integer, String>>() {}.getType();
        Map<Integer, String> mapDataForm = new Gson().fromJson(data, itemsMapType);
        Iterator<Integer> mapDataFormIter = mapDataForm.keySet().iterator();

        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();

            ObjectEntity objectEntity = new ObjectEntity();
            objectEntity.setName("Testing");
            objectEntity.setDescription(String.format("%tc", new Date()));
//            Integer objId = (Integer) session.save(objectEntity);

            while (mapDataFormIter.hasNext()){
                ParamEntity paramEntity = new ParamEntity();

                Integer attrId = mapDataFormIter.next();
                AttributeEntity attributeEntity = session.find(AttributeEntity.class, attrId);
                paramEntity.setAttributesByAttrId(attributeEntity);
                attributeEntity.getParamEntities().add(paramEntity);

                String formValue = mapDataForm.get(attrId);
                paramEntity.setValue(formValue);

                paramEntity.setObjectsByObjectId(objectEntity);
                objectEntity.getParamsOfObject().add(paramEntity);

                session.save(paramEntity);
                session.update(attributeEntity);
            }
            session.save(objectEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}