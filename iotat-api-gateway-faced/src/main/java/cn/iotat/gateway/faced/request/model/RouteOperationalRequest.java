package cn.iotat.gateway.faced.request.model;

import cn.iotat.gateway.faced.detail.filter.FilterDetail;
import cn.iotat.gateway.faced.detail.predicate.PredicateDetail;
import cn.iotat.gateway.faced.request.BaseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 路由操作请求
 */
public class RouteOperationalRequest extends BaseRequest {
    private static final long serialVersionUID = -8989730538917297202L;
    /**
     * 数据表id
     */
    private long id;
    /**
     * 路由描述
     */
    private String routeDes;
    /**
     * 所属应用
     */
    private String appName;
    /**
     * 路由id
     */
    private String routeId;
    /**
     * 路由断言配置
     */
    private List<PredicateDetail> predicates = new ArrayList<>();
    /**
     * 路由过滤器配置
     */
    private List<FilterDetail> filters = new ArrayList<>();
    /**
     * 转发的目标url
     */
    private String uri;
    /**
     * 路由执行顺序
     */
    private int order = 0;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public List<PredicateDetail> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<PredicateDetail> predicates) {
        this.predicates = predicates;
    }

    public List<FilterDetail> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterDetail> filters) {
        this.filters = filters;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRouteDes() {
        return routeDes;
    }

    public void setRouteDes(String routeDes) {
        this.routeDes = routeDes;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
