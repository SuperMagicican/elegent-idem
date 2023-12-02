package cn.elegent.idempotence.unique;
import cn.elegent.idempotence.core.UniqueID;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * DefaultRequestIdByHeader
 * @description 在请求头里获取唯一id的方法
 * @author WGL
 * @date 2022/11/11 9:43
*/
public class DefaultUniqueID implements UniqueID {

    /**
     * 从请求头里获取唯一id的方法
     * @return
     */
    @Override
    public String getUniqueID(String  name) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestId = request.getHeader(name);
        return requestId;
    }
}
