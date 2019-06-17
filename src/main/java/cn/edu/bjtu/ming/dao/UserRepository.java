package cn.edu.bjtu.ming.dao;

import cn.edu.bjtu.ming.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Transactional
    @Cacheable("test")
    List<User> findByNameAndPassword(String name, String password);

    @Transactional
    void deleteByName(String name);
}
