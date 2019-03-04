package com.oltest.controller;

import jdk.nashorn.internal.objects.ArrayBufferView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import scala.collection.mutable.ArrayBuffer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class userCenter {

    @Autowired
    private JdbcTemplate jdbcTempl;

    public String userCent(Model model, JdbcTemplate jdbcTemplate, String username){
        String sql = "select score from test where username='"+username+"'";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        List score_list = new ArrayList();
        for(Map<String, Object> a:list){
            score_list.add(Integer.parseInt(a.values().toString().substring(1,a.values().toString().length()-1)));
        }
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        model.addAttribute("score_list",score_list);
        model.addAttribute("user",username);
        return "/userCenter";
    }

    @RequestMapping(value = "/center", method = RequestMethod.POST)
    public String center(HttpServletRequest req, Model model) {
        String username = req.getParameter("username");

        return userCent(model,jdbcTempl,username);

    }

}
