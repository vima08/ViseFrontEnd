package ru.vise.dao.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vise.dao.interfaces.ObjectDAO;
import ru.vise.dao.services.ObjectService;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;

import java.util.Set;

@Service
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    private ObjectDAO dao;

    @Transactional
    public void deleteByObjectId(Long objectId){
        dao.deleteByObjectId(objectId);
    }

    public Set<ParamEntity> getAllByObjectId(Long objectId){
        return dao.getAllByObjectId(objectId);
    }

    public ObjectEntity findByObjectId(Long objectId){
        return dao.findByObjectId(objectId);
    }

    public void addParams(Set<ParamEntity> paramEntities, Long objectId){
        ObjectEntity object = dao.findByObjectId(objectId);
        object.setParamsOfObject(paramEntities);
    }

    @Transactional
    public ObjectEntity save(ObjectEntity objectEntity) {
        return dao.save(objectEntity);

    }

}
