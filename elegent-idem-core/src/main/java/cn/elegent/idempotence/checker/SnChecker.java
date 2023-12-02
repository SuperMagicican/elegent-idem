package cn.elegent.idempotence.checker;

import cn.elegent.data.ElegentData;
import cn.elegent.idempotence.annotation.CheckerName;
import cn.elegent.idempotence.core.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@CheckerName("sn")
public class SnChecker implements Checker {

    @Autowired
    private ElegentData elegentData;


    public static final String key="elegent_iu_sn_";

    @Override
    public boolean check(String uniqueID) {
        return elegentData.setIfAbsent(key+uniqueID, "-", 60);
    }

    @Override
    public void afterSuccess(String uniqueID) {

    }
}
