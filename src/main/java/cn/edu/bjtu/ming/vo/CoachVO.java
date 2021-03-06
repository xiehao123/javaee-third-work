package cn.edu.bjtu.ming.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class CoachVO extends ResourceSupport {

    private String name;
    private String phone;

    @JsonCreator
    public CoachVO(@JsonProperty String name, @JsonProperty String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }
    public String getPhone() { return this.phone;}
}
