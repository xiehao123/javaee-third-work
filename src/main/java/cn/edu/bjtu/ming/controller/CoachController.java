package cn.edu.bjtu.ming.controller;

import cn.edu.bjtu.ming.dao.CoachRepository;
import cn.edu.bjtu.ming.domain.Coach;
import cn.edu.bjtu.ming.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/v1")
public class CoachController {

    @Autowired
    private CoachRepository coachRepository;

    @ApiOperation(value = "获取教练列表")
    @RequestMapping(value = "/collection/coaches", method = RequestMethod.GET)
    public HttpEntity<CoachList> get_all_coaches(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Iterable<Coach> iterable = coachRepository.findAll();
        List<CoachVO> coaches = new LinkedList<>();
        for (Coach coach : iterable) {
            coaches.add(new CoachVO(coach.getName(), coach.getPhone()));
        }
        PageInfo<CoachVO> pageInfo = new PageInfo<>(coaches);
        CoachList coachList = new CoachList(pageInfo);
        coachList.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.CoachController.class).get_all_coaches(pageNum, pageSize)).withSelfRel());
        return  new ResponseEntity<>(coachList, HttpStatus.OK);
    }

    @ApiOperation(value = "添加一个教练")
    @RequestMapping(value = "/coach", method = RequestMethod.PUT)
    public HttpEntity<Status> add_coach(@RequestParam String name, @RequestParam String phone) {
        Coach coach = new Coach();
        coach.setName(name);
        coach.setPhone(phone);
        Status status;
        try {
            coachRepository.save(coach);
            status = new Status("Success");
        }
        catch (Exception ex) {
            status = new Status("Failed");
        }

        status.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.CoachController.class).add_coach(name, phone)).withSelfRel());
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }

    @ApiOperation(value = "删除一个教练")
    @RequestMapping(value = "/coach", method = RequestMethod.DELETE)
    public HttpEntity<Status> del_coach_by_phone(@RequestParam String phone) {
        Status status;
        try {
            coachRepository.deleteByPhone(phone);
            status = new Status("Success");
        }
        catch (Exception ex) {
            status = new Status("Failed");
        }

        status.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.CoachController.class).del_coach_by_phone(phone)).withSelfRel());
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }

}