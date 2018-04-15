package ru.vise.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Session;
import ru.vise.entities.AttributeEntity;
import ru.vise.entities.ListValueEntity;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;
import ru.vise.utils.HibernateSessionFactory;

import javax.swing.*; //TODO Art Не используй импорт звёздочкой в диффах, не будет видно добавление нового класса
import javax.ws.rs.*; //TODO Art
import java.lang.reflect.Type;
import java.util.*; //TODO Art

@Path("/database")
public class DataBase { //TODO Art DataBaseWebService
    @GET
    @Produces("text/plain")
    @Path("/generate")
    public String getGenerateObject(){ //TODO Art Выпиливай тестовые методы - это мусор. В крайнем случае выноси их в Unit тесты
        ObjectEntity objectEntity = new ObjectEntity();
        objectEntity.setName("Testing");
        objectEntity.setDescription(String.format("%tc", new Date()));

        Session session = null;
        Integer objectId = new Integer(0);
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession(); //TODO Art Нафиг сессию и транзакции из вебсервиса, вынести в спец. класс в managers пакет
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
    public void getInputObj(@FormParam("data") String data, @FormParam("objId") String objectId){ //TODO Art Заупутывающее название
        Type itemsMapType = new TypeToken<Map<Integer, String>>() {}.getType(); //TODO Art не надо создавать переменную, если используешь 1 раз. new Gson().fromJson(data, new TypeToken ...)
        Map<Integer, String> mapDataForm = new Gson().fromJson(data, itemsMapType); //TODO Art mapDataForm -> formDataMap
        Iterator<Integer> mapDataFormIter = mapDataForm.keySet().iterator(); //TODO Art Iter - Iterator не надо экономить символы в ущерб читаемости

        Session session = null; //TODO Art
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession(); //TODO Art
            session.beginTransaction(); //TODO Art

//            ObjectEntity objectEntity = new ObjectEntity(); //TODO Art Выпиливай если не нужно, а если планируешь использовать, то оставляй
//// todo с описанием того, как будешь использовать и когда планируешь сделать
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
    public void saveResult(@FormParam("result") String result, @FormParam("objId") String objId){ //TODO Art Сэйв того туда - не экономим символы
        Type itemsListType = new TypeToken<List<String[]>>() {}.getType(); //TODO Art
        List<String[]> listResult = new Gson().fromJson(result, itemsListType); //TODO Art listResult -> resultList

//        Type itemsArrType = new TypeToken<String[]>() {}.getType(); //TODO Art Выпиливай
//        String[] arrItemsDes = new Gson().fromJson(result, itemsArrType);
//        List<String> listResult = new ArrayList<>();
//
//        for (int i = 0; i < arrItemsDes.length; i++) {
//            listResult.add(arrItemsDes[i]);
//        }

        Session session = null; //TODO Art
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession(); //TODO Art
            session.beginTransaction(); //TODO Art

            ObjectEntity objectEntity = session.find(ObjectEntity.class, Integer.parseInt(objId));

            ParamEntity paramEntityAbs = new ParamEntity();
            paramEntityAbs.setObjectsByObjectId(objectEntity);

            ListValueEntity listValueEntityAbs = new ListValueEntity();
            listValueEntityAbs.setValue(Arrays.toString(listResult.get(0))); //TODO Art Выноси магические константы в топ класса, pvivate static Integer BAL_BLA = 0
            listValueEntityAbs.setNumber(listResult.get(0).length); //TODO Art
            listValueEntityAbs.setAttributesByAttrId(session.find(AttributeEntity.class,15)); //TODO Art
            Integer listValueAbsId = (Integer)session.save(listValueEntityAbs);

            objectEntity.getParamsOfObject().add(paramEntityAbs);
            paramEntityAbs.setAttributesByAttrId(session.find(AttributeEntity.class,15)); //TODO Art
            paramEntityAbs.setValue(listValueAbsId.toString());
            session.save(paramEntityAbs);
            session.update(objectEntity);

            for (int i = 1; i < listResult.size(); i++) {
                ParamEntity paramEntity = new ParamEntity();
                paramEntity.setObjectsByObjectId(objectEntity);

                ListValueEntity listValueEntity = new ListValueEntity();
                listValueEntity.setValue(Arrays.toString(listResult.get(i)));
                listValueEntity.setNumber(listResult.get(i).length);
                listValueEntity.setAttributesByAttrId(session.find(AttributeEntity.class,14)); //TODO Art
                Integer listValueId = (Integer)session.save(listValueEntity);

                paramEntity.setAttributesByAttrId(session.find(AttributeEntity.class, 14)); //TODO Art
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
}
