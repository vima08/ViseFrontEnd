package ru.vise.dao.services;

import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;

import java.util.Set;

public interface ObjectService {

    void deleteByObjectId(Long objectId);

    Set<ParamEntity> getAllByObjectId(Long objectId);

    ObjectEntity findByObjectId(Long objectId);

    void addParams(Set<ParamEntity> paramEntities, Long objectId);

    ObjectEntity save(ObjectEntity entity);

}
