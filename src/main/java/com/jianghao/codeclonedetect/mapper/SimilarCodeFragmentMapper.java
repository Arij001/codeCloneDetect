package com.jianghao.codeclonedetect.mapper;

import com.jianghao.codeclonedetect.model.SimilarCodeFragment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:相似代码片段映射器
 * @author: jianghao
 * @date: 2023-02-19 19:33
 **/
@Mapper
public interface SimilarCodeFragmentMapper {
    SimilarCodeFragment getById(Integer id);
    List<SimilarCodeFragment> listByFile(Integer fileId);
    List<SimilarCodeFragment> listByFiles(Integer fileId1, Integer fileId2);
    void saveAll(List<SimilarCodeFragment> fragmentList);
    void save(SimilarCodeFragment similarCodeFragment);
    void update(SimilarCodeFragment similarCodeFragment);
    void delete(Integer id);
}
