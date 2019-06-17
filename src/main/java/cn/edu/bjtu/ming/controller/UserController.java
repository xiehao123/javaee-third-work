package cn.edu.bjtu.ming.controller;

import cn.edu.bjtu.ming.dao.UserRepository;
import cn.edu.bjtu.ming.domain.User;
import cn.edu.bjtu.ming.vo.Status;
import cn.edu.bjtu.ming.vo.UserList;
import cn.edu.bjtu.ming.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping(value="/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HttpEntity<Status> login(
            @RequestParam String name,
            @RequestParam String password) {
        System.out.println(name);
        System.out.println(password);
        List<User> user = userRepository.findByNameAndPassword(name, password);
        Status status;
        if (user.size() > 0) {
            status = new Status("Success");
        }
        else
        {
            status = new Status("Failed");
        }
        status.add(linkTo(methodOn(UserController.class).login(name, password)).withSelfRel());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @RequestMapping(value = "get_users", method = RequestMethod.GET)
    @Cacheable
    public HttpEntity<UserList> get_users() {

        Iterable<User> iterable = userRepository.findAll();
        List<UserVO> users = new LinkedList<>();
        for (User user : iterable) {
            users.add(new UserVO(user.getName()));
        }
        UserList userList = new UserList(users);
        userList.add(linkTo(methodOn(UserController.class).get_users()).withSelfRel());
        return  new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "/del_user_by_name", method = RequestMethod.POST)
    public HttpEntity<Status> del_user_by_name(@RequestParam String name) {
        Status status;
        try {
            userRepository.deleteByName(name);
            status = new Status("Success");
        }
        catch (Exception ex) {
            status = new Status("Failed");
        }

        status.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.UserController.class).del_user_by_name(name)).withSelfRel());
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public HttpEntity<Status> add_user(@RequestParam String name, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        Status status;
        try {
            userRepository.save(user);
            status = new Status("Success");
        }
        catch (Exception ex) {
            status = new Status("Failed");
        }

        status.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.UserController.class).add_user(name, password)).withSelfRel());
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }
}
