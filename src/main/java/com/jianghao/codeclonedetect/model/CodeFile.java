package com.jianghao.codeclonedetect.model;

import lombok.Data;

/**
 * @description:代码文件实体类
 * @author: jianghao
 * @date: 2023-02-19 19:30
 **/
@Data
public class CodeFile {
    private Integer id;
    private String name;
    private String path;
    private String content;
    private Integer projectId;
    private String astJson;
}

