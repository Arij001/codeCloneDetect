package com.jianghao.codeclonedetect.model;

import lombok.Data;

import java.util.Date;


/**
 * @description:项目实体类
 * @author: jianghao
 * @date: 2023-02-19 19:30
 **/
@Data
public class Project {
    private Integer id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
}
