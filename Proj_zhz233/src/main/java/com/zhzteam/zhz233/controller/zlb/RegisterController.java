package com.zhzteam.zhz233.controller.zlb;

import com.zhzteam.zhz233.common.config.StatusConfig;
import com.zhzteam.zhz233.model.zlb.PathResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @RequestMapping("/register")
    public PathResult register(){
        PathResult  pathResult = new PathResult();
        pathResult.setPath("/register");
        pathResult.setStatus(StatusConfig.SUCCESS);
        return pathResult;
    }
}
