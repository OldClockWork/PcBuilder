package com.palta.BuildRig.data;

import com.palta.BuildRig.Models.Rig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RigDao extends CrudRepository<Rig, Integer> {
    public Rig getById(Integer id);
}
