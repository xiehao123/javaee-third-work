package cn.edu.bjtu.ming.dao;

import cn.edu.bjtu.ming.domain.Coach;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface CoachRepository extends CrudRepository<Coach, Integer>{

    @Transactional
    void deleteByPhone(String phone);

}
