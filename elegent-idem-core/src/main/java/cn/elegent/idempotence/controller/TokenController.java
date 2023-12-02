package cn.elegent.idempotence.controller;

import cn.elegent.data.ElegentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class TokenController {


    @Autowired
    private ElegentData elegentData;

    @GetMapping("/iu_token")
    public String createToken(){
        //生成topken
        String token = UUID.randomUUID().toString();
        //存储token 使用HASH
        elegentData.hset("elegent_iu_token",token,LocalDateTime.now().toString());
        return token;
    }

}
