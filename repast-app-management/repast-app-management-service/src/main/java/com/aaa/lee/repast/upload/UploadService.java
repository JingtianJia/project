package com.aaa.lee.repast.upload;

import com.aaa.lee.repast.properties.FtpProperties;
import com.aaa.lee.repast.utils.DateUtil;
import com.aaa.lee.repast.utils.FileNameUtil;
import com.aaa.lee.repast.utils.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.aaa.lee.repast.staticstatus.RequestProperties.POINT;
import static com.aaa.lee.repast.staticstatus.StaticCode.FORMAT_DATE2;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/3/17 11:02
 * @Description
 **/
@Service
public class UploadService {

    @Autowired
    private FtpProperties ftpProperties;

    /**
     * @author Seven Lee
     * @description
     *      ftp文件上传
     * @param [file, token]
     * @date 2020/3/17
     * @return java.lang.Boolean
     * @throws
    **/
    public String upload(MultipartFile file, String token) {
        // 目前来说有两个参数不舒服:1.filePath，2.fileName
        // 防止文件名重复，导致文件覆盖
        // 1.文件名替换
        String oldFileName = file.getOriginalFilename();
        String newFileName = FileNameUtil.getFileName(token);
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf(POINT));
        // 2.filePath
        String filePath = DateUtil.formatDate(new Date(), FORMAT_DATE2);
        try {
            return FtpUtil.uploadFile(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername()
            , ftpProperties.getPassword(), ftpProperties.getBasePath(),ftpProperties.getHttpPath(),
                    filePath, newFileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
