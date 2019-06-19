package cn.edu.bjtu.ming.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import org.springframework.hateoas.ResourceSupport;

public class CoachList extends ResourceSupport {

    PageInfo<CoachVO> pageInfo;

    @JsonCreator
    public CoachList(@JsonProperty PageInfo<CoachVO> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageInfo<CoachVO> getPageInfo(){
        return this.pageInfo;
    }
}
