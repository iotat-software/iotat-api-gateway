package cn.iotat.gateway.converter;

import cn.iotat.gateway.faced.detail.filter.FilterDetail;
import cn.iotat.gateway.faced.detail.predicate.PredicateDetail;
import cn.iotat.gateway.faced.request.model.RouteOperationalRequest;
import cn.iotat.gateway.faced.response.model.RouteInfo;
import cn.iotat.gateway.model.RouteModel;
import com.alibaba.fastjson.JSON;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RouteConverter {
    /**
     * 请求结构体转化为spring内部结构体
     *
     * @param request 请求结构体
     * @return spring内部结构体
     */
    public static RouteDefinition routeRequest2RouteDefinition(RouteOperationalRequest request) {
        List<FilterDefinition> filters = request.getFilters().stream().map(e -> {
            FilterDefinition filter = new FilterDefinition();
            filter.setName(e.getName());
            filter.setArgs(e.getArgs());
            return filter;
        }).collect(Collectors.toList());
        List<PredicateDefinition> predicates = request.getPredicates().stream().map(e -> {
            PredicateDefinition predicate = new PredicateDefinition();
            predicate.setArgs(e.getArgs());
            predicate.setName(e.getName());
            return predicate;
        }).collect(Collectors.toList());
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setFilters(filters);
        routeDefinition.setId(request.getRouteId());
        routeDefinition.setOrder(request.getOrder());
        routeDefinition.setPredicates(predicates);
        routeDefinition.setUri(URI.create(request.getUri()));
        return routeDefinition;
    }

    /**
     * 请求结构体转化为数据库结构体
     *
     * @param request 请求结构体
     * @return 数据库结构体
     */
    public static RouteModel routeRequest2RouteModel(RouteOperationalRequest request, RouteDefinition routeDefinition) {
        RouteModel routeModel = new RouteModel();
        routeModel.setId(request.getId());
        routeModel.setAppName(request.getAppName());
        routeModel.setRouteId(request.getRouteId());
        routeModel.setRouteDes(request.getRouteDes());
        routeModel.setPropertiesInfo(JSON.toJSONString(routeDefinition));
        Date now = new Date();
        routeModel.setCreateTime(now);
        routeModel.setModifyTIme(now);
        return routeModel;
    }

    /**
     * 将数据库结构体转化为spring内部结构体
     *
     * @param routeModel 数据库结构体
     * @return spring内部结构体
     */
    public static RouteDefinition routeModel2RouteDefinition(RouteModel routeModel) {
        String propertiesJson = routeModel.getPropertiesInfo();
        return JSON.parseObject(propertiesJson, RouteDefinition.class);
    }

    /**
     * 批量转换
     *
     * @param routeModelList 数据库结构体列表
     * @return spring内部结构体列表
     */
    public static List<RouteDefinition> batchRouteModel2RouteDefinition(List<RouteModel> routeModelList) {
        return routeModelList.stream().map(RouteConverter::routeModel2RouteDefinition).collect(Collectors.toList());
    }

    /**
     * 将spring内置过滤器配置结构转化为自定义结构
     *
     * @param filterDefinitionList spring内置过滤器配置列表
     * @return 自定义过滤器配置列表
     */
    private static List<FilterDetail> batchFilterDefinition2FilterDetail(List<FilterDefinition> filterDefinitionList) {
        return filterDefinitionList.stream().map(e -> {
            FilterDetail filterDetail = new FilterDetail();
            filterDetail.setArgs(e.getArgs());
            filterDetail.setName(e.getName());
            return filterDetail;
        }).collect(Collectors.toList());
    }

    /**
     * 将数据库结构转化为前端展示结构
     *
     * @param routeModel 数据库结构
     * @return 前端展示结构
     */
    public static RouteInfo routeModel2RouteInfo(RouteModel routeModel) {
        RouteInfo routeInfo = new RouteInfo();
        RouteDefinition routeDefinition = routeModel2RouteDefinition(routeModel);
        routeInfo.setCreateTime(routeModel.getCreateTime());
        routeInfo.setId(routeModel.getId());
        routeInfo.setModifyTime(routeModel.getCreateTime());
        routeInfo.setRouteId(routeModel.getRouteId());
        routeInfo.setRouteDes(routeModel.getRouteDes());
        routeInfo.setAppName(routeModel.getAppName());
        routeInfo.setFilters(batchFilterDefinition2FilterDetail(routeDefinition.getFilters()));
        routeInfo.setPredicates(batchPredicateDefinition2PredicateDetail(routeDefinition.getPredicates()));
        routeInfo.setUri(routeDefinition.getUri().toString());
        routeInfo.setOrder(routeDefinition.getOrder());
        return routeInfo;
    }

    /**
     * 批量转换
     */
    public static List<RouteInfo> batchRouteModel2RouteInfo(List<RouteModel> routeModelList) {
        return routeModelList.stream().map(RouteConverter::routeModel2RouteInfo).collect(Collectors.toList());
    }

    /**
     * 将spring内置断言配置结构转化为自定义结构
     *
     * @param predicateDefinitionList spring内置断言配置列表
     * @return 自定义断言配置列表
     */
    private static List<PredicateDetail> batchPredicateDefinition2PredicateDetail(List<PredicateDefinition> predicateDefinitionList) {
        return predicateDefinitionList.stream().map(e -> {
            PredicateDetail predicateDetail = new PredicateDetail();
            predicateDetail.setArgs(e.getArgs());
            predicateDetail.setName(e.getName());
            return predicateDetail;
        }).collect(Collectors.toList());
    }
}
