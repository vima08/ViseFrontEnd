package ru.vise.dao.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.vise.entities.ParamEntity;

public interface ParamDAO extends JpaRepository<ParamEntity, Long>{

    ParamEntity findByParamId(Long id);

    void deleteParam(Long paramId);
}
