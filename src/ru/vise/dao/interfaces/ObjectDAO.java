package ru.vise.dao.interfaces;

import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;

import java.util.Set;

public interface ObjectDAO {
    void deleteObject(int objectId);
    void updateObject(ObjectEntity objectEntity);
    int addObject(ObjectEntity objectEntity);
    Set<ParamEntity> getAllParam(int objectId);
    ObjectEntity getObject(int objectId);
    ObjectEntity getObject(ObjectEntity objectEntity);
    void addParams(Set<ParamEntity> paramEntities, int objectId);
}
