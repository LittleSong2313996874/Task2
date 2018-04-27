package com.ss.controller;


import com.alibaba.fastjson.JSON;
import com.ss.Dao.UserDao;
import com.ss.pojo.Group;
import com.ss.pojo.Person;
import com.ss.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/taskUser")
public class JsonController {

    public JsonController() {
       logger.info("创建了JsonController对象");
    }

    Logger logger = LoggerFactory.getLogger(JsonController.class);

    @Resource
    UserService userService; //这个名字要和刚才注入式给的一样，我猜。



    @RequestMapping(value = "/JsonPersons", method = RequestMethod.GET)

    public @ResponseBody Group getJson(Model model) throws Exception{
        List list = userService.getAll();
        Group group = new Group();
        group.setGroupId(1);
        group.setGroupName("西安分院");
        group.setGroupMember(list);
        //model.addAttribute("group_",group);
        return group;
    }

    @RequestMapping(value = "/JsonPerson/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person getJsonById(Model model, @PathVariable("id") Integer id) throws Exception{

        Person person = userService.getPersonById(id);

        return person;
    }


    //更新后把结果的json返回
    @RequestMapping(value = "/JsonPerson/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Person updateJsonById(Model model, @PathVariable("id") Integer id) throws Exception{

        Person person = userService.getPersonById(id);

        person.setNAME("已更新");
        person.setGender("人妖");
        person.setAge(100);
        person.setSenior("宇智波佐助");

        return person;
    }


}
