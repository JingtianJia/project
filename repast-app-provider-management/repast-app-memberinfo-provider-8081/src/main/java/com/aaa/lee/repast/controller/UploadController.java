package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.aaa.lee.repast.staticstatus.RequestProperties.TOKEN;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/17 11:01
 * @Description
 **/
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * @author Seven Lee
     * @description
     *      ftp文件上传
     * @param [file, token]
     * @date 2020/3/17
     * @return java.lang.Boolean
     * @throws
    **/
    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean uploadFile(@RequestBody MultipartFile file, @RequestParam(TOKEN) String token) {
        return uploadService.upload(file, token);
    }

}


