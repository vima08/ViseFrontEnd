package ru.vise.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;

import java.util.Set;

public interface ObjectDAO extends JpaRepository<ObjectEntity, Long> {

    void deleteByObjectId(Long objectId);

    Set<ParamEntity> getAllByObjectId(Long objectId);

    ObjectEntity findByObjectId(Long objectId);

    ObjectEntity getObject(ObjectEntity objectEntity);

    void addParams(Set<ParamEntity> paramEntities, Long objectId);
}
