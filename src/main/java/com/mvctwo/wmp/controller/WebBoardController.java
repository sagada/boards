package com.mvctwo.wmp.controller;

import com.mvctwo.wmp.domain.WebBoard;
import com.mvctwo.wmp.repository.WebBoardRepository;
import com.mvctwo.wmp.vo.PageMaker;
import com.mvctwo.wmp.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

    @Autowired
    private WebBoardRepository webBoardRepository;

    @GetMapping("/list")
    public void list(@ModelAttribute("pageVO") PageVO vo, Model model)
    {
        Pageable page = vo.makePageable(0, "bno");
        Page<WebBoard> result = webBoardRepository.findAll(
                webBoardRepository.makePredicate(vo.getType(), vo.getKeyword()), page);
        log.info("" + page);
        log.info("" + result);

        model.addAttribute("result", new PageMaker(result));
    }

    @GetMapping("/register")
    public void registerGET(@ModelAttribute("vo") WebBoard vo)
    {
        log.info("register get");
    }

    @PostMapping("/register")
    public String registerPOST(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr)
    {
        log.info("register post");
        log.info("" + vo);

        webBoardRepository.save(vo);
        rttr.addFlashAttribute("msg", "success");
        return "redirect:/boards/list";
    }

}
