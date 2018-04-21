package ru.vise.dao.services;

import ru.vise.entities.ParamEntity;

public interface ParamService {

    ParamEntity findByParamId(Long id);

    void deleteParam(Long paramId);

    ParamEntity save(ParamEntity paramEntity);
}
