package com.palta.BuildRig.data;


import com.palta.BuildRig.Models.MotherBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface MotherBoardDao extends CrudRepository<MotherBoard, Integer> {
}
