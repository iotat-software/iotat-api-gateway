package cn.iotat.gateway.faced.request;

import cn.iotat.gateway.core.model.ToString;

/**
 * 基础请求，该类包含一些请求的公共属性
 * 注意，所有非GET的方法均需要以该类为参数
 *
 * @author Pang
 */
public abstract class BaseRequest extends ToString {
    private static final long serialVersionUID = -587145464732137914L;

}
