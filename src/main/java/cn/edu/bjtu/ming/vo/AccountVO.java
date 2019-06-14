package cn.edu.bjtu.ming.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountVO {

    private String name;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String mobile;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

    @JsonCreator
    public AccountVO(@JsonProperty String name, @JsonProperty String mobile, @JsonProperty String address) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }
}
