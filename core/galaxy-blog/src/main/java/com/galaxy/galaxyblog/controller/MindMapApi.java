package com.galaxy.galaxyblog.controller;


import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.model.MindMap;
import com.galaxy.galaxyblog.model.vo.MindMapVo;
import com.galaxy.galaxyblog.service.MindMapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/11/3
 */
@RestController
@RequestMapping("/mindMap")
public class MindMapApi {
    @Resource
    private MindMapService mindMapService;
    @PostMapping("/add")
    public HttpResultResp saveMindMap(@RequestBody MindMap mindMap){
        mindMapService.saveOrUpdate(mindMap);
        return HttpResultResp.ok();
    }
    @PostMapping("/find")
    public HttpResultResp findMindMap(@RequestBody MindMapVo mindMapVo){
        return HttpResultResp.ok(mindMapService.findMindMap(mindMapVo));
    }
}
