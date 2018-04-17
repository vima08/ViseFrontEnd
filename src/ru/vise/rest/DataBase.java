package ru.vise.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Session;
import ru.vise.entities.AttributeEntity;
import ru.vise.entities.ListValueEntity;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;
import ru.vise.utils.HibernateSessionFactory;

import javax.swing.*;
import javax.ws.rs.*;
import java.lang.reflect.Type;
import java.util.*;

@Path("/database")
public class DataBase {
    @GET
    @Produces("text/plain")
    @Path("/generate")
    public String getGenerateObject(){
        ObjectEntity objectEntity = new ObjectEntity();
        objectEntity.setName("Testing");
        objectEntity.setDescription(String.format("%tc", new Date()));

        Session session = null;
        Integer objectId = new Integer(0);
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            objectId = (Integer) session.save(objectEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return objectId.toString();
    }

    @POST
    @Path("/saveForm")
    public void getInputObj(@FormParam("data") String data, @FormParam("objId") String objectId){
        Type itemsMapType = new TypeToken<Map<Integer, String>>() {}.getType();
        Map<Integer, String> mapDataForm = new Gson().fromJson(data, itemsMapType);
        Iterator<Integer> mapDataFormIter = mapDataForm.keySet().iterator();

        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();

//            ObjectEntity objectEntity = new ObjectEntity();
//            objectEntity.setName("Testing");
//            objectEntity.setDescription(String.format("%tc", new Date()));
//            Integer objId = (Integer) session.save(objectEntity);
            ObjectEntity objectEntity = session.find(ObjectEntity.class, Integer.parseInt(objectId));

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

    @POST
    @Path("/saveResult")
    public void saveResult(@FormParam("result") String result, @FormParam("objId") String objId){
        Type itemsListType = new TypeToken<List<String[]>>() {}.getType();
        List<String[]> listResult = new Gson().fromJson(result, itemsListType);

//        Type itemsArrType = new TypeToken<String[]>() {}.getType();
//        String[] arrItemsDes = new Gson().fromJson(result, itemsArrType);
//        List<String> listResult = new ArrayList<>();
//
//        for (int i = 0; i < arrItemsDes.length; i++) {
//            listResult.add(arrItemsDes[i]);
//        }

        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();

            ObjectEntity objectEntity = session.find(ObjectEntity.class, Integer.parseInt(objId));

            ParamEntity paramEntityAbs = new ParamEntity();
            paramEntityAbs.setObjectsByObjectId(objectEntity);

            ListValueEntity listValueEntityAbs = new ListValueEntity();
            listValueEntityAbs.setValue(Arrays.toString(listResult.get(0)));
            listValueEntityAbs.setNumber(listResult.get(0).length);
            listValueEntityAbs.setAttributesByAttrId(session.find(AttributeEntity.class,15));
            Integer listValueAbsId = (Integer)session.save(listValueEntityAbs);

            objectEntity.getParamsOfObject().add(paramEntityAbs);
            paramEntityAbs.setAttributesByAttrId(session.find(AttributeEntity.class,15));
            paramEntityAbs.setValue(listValueAbsId.toString());
            session.save(paramEntityAbs);
            session.update(objectEntity);

            for (int i = 1; i < listResult.size(); i++) {
                ParamEntity paramEntity = new ParamEntity();
                paramEntity.setObjectsByObjectId(objectEntity);

                ListValueEntity listValueEntity = new ListValueEntity();
                listValueEntity.setValue(Arrays.toString(listResult.get(i)));
                listValueEntity.setNumber(listResult.get(i).length);
                listValueEntity.setAttributesByAttrId(session.find(AttributeEntity.class,14));
                Integer listValueId = (Integer)session.save(listValueEntity);

                paramEntity.setAttributesByAttrId(session.find(AttributeEntity.class, 14));
                paramEntity.setValue(listValueId.toString());
                objectEntity.getParamsOfObject().add(paramEntity);
                session.save(paramEntity);
                session.update(objectEntity);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @POST
    @Produces("text/plain")
    @Path("/getParam")
    public String getParamExp(@FormParam("expId") String expId){
        HashMap<String, String> attrAndValue = new HashMap<>();
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();

            ObjectEntity experiment = session.find(ObjectEntity.class, Integer.parseInt(expId));
            Set<ParamEntity> paramsOfExp = experiment.getParamsOfObject();

            Iterator<ParamEntity> iterator = paramsOfExp.iterator();
            while (iterator.hasNext()){
                ParamEntity paramEntity = iterator.next();
                attrAndValue.put(String.valueOf(paramEntity.getAttributesByAttrId().getAttrId()), paramEntity.getValue());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return new Gson().toJson(attrAndValue);
    }
}
