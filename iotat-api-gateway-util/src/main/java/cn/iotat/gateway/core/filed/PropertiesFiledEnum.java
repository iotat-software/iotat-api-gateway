package cn.iotat.gateway.core.filed;

public enum PropertiesFiledEnum {
    ROUTE_ID("routeId", "路由id"),
    PREDICATES("predicates", "断言集合"),
    URI("uri", "目标uri"),
    ORDER("order", "执行顺序"),
    FILTERS("filters", "过滤器集合"),
    ;

    private String name;
    private String des;

    PropertiesFiledEnum(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
