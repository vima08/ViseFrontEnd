package ru.vise.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.vise.dao.FactoryDAO;
import ru.vise.entities.AttributeEntity;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;
import ru.vise.utils.HibernateSessionFactory;

import javax.swing.*;
import javax.ws.rs.*;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Path("/database")
public class DataBase {
    @GET
    @Produces("text/plain")
    @Path("/generate")
    public String getGenerateObject(@QueryParam("idExp") String idExp, @QueryParam("nameExp") String nameExp){
        ObjectEntity objectEntity = new ObjectEntity();
        objectEntity.setName(nameExp);
        ObjectEntity mainExp = FactoryDAO.getInstance().getObjectDAO().getObjectEntity(1);
        objectEntity.setObjectsByParentId(mainExp);
        objectEntity.setObjectId(Integer.parseInt(idExp));
        objectEntity.setDescription((new SimpleDateFormat("YYYY/MM/dd HH:mm:ss")).format(new Date()));

        int i = FactoryDAO.getInstance().getObjectDAO().saveObject(objectEntity);
        //FactoryDAO.getInstance().getObjectDAO().setObjectId(Integer.parseInt(idExp), i);
        return String.valueOf(i);
    }

    @POST
    @Path("/saveForm")
    public void getInputObj(@FormParam("data") String data, @FormParam("idExp") String objectId){
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

            ParamEntity paramTime = new ParamEntity();
            paramTime.setValue(DateFormat.getTimeInstance().format(new Date()));
            paramTime.setAttributesByAttrId(FactoryDAO.getInstance().getAttributeDAO().getAttributeEntity(22));
            paramTime.setObjectsByObjectId(objectEntity);
            session.save(paramTime);

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
    public void saveResult(@FormParam("result") String result,
                           @FormParam("idExp") String objId,
                           @FormParam("var") String var){
        Type itemsMapType = new TypeToken<Map<String, double[]>>() {}.getType();
        Map<String, double[]> mapOfResult = new Gson().fromJson(result, itemsMapType);

        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();

            ObjectEntity experimentObject = FactoryDAO.getInstance().getObjectDAO().getObjectEntity(Integer.parseInt(objId));

            Set<String> setKeyOfResult = mapOfResult.keySet();

            ObjectEntity resultObject = new ObjectEntity();
            resultObject.setObjectId((int) ((new Date()).getTime() % 1000000000));
            resultObject.setObjectsByParentId(session.find(ObjectEntity.class, 2));
            resultObject.setName("Result_" + DateFormat.getDateInstance().format(new Date()));
            resultObject.setDescription(DateFormat.getTimeInstance().format(new Date()));
            Integer idResultObject = (Integer) session.save(resultObject);


            ParamEntity paramRefOnResult = new ParamEntity();
            paramRefOnResult.setAttributesByAttrId(FactoryDAO.getInstance().getAttributeDAO().getAttributeEntity(14));
            paramRefOnResult.setValue(String.valueOf(idResultObject));
            paramRefOnResult.setObjectsByObjectId(experimentObject);//expObj set param

            session.save(paramRefOnResult);
            session.update(experimentObject);

            HashMap<String, AttributeEntity> mapAttrOfResult = new HashMap<>();
            Iterator<String> iterator = setKeyOfResult.iterator();
            while (iterator.hasNext()){
                String attrName = iterator.next();
                AttributeEntity attribute = FactoryDAO.getInstance().getAttributeDAO().getAttributeEntity(attrName, "point");
                mapAttrOfResult.put(attrName, attribute);
            }

            for (int i = 0; i < mapOfResult.get(mapOfResult.keySet().iterator().next()).length; i++) {
                Iterator<String> iteratorOfSetKeyResult = setKeyOfResult.iterator();
                ObjectEntity pointObject = new ObjectEntity();

//                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                pointObject.setObjectId((int) ((new Date()).getTime() % 2000000000)); // ID POIN!!!!
//                !!!!!!!!!!!!!!!!!!!!!!!!!


//                pointObject.setObjectId((int) ((new Date()).getTime() % 2000000000));
//                session.save(pointObject);

                ParamEntity paramVar = new ParamEntity();
                paramVar.setAttributesByAttrId(FactoryDAO.getInstance().getAttributeDAO().getAttributeEntity(18));
                paramVar.setValue(var);
                pointObject.setName("Point");
                pointObject.setDescription(DateFormat.getTimeInstance().format(new Date()));
                paramVar.setObjectsByObjectId(pointObject);
                session.save(paramVar);

                while (iteratorOfSetKeyResult.hasNext()){
                    ParamEntity paramEntity = new ParamEntity();
                    String nameAttribute = iteratorOfSetKeyResult.next();
                    AttributeEntity attributeEntity = mapAttrOfResult.get(nameAttribute);
                    double valueOfResult = mapOfResult.get(nameAttribute)[i];

                    paramEntity.setValue(String.valueOf(valueOfResult));
                    paramEntity.setAttributesByAttrId(attributeEntity);
                    paramEntity.setObjectsByObjectId(pointObject);
                    session.save(paramEntity);
                }

                pointObject.setObjectsByParentId(resultObject);
                session.save(pointObject);
                session.update(resultObject);
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

    @GET
    @Produces("text/plain")
    @Path("/getAllExp")
    public String getAllExp(){
        Session session = null;
        ArrayList<ObjectEntity> list = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
//            session.find()
            ObjectEntity mainExp = FactoryDAO.getInstance().getObjectDAO().getObjectEntity(1);
            Query query = session.createQuery("from ObjectEntity where objectsByParentId = :paramParent order by description");
            query.setParameter("paramParent", mainExp);
            list = (ArrayList<ObjectEntity>) query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return new Gson().toJson(list);
    }

//    @GET
//    @Produces("text/plain")
//    @Path("/getPoint")
//    public String getPointOfResult(@QueryParam("idResult") String idResult){
//        Session session = null;
//        try {
//            session = HibernateSessionFactory.getSessionFactory().openSession();
//            session.beginTransaction();
//
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//        return null;
//    }

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
            session.getTransaction().commit();
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
