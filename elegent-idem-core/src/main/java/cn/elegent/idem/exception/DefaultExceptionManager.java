package cn.elegent.idem.exception;
import cn.elegent.idem.core.ExceptionManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultExceptionManager implements ExceptionManager {

    /**
     * 出现重复请求的时候封装的处理逻辑
     */
    @Override
    public void errorHandler() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        try {
            response.getWriter().print("request again");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
