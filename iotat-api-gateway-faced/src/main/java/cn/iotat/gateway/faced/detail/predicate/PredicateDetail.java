package cn.iotat.gateway.faced.detail.predicate;

import cn.iotat.gateway.core.model.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 断言细节
 */
public class PredicateDetail extends ToString {
    private static final long serialVersionUID = -7536440759566652441L;
    /**
     * 断言名
     */
    private String name;
    /**
     * 断言规则
     */
    private Map<String,String> args = new HashMap<>();

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
