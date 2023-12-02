package cn.elegent.idem.checker;
import cn.elegent.idem.annotation.CheckerName;
import cn.elegent.idem.core.Checker;
import cn.elegent.idem.core.ElegentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@CheckerName("token")
public class TokenChecker implements Checker {

    public static final String key="elegent_iu_token";

    @Autowired
    private ElegentStore elegentStore;


    @Override
    public boolean check(String uniqueID) {
        Object value = elegentStore.hget(TokenChecker.key, uniqueID);
        if(value==null){  //不能查到
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void afterSuccess(String uniqueID) {
        elegentStore.hdel(TokenChecker.key,uniqueID);
    }

}