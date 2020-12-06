package cn.iotat.gateway.service.impl;

import cn.iotat.gateway.converter.RouteConverter;
import cn.iotat.gateway.dao.RouteDAO;
import cn.iotat.gateway.faced.api.RouteService;
import cn.iotat.gateway.faced.request.model.RouteOperationalRequest;
import cn.iotat.gateway.faced.response.BaseResponse;
import cn.iotat.gateway.faced.response.PageData;
import cn.iotat.gateway.faced.response.model.RouteInfo;
import cn.iotat.gateway.model.RouteModel;
import cn.iotat.gateway.util.route.DynamicRouteHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RouteServiceImpl implements RouteService {

    @Autowired
    private DynamicRouteHelper dynamicRouteHelper;
    @Autowired
    private RouteDAO routeDAO;


    @Override
    public Mono<BaseResponse<Boolean>> addNewRoute(RouteOperationalRequest request) {
        RouteDefinition routeDefinition = RouteConverter.routeRequest2RouteDefinition(request);
        RouteModel routeModel = RouteConverter.routeRequest2RouteModel(request, routeDefinition);
        if (routeDAO.addRoute(routeModel) < 1) {
            return Mono.create(e -> e.error(new Throwable()));
        }
        boolean result = dynamicRouteHelper.add(routeDefinition);
        return Mono.create(e -> e.success(BaseResponse.success(result)));

    }

    @Override
    public Mono<BaseResponse<Boolean>> updateRoute(RouteOperationalRequest request) {
        RouteDefinition routeDefinition = RouteConverter.routeRequest2RouteDefinition(request);
        RouteModel routeModel = RouteConverter.routeRequest2RouteModel(request, routeDefinition);
        if (routeDAO.updateRoute(routeModel) < 1) {
            return Mono.create(e -> e.error(new Throwable()));
        }
        boolean result = dynamicRouteHelper.update(routeDefinition);
        return Mono.create(e -> e.success(BaseResponse.success(result)));
    }

    @Override
    public Mono<BaseResponse<Boolean>> deleteRoute(RouteOperationalRequest request) {
        if (routeDAO.deleteRoute(request.getId(), request.getRouteId()) < 1) {
            return Mono.create(e -> e.error(new Throwable()));
        }
        boolean result = dynamicRouteHelper.delete(request.getRouteId());
        return Mono.create(e -> e.success(BaseResponse.success(result)));
    }

    @Override
    public BaseResponse<PageData<RouteInfo>> getAllRoutes(int pageNo, int pageSize) {
        List<RouteModel> routeList = routeDAO.getAllRoutes((pageNo - 1) * pageSize, pageSize);
        int totalRoute = routeDAO.getCountRoutes();
        List<RouteInfo> routeInfoList = RouteConverter.batchRouteModel2RouteInfo(routeList);
        return BaseResponse.pageResponse(routeInfoList, pageNo, pageSize, totalRoute);
    }

    @Override
    public Mono<BaseResponse<RouteInfo>> getRoute(String routeId) {
        RouteModel routeModel = routeDAO.getRouteByRouteId(routeId);
        RouteInfo routeInfo = RouteConverter.routeModel2RouteInfo(routeModel);
        return Mono.create(e -> e.success(BaseResponse.success(routeInfo)));
    }

    @Override
    public Mono<BaseResponse<Boolean>> refreshRoute() {
        int pageSize = routeDAO.getCountRoutes();
        int pageNo = 1;
        List<RouteModel> routeList = routeDAO.getAllRoutes((pageNo - 1) * pageSize, pageSize);
        List<RouteDefinition> routeDefinitions = RouteConverter.batchRouteModel2RouteDefinition(routeList);
        routeDefinitions.forEach(e -> {
            dynamicRouteHelper.update(e);
        });
        return Mono.create(e -> e.success(BaseResponse.success(true)));
    }

    @Override
    public Mono<BaseResponse<Boolean>> refreshRoute(String routeId) {
        RouteModel routeModel = routeDAO.getRouteByRouteId(routeId);
        RouteDefinition routeDefinition = RouteConverter.routeModel2RouteDefinition(routeModel);
        dynamicRouteHelper.update(routeDefinition);
        return Mono.create(e -> e.success(BaseResponse.success(true)));
    }
}
