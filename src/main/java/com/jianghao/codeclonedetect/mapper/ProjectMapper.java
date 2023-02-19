package com.jianghao.codeclonedetect.mapper;

import com.jianghao.codeclonedetect.model.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:项目映射器
 * @author: jianghao
 * @date: 2023-02-19 19:33
 **/
@Mapper
public interface ProjectMapper {
    Project getById(Integer id);
    List<Project> list();
    void save(Project project);
    void update(Project project);
    void delete(Integer id);
}
