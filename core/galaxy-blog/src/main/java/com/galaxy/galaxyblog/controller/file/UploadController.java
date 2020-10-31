package com.galaxy.galaxyblog.controller.file;

import com.galaxy.galaxyblog.common.HttpResultResp;
import com.galaxy.galaxyblog.common.utils.FastDFSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/10/31
 */
@RestController
@RequestMapping("/file")
public class UploadController {
    @Autowired
    private FastDFSClientUtil dfsClient;

    @PostMapping("/upload")
    public HttpResultResp fdfsUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        try {
            HashMap<String, String> hashMap = new HashMap<>();
            String url = dfsClient.uploadFile(file);
            hashMap.put("url",url);
            return HttpResultResp.ok(hashMap);
        } catch (IOException e) {
            e.printStackTrace();
            return HttpResultResp.error("文件上传失败");
        }
    }
    @DeleteMapping("/del")
    public HttpResultResp fdfsDel(@RequestParam("filePath") String filePath){
        dfsClient.delFile(filePath);
        return HttpResultResp.ok();
    }

}
