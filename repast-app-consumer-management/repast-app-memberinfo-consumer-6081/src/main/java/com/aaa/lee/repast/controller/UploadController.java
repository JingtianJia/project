package com.aaa.lee.repast.controller;

import com.aaa.lee.repast.base.BaseController;
import com.aaa.lee.repast.base.ResultData;
import com.aaa.lee.repast.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/17 10:12
 * @Description
 **/

@RestController
@Api(value = "文件上传", tags = "文件上传接口")
public class UploadController extends BaseController {

    @Autowired
    private IMemberService repastService;

    /**
     * @author Seven Lee
     * @description
     *      文件上传接口
     * @param [file, token]
     * @date 2020/3/17
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
    **/
    @PostMapping("/upload")
    @ApiOperation(value = "实现文件上传", notes = "单文件上传接口")
    public ResultData uploadFile(MultipartFile file, String token) {
        Boolean ifSuccess = repastService.uploadFile(file, token);
        if(ifSuccess) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

}
