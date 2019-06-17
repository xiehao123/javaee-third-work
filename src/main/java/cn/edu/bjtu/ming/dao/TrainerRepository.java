package cn.edu.bjtu.ming.dao;

import cn.edu.bjtu.ming.domain.Trainer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface TrainerRepository extends CrudRepository<Trainer, Integer>{

    @Transactional
    void deleteByPhone(String phone);

}
