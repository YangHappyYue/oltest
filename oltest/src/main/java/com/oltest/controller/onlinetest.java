package com.oltest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class onlinetest {
    @Autowired
    private JdbcTemplate jdbcTempl;

    @RequestMapping(value = "/checkAnswer", method = RequestMethod.POST)
    public String checkAnswer(HttpServletRequest req,Model model){
        String test= jdbcTempl.queryForList("select max(id) from test").get(0).values().toString();
        int testId  = Integer.parseInt(test.substring(1,test.length()-1));
        System.out.println(testId);
        int score = 0;
        String sql = "select question from test where id="+testId;
        String question_list_1 = jdbcTempl.queryForList(sql).get(0).values().toString();
        String[] question_list_2 = question_list_1.substring(2,question_list_1.length()-2).split(",");

        ArrayList ans =  new ArrayList();
        for(int i=0;i<15;i++){
            String question_ans = jdbcTempl.queryForList("select answer from questions where id="+question_list_2[i]).get(0).values().toString().substring(1,2);

            String tag = "answer"+(i+1);
            String x = req.getParameter(tag);
            System.out.println("true:"+question_ans);
            System.out.println("real:"+x);
            if(question_ans .equals(x)){
                score += 4;
            }
            ans.add(x);
        }
        String ans_list = "[";
        for(int i = 0;i<ans.size();i++){
            if(i!=ans.size()-1){
                ans_list += ans.get(i) + ",";
            }else{
                ans_list += ans.get(i) + "]";
            }
        }
        jdbcTempl.execute("update test set user_answer='"+ans_list+"' , score="+score+" where id="+testId);
        String username_1 = jdbcTempl.queryForList("select username from test where id="+testId).get(0).values().toString();
        String username = username_1.substring(1,username_1.length()-1);

        model.addAttribute("score",score);
        model.addAttribute("user",username);
        return "/score";
    }

    @RequestMapping(value = "/oltest", method = RequestMethod.POST)
    public String oltest(Model model,HttpServletRequest req) {
        String uname = req.getParameter("username");
        Vector<Integer> v = getNum(15,16);
        String question = "[";
        ArrayList list = new ArrayList();
        for(int i=0;i<v.size();i++){
            if(i==v.size()-1){
                question += v.get(i).toString()+"]";
            }else{
                question += v.get(i).toString()+",";
            }
            String sql = "select * from questions where id="+v.get(i);
            list.add(jdbcTempl.queryForList(sql).get(0).values());
        }
        jdbcTempl.execute("insert into test (username,question) values ('"+uname+"','"+question+"')");
       // int test_id = ;
        model.addAttribute("question_list",list);
        return "/oltest";
    }

    private Vector<Integer> getNum(int num,int quesnum){
        Vector<Integer> v = new Vector<Integer>();
        Random r = new Random();
        int count = 0;
        while(count < num){
            int number = r.nextInt(quesnum);
            if(!v.contains(number)){
                v.add(number);
                count++;
            }
        }
        return v;
    }
}
