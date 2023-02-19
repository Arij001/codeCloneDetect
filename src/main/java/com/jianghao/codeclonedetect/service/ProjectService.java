package com.jianghao.codeclonedetect.service;

import com.jianghao.codeclonedetect.mapper.ProjectMapper;
import com.jianghao.codeclonedetect.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:项目服务类
 * @author: jianghao
 * @date: 2023-02-19 19:49
 **/
@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    public Project getById(Integer id) {
        return projectMapper.getById(id);
    }

    public List<Project> list() {
        return projectMapper.list();
    }

    public void save(Project project) {
        projectMapper.save(project);
    }

    public void update(Project project) {
        projectMapper.update(project);
    }

    public void delete(Integer id) {
        projectMapper.delete(id);
    }
}




