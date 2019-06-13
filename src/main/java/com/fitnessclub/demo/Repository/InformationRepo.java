package com.fitnessclub.demo.Repository;

import com.fitnessclub.demo.Entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepo extends JpaRepository<Information,String> {

}
