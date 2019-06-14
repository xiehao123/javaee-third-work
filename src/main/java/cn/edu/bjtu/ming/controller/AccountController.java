package cn.edu.bjtu.ming.controller;

import cn.edu.bjtu.ming.dao.AccountRepository;
import cn.edu.bjtu.ming.domain.Account;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping(value="/v1")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HttpEntity<Status> login(
            @RequestParam String name,
            @RequestParam String password) {
        List<Account> account = accountRepository.findByNameAndPassword(name, password);
        Status status;
        if (account.size() > 0) {
            status = new Status("Success");
        }
        else
        {
            status = new Status("Failed");
        }
        status.add(linkTo(methodOn(AccountController.class).login(name, password)).withSelfRel());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "/collection/accounts", method = RequestMethod.GET)
    public HttpEntity<AccountList> get_all_accounts(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Iterable<Account> iterable = accountRepository.findAll();
        List<AccountVO> accounts = new LinkedList<>();
        for (Account account : iterable) {
            accounts.add(new AccountVO(account.getName(), account.getMobile(), account.getAddress()));
        }
        PageInfo<AccountVO> pageInfo = new PageInfo<>(accounts);
        AccountList accountList = new AccountList(pageInfo);
        accountList.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.AccountController.class).get_all_accounts(pageNum, pageSize)).withSelfRel());
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    @ApiOperation(value = "删除用户（通过用户名）")
    @RequestMapping(value = "/account", method = RequestMethod.DELETE)
    public HttpEntity<Status> del_account_by_name(@RequestParam String name) {
        Status status;
        try {
            accountRepository.deleteByName(name);
            status = new Status("Success");
        }
        catch (Exception ex) {
            status = new Status("Failed");
        }

        status.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.AccountController.class).del_account_by_name(name)).withSelfRel());
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }
    @ApiOperation(value = "添加一个用户")
    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public HttpEntity<Status> add_account(@RequestParam String name, @RequestParam String password, @RequestParam String mobile, @RequestParam String address) {
        Account account = new Account();
        account.setName(name);
        account.setPassword(password);
        account.setAddress(address);
        account.setMobile(mobile);
        Status status;
        try {
            accountRepository.save(account);
            status = new Status("Success");
        }
        catch (Exception ex) {
            status = new Status("Failed");
        }

        status.add(linkTo(methodOn(cn.edu.bjtu.ming.controller.AccountController.class).add_account(name, password, mobile, address)).withSelfRel());
        return  new ResponseEntity<>(status, HttpStatus.OK);
    }
}
