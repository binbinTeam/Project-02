package com.zhzteam.zhz233.controller.zlb;

import com.zhzteam.zhz233.common.config.StatusConfig;
import com.zhzteam.zhz233.common.utils.FileLoadUtils;
import com.zhzteam.zhz233.model.zlb.ResultView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(value = "/zlb")
public class DownloadController {

    @RequestMapping(value = "/download")
    @ResponseBody
    public String download(HttpServletRequest request, HttpServletResponse response) {
        String pathName =  "G:\\Zhz233File\\";
        String fileName = "UpperDevice2018.05.20.0001.zip";
        if(FileLoadUtils.downloadFile(request,response,pathName,fileName)) return "下载成功！";
        else return "下载失败！";
    }
}
