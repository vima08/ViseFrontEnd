package ru.vise.dao.interfaces;


import ru.vise.entities.ListValueEntity;

public interface ListValueDAO {
    void deleteListValue(int listValueId);
    void updateListValue(ListValueEntity listValueEntity);
    void addListValue(ListValueEntity listValueEntity);
}
