package ru.vise.dao.services;

import ru.vise.entities.AttributeEntity;

public interface AttributeService {

    void deleteByAttrId(Long attrId);

    AttributeEntity findByAttrId(Long attrId);

    AttributeEntity save(AttributeEntity attributeEntity);
}
