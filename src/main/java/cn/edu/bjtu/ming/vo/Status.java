package cn.edu.bjtu.ming.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class Status extends ResourceSupport {

    private String msg;

    @JsonCreator
    public Status(@JsonProperty String msg) {
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }
}
