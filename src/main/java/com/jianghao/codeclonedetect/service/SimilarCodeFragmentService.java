package com.jianghao.codeclonedetect.service;

import com.jianghao.codeclonedetect.mapper.SimilarCodeFragmentMapper;
import com.jianghao.codeclonedetect.model.SimilarCodeFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:相似代码片段服务类
 * @author: jianghao
 * @date: 2023-02-19 19:51
 **/
@Service
public class SimilarCodeFragmentService {
    @Autowired
    private SimilarCodeFragmentMapper similarCodeFragmentMapper;

    public SimilarCodeFragment getById(Integer id) {
        return similarCodeFragmentMapper.getById(id);
    }

    public List<SimilarCodeFragment> listByFile(Integer fileId) {
        return similarCodeFragmentMapper.listByFile(fileId);
    }

    public List<SimilarCodeFragment> listByFiles(Integer fileId1, Integer fileId2) {
        return similarCodeFragmentMapper.listByFiles(fileId1, fileId2);
    }

    public void save(SimilarCodeFragment similarCodeFragment) {
        similarCodeFragmentMapper.save(similarCodeFragment);
    }

    public void update(SimilarCodeFragment similarCodeFragment) {
        similarCodeFragmentMapper.update(similarCodeFragment);
    }

    public void delete(Integer id) {
        similarCodeFragmentMapper.delete(id);
    }
}
