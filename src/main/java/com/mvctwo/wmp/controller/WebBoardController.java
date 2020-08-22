package com.mvctwo.wmp.controller;

import com.mvctwo.wmp.domain.WebBoard;
import com.mvctwo.wmp.repository.WebBoardRepository;
import com.mvctwo.wmp.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

    @Autowired
    private WebBoardRepository webBoardRepository;

    @GetMapping("/list")
    public void list(PageVO vo, Model model)
    {
        Pageable page = vo.makePageable(0, "bno");
        Page<WebBoard> result = webBoardRepository.findAll(webBoardRepository.makePredicate(null, null), page);

        log.info("" + page);
        log.info("" + result);

        model.addAttribute("result", result);
    }

}
