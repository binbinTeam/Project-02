package com.zhzteam.zhz233.controller.zlb;

import com.zhzteam.zhz233.common.config.StatusConfig;
import com.zhzteam.zhz233.model.zlb.ResultView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping(value = "/zlb")
public class UploadController {

    private ResultView resultView;

    @RequestMapping(value = "/upload")
    public ResultView ResultView(@RequestParam("file") MultipartFile file){
        //初始化容器
        resultView = new ResultView();
        if(!file.isEmpty()){
            //获取文件名
            String fileName = file.getOriginalFilename();
            //System.err.println("文件名：" + fileName);
            String prefixName = fileName.substring(0,fileName.lastIndexOf("."));
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //System.err.println("文件后缀名：" + suffixName);
            //设置路径
            String filePath = "G:\\Zhz233File\\";
            //New FileName
            //创建数据库UpperDevice
            //设置版本号
            //创建文件
            File dest = new File(filePath,fileName);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            try {
                file.transferTo(dest);// 文件写入
                resultView.setMessage("上传成功!");
                resultView.setStatus(StatusConfig.SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            resultView.setStatus(StatusConfig.FAIL);
            resultView.setMessage("文件为空!");
        }
        return resultView;
    }
}
