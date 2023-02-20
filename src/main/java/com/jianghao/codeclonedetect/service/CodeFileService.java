package com.jianghao.codeclonedetect.service;


import com.jianghao.codeclonedetect.mapper.CodeFileMapper;
import com.jianghao.codeclonedetect.model.CodeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @description:代码文件服务类
 * @author: jianghao
 * @date: 2023-02-19 19:50
 **/
@Service
public class CodeFileService {
    @Autowired
    private CodeFileMapper codeFileMapper;

    public CodeFile getById(Integer id) {
        return codeFileMapper.getCodeFileById(id);
    }

    public List<CodeFile> listByProject(Integer projectId) {
        return codeFileMapper.listByProject(projectId);
    }

    public void save(CodeFile codeFile) {
        codeFileMapper.save(codeFile);
    }

    public List<CodeFile> listAll() {
        return codeFileMapper.listAll();
    }

    public void update(CodeFile codeFile) {
        codeFileMapper.update(codeFile);
    }

    public void delete(Integer id) {
        codeFileMapper.delete(id);
    }
}
