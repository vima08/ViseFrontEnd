package ru.vise.dao.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vise.dao.interfaces.ParamDAO;
import ru.vise.dao.services.ParamService;
import org.springframework.transaction.annotation.Transactional;
import ru.vise.entities.ParamEntity;

@Service
public class ParamDAOImpl implements ParamService {

    @Autowired
    ParamDAO dao;

    public ParamEntity findByParamId(Long id){
        return dao.findByParamId(id);
    }

    @Transactional
    public void deleteParam(Long paramId){
        dao.deleteParam(paramId);
    }

    @Transactional
    public ParamEntity save(ParamEntity paramEntity) {
        return dao.save(paramEntity);
    }
}
