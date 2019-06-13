package com.fitnessclub.demo.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "information")
public class Information  implements Serializable {

    @Id
    @Column(name = "name")
    public String name;
    @Column(name = "sex")
    public String sex;
    @Column(name = "age")
    public String age;
    @Column(name = "reason")
    public String reason;

}
