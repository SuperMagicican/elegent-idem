package cn.elegent.idem.checker;
import cn.elegent.idem.annotation.CheckerName;
import cn.elegent.idem.core.Checker;
import cn.elegent.idem.core.ElegentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@CheckerName("sn")
public class SnChecker implements Checker {

    @Autowired
    private ElegentStore elegentStore;


    public static final String key="elegent_iu_sn_";

    @Override
    public boolean check(String uniqueID) {
        return elegentStore.setIfAbsent(key+uniqueID, "-", 60);
    }

    @Override
    public void afterSuccess(String uniqueID) {

    }
}
