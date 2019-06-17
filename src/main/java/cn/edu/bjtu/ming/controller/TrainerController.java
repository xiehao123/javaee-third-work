package cn.edu.bjtu.ming.controller;

import cn.edu.bjtu.ming.dao.TrainerRepository;
import cn.edu.bjtu.ming.domain.Trainer;
import cn.edu.bjtu.ming.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value="/v1")
public class TrainerController {

    @Autowired
    private TrainerRepository trainerRepository;

    @RequestMapping(value = "/get_all_trainers", method = RequestMethod.GET)
    public HttpEntity<PageInfo> get_all_trainers(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        System.out.println("Compute");
        PageHelper.startPage(pageNum, pageSize);
        Iterable<Trainer> iterable = trainerRepository.findAll();
        List<TrainerVO> trainers = new LinkedList<>();
        for (Trainer trainer : iterable) {
            trainers.add(new TrainerVO(trainer.getName(), trainer.getPhone()));
        }
        PageInfo<TrainerVO> pageInfo = new PageInfo<>(trainers);
        TrainerList trainerList = new TrainerList(trainers);
        trainerList.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.TrainerController.class).get_all_trainers(pageNum, pageSize)).withSelfRel());
        return  new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/add_trainer", method = RequestMethod.POST)
    public HttpEntity<Status> add_trainer(@RequestParam String name, @RequestParam String phone) {
        Trainer trainer = new Trainer();
        trainer.setName(name);
        trainer.setPhone(phone);
        Status status;
        try {
            trainerRepository.save(trainer);
            status = new Status("Success");
        }
        catch (Exception ex) {
            status = new Status("Failed");
        }

        status.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.TrainerController.class).add_trainer(name, phone)).withSelfRel());
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }

    @RequestMapping(value = "/del_trainer_by_phone", method = RequestMethod.POST)
    public HttpEntity<Status> del_trainer_by_phone(@RequestParam String phone) {
        Status status;
        try {
            trainerRepository.deleteByPhone(phone);
            status = new Status("Success");
        }
        catch (Exception ex) {
            status = new Status("Failed");
        }

        status.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.TrainerController.class).del_trainer_by_phone(phone)).withSelfRel());
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }

}
