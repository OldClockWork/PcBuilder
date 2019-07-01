package com.palta.BuildRig.data;

import com.palta.BuildRig.Models.Software;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface SoftwareDao extends CrudRepository<Software, Integer> {
}
