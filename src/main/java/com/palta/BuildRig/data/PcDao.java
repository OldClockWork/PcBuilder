package com.palta.BuildRig.data;

import com.palta.BuildRig.Models.Pc;
import com.palta.BuildRig.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Transactional
@Repository
public interface PcDao extends CrudRepository<Pc, Integer> {

    List<Pc> findByUser(User user);


}
