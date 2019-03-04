package com.oltest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class Login {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/login")
    public String login() {
        return "/Login";
    }

    @RequestMapping(value = "/loginAuth", method = RequestMethod.POST)
    public String loginAuth(HttpServletRequest req,Model model) {

        userCenter uc = new userCenter();
        String userName = req.getParameter("username");
        String password = req.getParameter("passwd");
        String sql = "select * from user where username='"+userName+"' and passwd="+password;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        if(!list.isEmpty()){
           //
            return uc.userCent(model,jdbcTemplate,userName);
        }
        else{
            model.addAttribute("answer","error");
            return "/Login";}
    }
    @RequestMapping("/123")
    public String test1(Model model) {
        model.addAttribute("hello","error");
        //model.addAttribute("user", "23");
        return "/123";
    }

}
