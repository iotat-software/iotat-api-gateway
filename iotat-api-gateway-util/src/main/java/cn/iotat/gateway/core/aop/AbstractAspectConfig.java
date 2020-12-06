package cn.iotat.gateway.core.aop;

import cn.iotat.gateway.util.json.JsonUtil;
import cn.iotat.gateway.util.param.ParamUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

import java.util.Map;

/**
 * 日志配置操作的父类
 *
 * @author pang
 */
public abstract class AbstractAspectConfig {

    /**
     * 切点操作
     *
     * @param point 切点
     * @return 返回的结果
     */
    public abstract Object around(ProceedingJoinPoint point);

    /**
     * 打印日志
     *
     * @param LOG         普通日志
     * @param LOG_MONITOR 监控日志
     * @param point       切点
     * @return 切点执行的结果
     */
    protected Object printLog(final Logger LOG, final Logger LOG_MONITOR, ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        // 类名
        String className = point.getSignature().getDeclaringTypeName();
        // 方法名
        String methodName = point.getSignature().getName();
        // 参数
        Map<String, Object> argsMap = ParamUtil.getArgsMap(point);
        // 结果
        Object result = null;
        try {
            result = point.proceed();
            LOG.info("{}#{},args={},result={}", className, methodName, JsonUtil.map2JsonStr(argsMap), result);
        } catch (Throwable e) {
            LOG.error("{}#{},args={}", className, methodName, JsonUtil.map2JsonStr(argsMap), e);
        } finally {
            long time = System.currentTimeMillis() - start;
            LOG_MONITOR.info("{}#{}|{}", className, methodName, time);
        }
        return result;
    }
}
