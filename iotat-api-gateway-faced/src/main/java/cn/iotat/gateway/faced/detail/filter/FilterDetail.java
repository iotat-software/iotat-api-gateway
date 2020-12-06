package cn.iotat.gateway.faced.detail.filter;

import cn.iotat.gateway.core.model.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 过滤器细节
 */
public class FilterDetail extends ToString {
    private static final long serialVersionUID = 3456059141405915684L;
    /**
     * 过滤器名
     */
    private String name;
    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
