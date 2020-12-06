package cn.iotat.gateway.core.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public abstract class ToString implements Serializable {
    private static final long serialVersionUID = 7730763402528350944L;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
