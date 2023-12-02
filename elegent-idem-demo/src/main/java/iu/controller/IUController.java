package iu.controller;
import cn.elegent.idem.annotation.ElegentIdem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IUController {

    @GetMapping("/testiu")
    @ElegentIdem(type="sn" ,name="requestId")
    public String testiu() throws InterruptedException {
        log.info("finish ...");
        return "OK";
    }

}
