package ru.vise.dao.services.implementations;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vise.dao.interfaces.AttributeDAO;
import ru.vise.dao.services.AttributeService;
import ru.vise.entities.AttributeEntity;

@Service
public class AttributeDAOImpl implements AttributeService {

    @Autowired
    AttributeDAO dao;

    @Transactional
    public void deleteByAttrId(Long id){
        dao.deleteByAttrId(id);
    }

    public AttributeEntity findByAttrId(Long attrId){
        return dao.findByAttrId(attrId);
    }

    @Transactional
    public AttributeEntity save(AttributeEntity attributeEntity) {
        return dao.save(attributeEntity);
    }
}
