package com.galaxy.galaxyblog.service;

import com.galaxy.galaxyblog.mapper.MindMapMapper;
import com.galaxy.galaxyblog.model.Article;
import com.galaxy.galaxyblog.model.MindMap;
import com.galaxy.galaxyblog.model.vo.MindMapVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/11/3
 */
@Service
public class MindMapService {

    @Resource
    private MindMapMapper mindMapMapper;

    public int saveOrUpdate(MindMap mindMap) {
        if(mindMap.getId() == null) return mindMapMapper.insert(mindMap);
        return mindMapMapper.updateById(mindMap);
    }

    public PageInfo<MindMap> findMindMap(MindMapVo mindMapVo) {
        PageHelper.startPage(mindMapVo.getPage(),mindMapVo.getSize());
        List<MindMap> mindMaps = mindMapMapper.searchMindMapPages(mindMapVo);
        PageInfo<MindMap> pageInfo = new PageInfo<>(mindMaps);
        return pageInfo;
    }
}
