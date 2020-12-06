package cn.iotat.gateway.util.route;

import cn.iotat.gateway.util.log.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DynamicRouteHelper implements ApplicationEventPublisherAware {
    private static final LoggerUtil LOG = new LoggerUtil(DynamicRouteHelper.class);

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
    /**
     * 添加新路由
     *
     * @param routeDefinition 路由信息
     * @return 成功
     */
    public boolean add(RouteDefinition routeDefinition) {
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return true;
    }

    /**
     * 更新路由
     *
     * @param routeDefinition 路由消息
     * @return 成功
     */
    public boolean update(RouteDefinition routeDefinition) {
        try {
            if (!delete(routeDefinition.getId())) {
                LOG.error("删除路由失败,routeDefinition={}",routeDefinition);
                return false;
            }
        } catch (Exception e) {
            LOG.error("删除路由失败，有异常,routeDefinition={}",routeDefinition,e);
            return false;
        }
        try {
            return add(routeDefinition);
        } catch (Exception e) {
            LOG.error("添加路由失败，有异常,routeDefinition={}",routeDefinition,e);
            return false;
        }
    }

    /**
     * 删除路由
     *
     * @param id 要删除的路由id
     * @return 删除是否成功
     */
    public boolean delete(String id) {
        Mono<Boolean> booleanMono = routeDefinitionWriter.delete(Mono.just(id))
                .then(Mono.defer(() -> Mono.just(Boolean.TRUE)))
                .onErrorResume(err -> err instanceof NotFoundException, err -> Mono.just(Boolean.FALSE));
        return true;
    }
}
