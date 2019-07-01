package com.palta.BuildRig.data;

import com.palta.BuildRig.Models.Storage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface StorageDao extends CrudRepository<Storage, Integer> {
}
