package cn.iotat.gateway.faced.api;

import cn.iotat.gateway.faced.request.model.RouteOperationalRequest;
import cn.iotat.gateway.faced.response.BaseResponse;
import cn.iotat.gateway.faced.response.PageData;
import cn.iotat.gateway.faced.response.model.RouteInfo;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/iotat-api-gateway/route")
public interface RouteService {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    Mono<BaseResponse<Boolean>> addNewRoute(@RequestBody RouteOperationalRequest request);
/*
{
    "routeId": "route_test",
    "predicates": [
        {
            "name": "Path",
            "args": {
                "pattern": "/api/v1/item/all"
            }
        }
    ],
    "uri": "lb://producer-demo",
    "order": 1,
    "appName": "producer-demo",
    "routeDes": "测试网管"
}
*/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Mono<BaseResponse<Boolean>> updateRoute(@RequestBody RouteOperationalRequest request);

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Mono<BaseResponse<Boolean>> deleteRoute(@RequestBody RouteOperationalRequest request);

    @RequestMapping(value = "/get/list", method = RequestMethod.GET)
    BaseResponse<PageData<RouteInfo>> getAllRoutes(@RequestParam int pageNo, @RequestParam int pageSize);

    @RequestMapping(value = "/get/{routeId}", method = RequestMethod.GET)
    Mono<BaseResponse<RouteInfo>> getRoute(@PathVariable String routeId);

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    Mono<BaseResponse<Boolean>> refreshRoute();

    @RequestMapping(value = "/refresh/{routeId}", method = RequestMethod.GET)
    Mono<BaseResponse<Boolean>> refreshRoute(@PathVariable String routeId);
}
