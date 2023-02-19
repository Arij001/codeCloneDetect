package com.jianghao.codeclonedetect.controller;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.jianghao.codeclonedetect.model.CodeFile;
import com.jianghao.codeclonedetect.model.Project;
import com.jianghao.codeclonedetect.service.CodeFileService;
import com.jianghao.codeclonedetect.service.ProjectService;
import com.jianghao.codeclonedetect.utils.ASTNodeJsonConverter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * @description:项目控制器
 * @author: jianghao
 * @date: 2023-02-19 19:51
 **/
@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private CodeFileService codeFileService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Project> projectList = projectService.list();
        model.addAttribute("projectList", projectList);
        return "project/list";
    }

    @GetMapping("/add")
    public String add() {
        return "project/add";
    }

    @PostMapping("/save")
    public String save(Project project) {
        projectService.save(project);
        return "redirect:/project/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Project project = projectService.getById(id);
        model.addAttribute("project", project);
        return "project/edit";
    }

    @PostMapping("/update")
    public String update(Project project) {
        projectService.update(project);
        return "redirect:/project/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Integer id, Model model) {
        Project project = projectService.getById(id);
        if (project == null) {
            throw new RuntimeException("指定的项目不存在");
        }

        List<CodeFile> codeFileList = codeFileService.listByProject(id);
        model.addAttribute("project", project);
        model.addAttribute("codeFileList", codeFileList);

        return "project/view";
    }

    @GetMapping("/{id}/codeFile/upload")
    public String uploadCodeFile(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        return "codeFile/upload";
    }

    @PostMapping("/{id}/codeFile/save")
    public String saveCodeFile(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("上传的文件不能为空");
        }

        Project project = projectService.getById(id);
        if (project == null) {
            throw new RuntimeException("指定的项目不存在");
        }

        // 将文件保存到磁盘
        String uploadPath = System.getProperty("java.io.tmpdir") + "/codesimilarity/" + UUID.randomUUID().toString() + ".java";
        File dest = new File(uploadPath);
        FileUtils.copyInputStreamToFile(file.getInputStream(), dest);

        // 解析文件内容
        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> cu = javaParser.parse(dest);
        String astJson = null;
        if (cu.isSuccessful()) {
            CompilationUnit compilationUnit = cu.getResult().get();
            astJson = new ASTNodeJsonConverter().convert(compilationUnit);
        }

        // 将代码文件信息保存到数据库
        CodeFile codeFile = new CodeFile();
        codeFile.setName(file.getOriginalFilename());
        codeFile.setPath(dest.getAbsolutePath());
        codeFile.setContent(FileUtils.readFileToString(dest, StandardCharsets.UTF_8));
        codeFile.setProjectId(project.getId());
        codeFile.setAstJson(astJson);
        codeFileService.save(codeFile);

        return "redirect:/project/" + id + "/codeFile/list";
    }

    @GetMapping("/codeFile/{id}")
    public void downloadCodeFile(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        CodeFile codeFile = codeFileService.getById(id);
        if (codeFile == null) {
            throw new RuntimeException("指定的文件不存在");
        }

        File file = new File(codeFile.getPath());
        if (!file.exists() || file.isDirectory()) {
            throw new RuntimeException("指定的文件不存在");
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + codeFile.getName());

        try (InputStream inputStream = new FileInputStream(file); OutputStream outputStream = response.getOutputStream()) {
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}