package cn.edu.bjtu.ming.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import org.springframework.hateoas.ResourceSupport;

public class AccountList extends ResourceSupport {

    PageInfo<AccountVO> pageInfo;

    @JsonCreator
    public AccountList(@JsonProperty PageInfo<AccountVO> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageInfo<AccountVO> getPageInfo(){
        return this.pageInfo;
    }
}
