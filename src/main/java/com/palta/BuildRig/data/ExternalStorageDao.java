package com.palta.BuildRig.data;

import com.palta.BuildRig.Models.ExternalStorage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ExternalStorageDao extends CrudRepository<ExternalStorage, Integer> {
}
