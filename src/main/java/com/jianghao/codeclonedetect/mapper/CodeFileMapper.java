package com.jianghao.codeclonedetect.mapper;


import com.jianghao.codeclonedetect.model.CodeFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:代码文件映射器
 * @author: jianghao
 * @date: 2023-02-19 19:33
 **/
@Mapper
public interface CodeFileMapper {
    CodeFile getCodeFileById(Integer id);
    List<CodeFile> listByProject(Integer projectId);
    List<CodeFile> listAll();
    void save(CodeFile codeFile);
    void update(CodeFile codeFile);
    void delete(Integer id);
}

