package com.example.demo.controller;

import com.example.demo.domain.MemberVO;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log
public class SampleController {

    @GetMapping("/sample1")
    public void sample1(Model model)
    {
        model.addAttribute("greeting", "별이되었다구");
    }

    @GetMapping("/sample2")
    public void sample2(Model model)
    {
        MemberVO vo = new MemberVO(123, "u03","p00","홍길동", new Timestamp(System.currentTimeMillis()));
        model.addAttribute("vo", vo);
    }

    @GetMapping("/sample3")
    public void sample3(Model model)
    {
        List<MemberVO> memberVOList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            memberVOList.add(new MemberVO(123, "u0" + i , "p0" + i , "홍길동" + i, new Timestamp(System.currentTimeMillis())));
        }

        model.addAttribute("list", memberVOList);
    }

    @GetMapping("/sample5")
    public void sample5(Model model)
    {
        String result = "SUCCESS";
        model.addAttribute("result", result);
    }

    @GetMapping("/sample6")
    public void sample6(Model model)
    {
        List<MemberVO> list = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            list.add(new MemberVO(i, "u0" + i, "p0" + i, "홍길동" + i, new Timestamp(System.currentTimeMillis())));
        }

        model.addAttribute("list", list);
        String result = "SUCCESS";
        model.addAttribute("result", result);
    }

    @GetMapping("/sample8")
    public void sample8(Model model)
    {

    }
}
