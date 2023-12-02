package cn.elegent.idem.controller;
import cn.elegent.idem.core.ElegentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class TokenController {


    @Autowired
    private ElegentStore elegentStore;

    @GetMapping("/iu_token")
    public String createToken(){
        //生成topken
        String token = UUID.randomUUID().toString();
        //存储token 使用HASH
        elegentStore.hset("elegent_iu_token",token,LocalDateTime.now().toString());
        return token;
    }

}
