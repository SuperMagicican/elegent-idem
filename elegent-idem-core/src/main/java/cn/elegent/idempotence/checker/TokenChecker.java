package cn.elegent.idempotence.checker;

import cn.elegent.data.ElegentData;
import cn.elegent.idempotence.annotation.CheckerName;
import cn.elegent.idempotence.core.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@CheckerName("token")
public class TokenChecker implements Checker {

    public static final String key="elegent_iu_token";

    @Autowired
    private ElegentData elegentData;


    @Override
    public boolean check(String uniqueID) {
        Object value = elegentData.hget(TokenChecker.key, uniqueID);
        if(value==null){  //不能查到
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void afterSuccess(String uniqueID) {
        elegentData.hdel(TokenChecker.key,uniqueID);
    }

}