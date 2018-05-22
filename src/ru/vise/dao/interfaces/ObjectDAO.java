package ru.vise.dao.interfaces;

import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;

import java.util.Set;

public interface ObjectDAO {
    void deleteObject(int objectId);
    void updateObject(ObjectEntity objectEntity);
    int saveObject(ObjectEntity objectEntity);
    ObjectEntity createExperiment(int ExpId);
    ObjectEntity getObjectEntity(int id);
    void setObjectId(int newId, int oldId);
}
