package cn.iotat.gateway.model;

import cn.iotat.gateway.core.model.ToString;

import java.util.Date;

/**
 * t_api_route
 *
 * @author
 */
public class RouteModel extends ToString {
    private static final long serialVersionUID = -1794408926231423168L;
    /**
     * 自增id
     */
    private long id;

    /**
     * 项目名，同spirng.application.name
     */
    private String appName;

    /**
     * routeId，唯一
     */
    private String routeId;

    /**
     * 路由描述
     */
    private String routeDes;

    /**
     * 参数描述，可省略
     */
    private String paramInfo;

    /**
     * 详细配置，以json格式存储
     */
    private String propertiesInfo;

    private Date createTime;

    private Date modifyTIme;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteDes() {
        return routeDes;
    }

    public void setRouteDes(String routeDes) {
        this.routeDes = routeDes;
    }

    public String getParamInfo() {
        return paramInfo;
    }

    public void setParamInfo(String paramInfo) {
        this.paramInfo = paramInfo;
    }

    public String getPropertiesInfo() {
        return propertiesInfo;
    }

    public void setPropertiesInfo(String propertiesInfo) {
        this.propertiesInfo = propertiesInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTIme() {
        return modifyTIme;
    }

    public void setModifyTIme(Date modifyTIme) {
        this.modifyTIme = modifyTIme;
    }
}