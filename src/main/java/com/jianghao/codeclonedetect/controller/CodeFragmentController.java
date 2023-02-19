package com.jianghao.codeclonedetect.controller;

import com.jianghao.codeclonedetect.model.SimilarCodeFragment;
import com.jianghao.codeclonedetect.service.SimilarCodeFragmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/codeFragment")
public class CodeFragmentController {
    @Autowired
    private SimilarCodeFragmentService similarCodeFragmentService;

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Integer id, Model model) {
        SimilarCodeFragment fragment = similarCodeFragmentService.getById(id);
        if (fragment == null) {
            throw new RuntimeException("指定的代码片段不存在");
        }

        model.addAttribute("fragment", fragment);

        return "codeFragment/view";
    }
}

