package ru.vise.dao.services;

import ru.vise.entities.ListValueEntity;

public interface ListValueService {

    void deleteByListValueId(Long listValueId);

    ListValueEntity findByListValueId(Long id);

    ListValueEntity save(ListValueEntity listValueEntity);
}
