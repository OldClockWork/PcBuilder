package com.palta.BuildRig.data;

import com.palta.BuildRig.Models.OperatingSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface OperatingSystemDao extends CrudRepository<OperatingSystem, Integer> {
}
