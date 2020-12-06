package cn.iotat.gateway.dao;

import cn.iotat.gateway.model.RouteModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteDAO {
    int deleteRoute(@Param("id") long id, @Param("routeId") String routeId);

    int addRoute(RouteModel record);

    RouteModel getRouteByRouteId(String routeId);

    int updateRoute(RouteModel record);

    List<RouteModel> getAllRoutes(@Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    int getCountRoutes();
}