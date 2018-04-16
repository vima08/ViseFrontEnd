package ru.vise.dao.interfaces;


import ru.vise.entities.AttributeEntity;

public interface AttributeDAO {
    void deleteAttribute(int attrId);
    void updateAttribute(AttributeEntity attributeEntity);
    void addAttribute(AttributeEntity attributeEntity);
}
