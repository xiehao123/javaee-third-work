package cn.edu.bjtu.ming.domain;

import javax.persistence.*;


@Entity
@Table(name="coach", uniqueConstraints = {@UniqueConstraint(columnNames = "phone")})
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String phone;

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

    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
