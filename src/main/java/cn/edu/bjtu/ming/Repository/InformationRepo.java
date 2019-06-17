package cn.edu.bjtu.ming.Repository;


import cn.edu.bjtu.ming.Entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepo extends JpaRepository<Information,String> {

}
