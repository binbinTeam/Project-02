package com.zhzteam.zhz233.controller.zlb;

import com.zhzteam.zhz233.common.utils.SMSUtils;
import com.zhzteam.zhz233.common.utils.UploadUtils;
import com.zhzteam.zhz233.model.TestModel;
import com.zhzteam.zhz233.model.zlb.GoodsLeaseInfo;
import com.zhzteam.zhz233.model.zlb.GoodsResult;
import com.zhzteam.zhz233.model.zlb.ResultView;
import com.zhzteam.zhz233.model.zlb.SMSResult;
import com.zhzteam.zhz233.service.zlb.impl.GoodsServiceImpl;
import com.zhzteam.zhz233.service.zlb.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    GoodsServiceImpl goodsServiceImpl;

    private ResultView resultView;
    private List<GoodsResult> goodsResultList;




    @RequestMapping("/uploadFile")
    public String createFile(){
        String baseFilePath = "G:\\Zhz233File\\";
        String newPath = "newFile\\";
        File file = new File(baseFilePath + newPath);



        if(!file.exists()){
            file.mkdirs();
        }
        return "success";
    }

    @RequestMapping("/redis")
    public String redisTest(){
        TestModel testModel = new TestModel();
        testModel.setTest("test");
        redisService.set("test",testModel,1000L);
        System.err.println(testModel.getTest()+"/////");
        testModel = (TestModel) redisService.get("test");
        System.err.println(testModel.getTest());
        return "test";
    }
}
