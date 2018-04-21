package ru.vise.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vise.WriteLog;
import ru.vise.dao.services.AttributeService;
import ru.vise.dao.services.ListValueService;
import ru.vise.dao.services.ObjectService;
import ru.vise.dao.services.ParamService;
import ru.vise.entities.AttributeEntity;
import ru.vise.entities.ListValueEntity;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Path("/database")
@Component
public class DataBase {

    @Autowired
    private ObjectService objectService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ParamService paramService;

    @Autowired
    private ListValueService listValueService;

    private WriteLog log = new WriteLog();

    @GET
    @Produces("text/plain")
    @Path("/generate")
    public String getGenerateObject(){
        log.write(log.ALL, "Testing autowiring" + paramService.hashCode());
        ObjectEntity objectEntity = new ObjectEntity();
        objectEntity.setName("Testing");
        objectEntity.setDescription(String.format("%tc", new Date()));
        objectEntity = objectService.save(objectEntity);
        log.write(log.ALL, "Saved successfully" + objectService.findByObjectId(objectEntity.getObjectId()));
        return objectEntity.getObjectId().toString();
    }

    @POST
    @Path("/saveToBase")
    public void saveResultAndForm(@FormParam("data") String data, @FormParam("result") String result, @FormParam("objId") String objId){
        Type itemsMapType = new TypeToken<Map<Integer, String>>() {}.getType();
        Map<Integer, String> mapDataForm = new Gson().fromJson(data, itemsMapType);
        Type itemsListType = new TypeToken<List<String[]>>() {}.getType();
        List<String[]> listResult = new Gson().fromJson(result, itemsListType);
        Iterator<Integer> mapDataFormIter = mapDataForm.keySet().iterator();
        ObjectEntity objectEntity = objectService.findByObjectId(Long.parseLong(objId));
        log.write(log.ALL, "Object entity's id: " + objectEntity.getObjectId());

        while (mapDataFormIter.hasNext()) {
            ParamEntity paramEntity = new ParamEntity();
            Integer attrId = mapDataFormIter.next();
            AttributeEntity attributeEntity = attributeService.findByAttrId(attrId.longValue());
            paramEntity.setAttributesByAttrId(attributeEntity);
            paramEntity.setValue(mapDataForm.get(attrId));
            paramEntity.setObjectsByObjectId(objectEntity);
            attributeEntity.getParamEntities().add(paramEntity);
            objectEntity.getParamsOfObject().add(paramEntity);
            log.write(log.ALL, "Param entitie's id: " + paramEntity.getParamId());
            log.write(log.ALL, "Attr entitie's id: " + attributeEntity.getAttrId());
            paramService.save(paramEntity);
            attributeService.save(attributeEntity);
        }

        ParamEntity paramEntityAbs = new ParamEntity();
        paramEntityAbs.setObjectsByObjectId(objectEntity);

        ListValueEntity listValueEntityAbs = new ListValueEntity();
        listValueEntityAbs.setValue(Arrays.toString(listResult.get(0)));
        listValueEntityAbs.setNumber(listResult.get(0).length);
        listValueEntityAbs.setAttributesByAttrId(attributeService.findByAttrId(15L));
        log.write(log.ALL, "id before saving : " + listValueEntityAbs.getListValueId());

        objectEntity.getParamsOfObject().add(paramEntityAbs);
        paramEntityAbs.setAttributesByAttrId(attributeService.findByAttrId(15L));
        paramEntityAbs.setValue(listValueService.save(listValueEntityAbs).toString());

        log.write(log.ALL, "List value's: " + listValueService.findByListValueId(listValueEntityAbs.getListValueId()));
        log.write(log.ALL, "List valie's value : " + listValueService.findByListValueId(listValueEntityAbs.getListValueId()).getValue());

        paramService.save(paramEntityAbs);
        objectService.save(objectEntity);

        for (int i = 1; i < listResult.size(); i++) {
            ParamEntity paramEntity = new ParamEntity();
            paramEntity.setObjectsByObjectId(objectEntity);

            ListValueEntity listValueEntity = new ListValueEntity();
            listValueEntity.setValue(Arrays.toString(listResult.get(i)));
            listValueEntity.setNumber(listResult.get(i).length);
            listValueEntity.setAttributesByAttrId(attributeService.findByAttrId(14L));

            paramEntity.setAttributesByAttrId(attributeService.findByAttrId(14L));
            paramEntity.setValue(listValueService.save(listValueEntity).toString());
            objectEntity.getParamsOfObject().add(paramEntity);
            paramService.save(paramEntity);
            objectService.save(objectEntity);
        }


    }

//    @POST
//    @Path("/saveForm")
//    public void getInputObj(@FormParam("data") String data, @FormParam("objId") String objectId) {
//        Type itemsMapType = new TypeToken<Map<Integer, String>>() {}.getType();
//        Map<Integer, String> mapDataForm = new Gson().fromJson(data, itemsMapType);
//        Iterator<Integer> mapDataFormIter = mapDataForm.keySet().iterator();
//        ObjectEntity objectEntity = objectService.findByObjectId(Long.parseLong(objectId));
//
//        while (mapDataFormIter.hasNext()) {
//            ParamEntity paramEntity = new ParamEntity();
//            Integer attrId = mapDataFormIter.next();
//            AttributeEntity attributeEntity = attributeService.findByAttrId(attrId.longValue());
//            paramEntity.setAttributesByAttrId(attributeEntity);
//            attributeEntity.getParamEntities().add(paramEntity);
//            String formValue = mapDataForm.get(attrId);
//            paramEntity.setValue(formValue);
//            paramEntity.setObjectsByObjectId(objectEntity);
//            objectEntity.getParamsOfObject().add(paramEntity);
//            paramService.save(paramEntity);
//            attributeService.save(attributeEntity);
//        }
//
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
//        ObjectEntity objectEntity = objectService.findByObjectId(Long.valueOf(objId));
//
//        ParamEntity paramEntityAbs = new ParamEntity();
//        paramEntityAbs.setObjectsByObjectId(objectEntity);
//
//        ListValueEntity listValueEntityAbs = new ListValueEntity();
//        listValueEntityAbs.setValue(Arrays.toString(listResult.get(0)));
//        listValueEntityAbs.setNumber(listResult.get(0).length);
//        listValueEntityAbs.setAttributesByAttrId(attributeService.findByAttrId(15L));
//
//        objectEntity.getParamsOfObject().add(paramEntityAbs);
//        paramEntityAbs.setAttributesByAttrId(attributeService.findByAttrId(15L));
//        paramEntityAbs.setValue(listValueService.save(listValueEntityAbs).toString());
//        paramService.save(paramEntityAbs);
//        objectService.save(objectEntity);
//
//        for (int i = 1; i < listResult.size(); i++) {
//            ParamEntity paramEntity = new ParamEntity();
//            paramEntity.setObjectsByObjectId(objectEntity);
//
//            ListValueEntity listValueEntity = new ListValueEntity();
//            listValueEntity.setValue(Arrays.toString(listResult.get(i)));
//            listValueEntity.setNumber(listResult.get(i).length);
//            listValueEntity.setAttributesByAttrId(attributeService.findByAttrId(14L));
//
//            paramEntity.setAttributesByAttrId(attributeService.findByAttrId(14L));
//            paramEntity.setValue(listValueService.save(listValueEntity).toString());
//            objectEntity.getParamsOfObject().add(paramEntity);
//            paramService.save(paramEntity);
//            objectService.save(objectEntity);
//        }
//
//    }

    @POST
    @Produces("text/plain")
    @Path("/getParam")
    public String getParamExp(@FormParam("expId") String expId){
        HashMap<String, String> attrAndValue = new HashMap<>();
        ObjectEntity experiment = objectService.findByObjectId(Long.valueOf(expId));
        Set<ParamEntity> paramsOfExp = experiment.getParamsOfObject();
        for (ParamEntity paramEntity : paramsOfExp) {
            attrAndValue.put(String.valueOf(paramEntity.getAttributesByAttrId().getAttrId()), paramEntity.getValue());
        }
        return new Gson().toJson(attrAndValue);
    }


}
