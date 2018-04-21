package ru.vise.dao.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vise.dao.interfaces.ListValueDAO;
import ru.vise.dao.services.ListValueService;
import ru.vise.entities.ListValueEntity;

@Service
public class ListValueDAOImpl implements ListValueService {

    @Autowired
    ListValueDAO dao;

    @Transactional
    public void deleteByListValueId(Long listValueId){
        dao.deleteByListValueId(listValueId);
    }

    public ListValueEntity findByListValueId(Long id){
        return dao.findByListValueId(id);
    }

    @Transactional
    public ListValueEntity save(ListValueEntity listValueEntity) {
        return dao.save(listValueEntity);
    }
}
