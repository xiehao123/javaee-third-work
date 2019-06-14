package cn.edu.bjtu.ming.dao;

import cn.edu.bjtu.ming.domain.Account;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Transactional
    List<Account> findByNameAndPassword(String name, String password);

    @Transactional
    void deleteByName(String name);
}
