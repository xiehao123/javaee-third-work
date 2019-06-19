package cn.edu.bjtu.ming.dao;

import cn.edu.bjtu.ming.domain.Coach;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CoachRepository extends CrudRepository<Coach, Integer>{

    @Transactional
    void deleteByMobile(String mobile);

    @Transactional
    List<Coach> findByMobile(String mobile);

}
