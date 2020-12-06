package cn.iotat.gateway.util.param;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数工具类，主要是用于切面获取参数
 * @author Pang
 * @date 2020/10/22
 */
public class ParamUtil {

    /**
     * 获取参数Map集合
     */
    public static Map<String, Object> getArgsMap(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        return param;
    }
}
