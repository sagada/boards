package com.mvctwo.wmp.controller;

import com.mvctwo.wmp.domain.WebBoard;
import com.mvctwo.wmp.repository.CustomCrudRepository;
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
    private CustomCrudRepository webBoardRepository;
//    private WebBoardRepository webBoardRepository;

    @GetMapping("/list")
    public void list(@ModelAttribute("pageVO") PageVO vo, Model model)
    {
        Pageable page = vo.makePageable(0, "bno");
        Page<Object[]> result = webBoardRepository.getCustomPage(vo.getType(), vo.getKeyword(), page);

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

    @GetMapping("/view")
    public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model)
    {
        log.info("BNO : " + bno);
        webBoardRepository.findById(bno).ifPresent(board->model.addAttribute("vo", board));
    }

    @GetMapping("/modify")
    public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model){
        webBoardRepository.findById(bno).ifPresent(board->model.addAttribute("vo", board));
    }

    @PostMapping("/delete")
    public String delete(Long bno, PageVO vo, RedirectAttributes rttr)
    {
        webBoardRepository.deleteById(bno);

        rttr.addFlashAttribute("msg", "success");
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/list";
    }


    @PostMapping("/modify")
    public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr)
    {
        webBoardRepository.findById(board.getBno()).ifPresent(origin->{
            origin.setTitle(board.getTitle());
            origin.setContent(board.getContent());

            webBoardRepository.save(origin);

            rttr.addFlashAttribute("msg", "success");
            rttr.addAttribute("bno", origin.getBno());

        });
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/view";
    }
}
