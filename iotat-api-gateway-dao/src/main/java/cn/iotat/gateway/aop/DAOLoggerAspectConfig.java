package cn.iotat.gateway.aop;

import cn.iotat.gateway.core.aop.AbstractAspectConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * DAO层日志输出配置
 *
 * @author pang
 */
@Aspect
@Component
public class DAOLoggerAspectConfig extends AbstractAspectConfig {
    /**
     * 普通日志，输出调用方法的入参、返回值
     */
    static final Logger LOG = LoggerFactory.getLogger(DAOLoggerAspectConfig.class);
    /**
     * 监控日志，输出调用方法的耗时
     */
    static final Logger LOG_MONITOR = LoggerFactory.getLogger("monitor");

    /**
     * 定义切面
     */
    @Pointcut("execution(* cn.iotat.gateway.dao.*DAO.*(..))")
    public void dalPoint() {
    }

    /**
     * 对切面进行操作
     *
     * @param point 切点
     * @return 切面方法执行的结果
     */
    @Around("dalPoint()")
    @Override
    public Object around(ProceedingJoinPoint point) {
        return printLog(LOG, LOG_MONITOR, point);
    }
}
