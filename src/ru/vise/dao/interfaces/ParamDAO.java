package ru.vise.dao.interfaces;


import ru.vise.entities.ParamEntity;

public interface ParamDAO {
    void deleteParam(int paramId);
    void updateParam(ParamEntity paramEntity);
    void addParam(ParamEntity paramEntity);
}
