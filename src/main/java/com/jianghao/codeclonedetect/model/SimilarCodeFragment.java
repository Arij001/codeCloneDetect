package com.jianghao.codeclonedetect.model;

import lombok.Data;

/**
 * @description:相似代码片段实体类
 * @author: jianghao
 * @date: 2023-02-19 19:30
 **/
@Data
public class SimilarCodeFragment {
    //相似代码片段所属的代码文件ID
    private Integer id;
    private Integer fileId1;
    private Integer fileId2;
    //代码片段在第一个文件中的起始行号和结束行号
    private Integer startLine1;
    private Integer endLine1;
    //代码片段在第二个文件中的起始行号和结束行号
    private Integer startLine2;
    private Integer endLine2;
    //相似度
    private Double similarity;
    //代码片段的内容
    private String fragment1;
    private String fragment2;
}
