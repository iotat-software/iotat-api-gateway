package cn.iotat.gateway.util.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义日志输出工具，
 */
public final class LoggerUtil {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerUtil.class);
    private final String className;

    public LoggerUtil(Class c) {
        className = c.getName();
    }

    public void debug(String s, Object... o) {
        LOG.debug("{}," + s, className, o);
    }

    public void info(String s, Object... o) {
        LOG.info("{}," + s, className, o);
    }

    public void warn(String s, Object... o) {
        LOG.warn("{}," + s, className, o);
    }

    public void error(String s, Object... o) {
        LOG.error("{}," + s, className, o);
    }

}
