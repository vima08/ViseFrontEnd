package ru.vise.rest;

import javax.swing.*; //TODO Max
import javax.ws.rs.*; //TODO Max

import altr.BackEnd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Session;

import ru.vise.entities.AttributeEntity;
import ru.vise.entities.ListValueEntity;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;
import ru.vise.utils.HibernateSessionFactory;
import sun.plugin.javascript.navig.Array;

import java.lang.reflect.Type;
import java.util.*; //TODO Max

// The Java class will be hosted at the URI path "/helloworld" //TODO Art Выпилить
@Path("/helloworld") //TODO Vlad Оцени, что по твоему делает этот вебсервис и переименуй его так чтобы было ясно, что он делает.
public class HelloWorld { //TODO Vlad XxxWebService
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain" //TODO Art Выпилить
    @Produces("text/plain")
    @Path("/plot")
    public String getClichedMessage(@QueryParam("test") Integer test, @QueryParam("q") String q, //TODO Art Переименовать, тест и ку - выпилить
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
        String json = new Gson().toJson(BackEnd.runSimpleEgo(capital, distrib, mu, sigma,
                k, iteration, stepNumber, peopleCount,
                majorityThreshold, start, finish, step, var));
        return json;
//        return new Gson().toJson(BackEnd.runSimpleEgo(10, "ParetoDistribution", 0.1, 1,
//                20, 100, 10, 101,
//                0.5, -250, 100, 10));
//        return "Hi";//TestGetter.get().toString() + test.toString() + " " + q;
    }

    @GET
    @Path("/percentage")
    public String getPercentage() { //TODO Art Должно быть ясно, проценты чего, из назавания
        return Double.toString(BackEnd.getPercentage());
    }

//    @GET //TODO Art Выпиливай ненужное - больше половины класса - мусор
//    @Produces("text/plain")
//    @Path("/generate")
//    public String getGenerateObject(){
//        ObjectEntity objectEntity = new ObjectEntity();
//        objectEntity.setName("Testing");
//        objectEntity.setDescription(String.format("%tc", new Date()));
//
//        Session session = null;
//        Integer objectId = new Integer(0);
//        try {
//            session = HibernateSessionFactory.getSessionFactory().openSession();
//            session.beginTransaction();
//            objectId = (Integer) session.save(objectEntity);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//        return objectId.toString();
//    }
//
//    @POST
//    @Path("/saveForm")
//    public void getInputObj(@FormParam("data") String data, @FormParam("objId") String objectId){
//        Type itemsMapType = new TypeToken<Map<Integer, String>>() {}.getType();
//        Map<Integer, String> mapDataForm = new Gson().fromJson(data, itemsMapType);
//        Iterator<Integer> mapDataFormIter = mapDataForm.keySet().iterator();
//
//        Session session = null;
//        try {
//            session = HibernateSessionFactory.getSessionFactory().openSession();
//            session.beginTransaction();
//
////            ObjectEntity objectEntity = new ObjectEntity();
////            objectEntity.setName("Testing");
////            objectEntity.setDescription(String.format("%tc", new Date()));
////            Integer objId = (Integer) session.save(objectEntity);
//            ObjectEntity objectEntity = session.find(ObjectEntity.class, Integer.parseInt(objectId));
//
//            while (mapDataFormIter.hasNext()){
//                ParamEntity paramEntity = new ParamEntity();
//
//                Integer attrId = mapDataFormIter.next();
//                AttributeEntity attributeEntity = session.find(AttributeEntity.class, attrId);
//                paramEntity.setAttributesByAttrId(attributeEntity);
//                attributeEntity.getParamEntities().add(paramEntity);
//
//                String formValue = mapDataForm.get(attrId);
//                paramEntity.setValue(formValue);
//
//                paramEntity.setObjectsByObjectId(objectEntity);
//                objectEntity.getParamsOfObject().add(paramEntity);
//
//                session.save(paramEntity);
//                session.update(attributeEntity);
//            }
//            session.save(objectEntity);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }
//
//    @POST
//    @Path("/saveResult")
//    public void saveResult(@FormParam("result") String result, @FormParam("objId") String objId){
//        Type itemsListType = new TypeToken<List<String[]>>() {}.getType();
//        List<String[]> listResult = new Gson().fromJson(result, itemsListType);
//
////        Type itemsArrType = new TypeToken<String[]>() {}.getType();
////        String[] arrItemsDes = new Gson().fromJson(result, itemsArrType);
////        List<String> listResult = new ArrayList<>();
////
////        for (int i = 0; i < arrItemsDes.length; i++) {
////            listResult.add(arrItemsDes[i]);
////        }
//
//        Session session = null;
//        try {
//            session = HibernateSessionFactory.getSessionFactory().openSession();
//            session.beginTransaction();
//
//            ObjectEntity objectEntity = session.find(ObjectEntity.class, Integer.parseInt(objId));
//
//            ParamEntity paramEntityAbs = new ParamEntity();
//            paramEntityAbs.setObjectsByObjectId(objectEntity);
//
//            ListValueEntity listValueEntityAbs = new ListValueEntity();
//            listValueEntityAbs.setValue(Arrays.toString(listResult.get(0)));
//            listValueEntityAbs.setNumber(listResult.get(0).length);
//            listValueEntityAbs.setAttributesByAttrId(session.find(AttributeEntity.class,15));
//            Integer listValueAbsId = (Integer)session.save(listValueEntityAbs);
//
//            objectEntity.getParamsOfObject().add(paramEntityAbs);
//            paramEntityAbs.setAttributesByAttrId(session.find(AttributeEntity.class,15));
//            paramEntityAbs.setValue(listValueAbsId.toString());
//            session.save(paramEntityAbs);
//            session.update(objectEntity);
//
//            for (int i = 1; i < listResult.size(); i++) {
//                ParamEntity paramEntity = new ParamEntity();
//                paramEntity.setObjectsByObjectId(objectEntity);
//
//                ListValueEntity listValueEntity = new ListValueEntity();
//                listValueEntity.setValue(Arrays.toString(listResult.get(i)));
//                listValueEntity.setNumber(listResult.get(i).length);
//                listValueEntity.setAttributesByAttrId(session.find(AttributeEntity.class,14));
//                Integer listValueId = (Integer)session.save(listValueEntity);
//
//                paramEntity.setAttributesByAttrId(session.find(AttributeEntity.class, 14));
//                paramEntity.setValue(listValueId.toString());
//                objectEntity.getParamsOfObject().add(paramEntity);
//                session.save(paramEntity);
//                session.update(objectEntity);
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }
}