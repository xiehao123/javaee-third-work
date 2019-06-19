package cn.edu.bjtu.ming.domain;

import javax.persistence.*;


@Entity
@Table(name="coach", uniqueConstraints = {@UniqueConstraint(columnNames = "mobile")})
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String mobile;

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
