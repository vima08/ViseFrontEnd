package ru.vise.dao.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.vise.entities.AttributeEntity;

public interface AttributeDAO extends JpaRepository<AttributeEntity, Long>{

    void deleteByAttrId(Long attrId);

    AttributeEntity findByAttrId(Long attrId);
}
