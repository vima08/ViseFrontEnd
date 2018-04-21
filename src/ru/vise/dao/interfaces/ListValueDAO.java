package ru.vise.dao.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.vise.entities.ListValueEntity;

public interface ListValueDAO extends JpaRepository<ListValueEntity, Long>{

    void deleteByListValueId(Long listValueId);

    ListValueEntity findByListValueId(Long id);
}
