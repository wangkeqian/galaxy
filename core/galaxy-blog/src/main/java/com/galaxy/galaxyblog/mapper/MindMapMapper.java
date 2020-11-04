package com.galaxy.galaxyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.galaxy.galaxyblog.model.Article;
import com.galaxy.galaxyblog.model.MindMap;
import com.galaxy.galaxyblog.model.vo.MindMapVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MindMapMapper extends BaseMapper<MindMap> {
    List<MindMap> searchMindMapPages(@Param("params") MindMapVo mindMapVo);
}
