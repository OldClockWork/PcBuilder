package com.palta.BuildRig.data;

import com.palta.BuildRig.Models.Rig;
import com.palta.BuildRig.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Transactional
@Repository
public interface RigDao extends CrudRepository<Rig, Integer> {

    List<Rig> findByUser(User user);


}
