package com.jianghao.codeclonedetect.controller;

import com.jianghao.codeclonedetect.model.CodeFile;
import com.jianghao.codeclonedetect.model.Project;
import com.jianghao.codeclonedetect.model.SimilarCodeFragment;
import com.jianghao.codeclonedetect.service.CodeFileService;
import com.jianghao.codeclonedetect.service.SimilarCodeFragmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @description:代码文件控制器
 * @author: jianghao
 * @date: 2023-02-19 21:59
 **/

@Controller
@RequestMapping("/codeFile")
public class CodeFileController {
    @Autowired
    private CodeFileService codeFileService;
    @Autowired
    private SimilarCodeFragmentService similarCodeFragmentService;

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Integer id, Model model) {
        CodeFile codeFile = codeFileService.getById(id);
        if (codeFile == null) {
            throw new RuntimeException("指定的文件不存在");
        }

        String astJson = codeFile.getAstJson();
        if (astJson == null) {
            throw new RuntimeException("指定的文件没有AST树");
        }

        List<SimilarCodeFragment> fragmentList = similarCodeFragmentService.listByFile(id);
        model.addAttribute("codeFile", codeFile);
        model.addAttribute("astJson", astJson);
        model.addAttribute("fragmentList", fragmentList);

        return "codeFile/view";
    }

    @GetMapping("/compare/{id1}/{id2}")
    public String compare(@PathVariable("id1") Integer id1, @PathVariable("id2") Integer id2, Model model) {
        CodeFile codeFile1 = codeFileService.getById(id1);
        CodeFile codeFile2 = codeFileService.getById(id2);
        if (codeFile1 == null || codeFile2 == null) {
            throw new RuntimeException("指定的文件不存在");
        }

        String astJson1 = codeFile1.getAstJson();
        String astJson2 = codeFile2.getAstJson();
        if (astJson1 == null || astJson2 == null) {
            throw new RuntimeException("指定的文件没有AST树");
        }

        List<SimilarCodeFragment> fragmentList = similarCodeFragmentService.listByFiles(id1, id2);
        model.addAttribute("codeFile1", codeFile1);
        model.addAttribute("codeFile2", codeFile2);
        model.addAttribute("astJson1", astJson1);
        model.addAttribute("astJson2", astJson2);
        model.addAttribute("fragmentList", fragmentList);

        return "codeFile/compare";
    }
}



