package cn.iotat.gateway.faced.common;

/**
 * 在实际生产活动中，该错误码应该由统一的平台进行管理，需要创建新的错误码则向平台进行申请，
 * 以便于企业进行错误码统计管理以及减少不同业务部门之间的障碍
 * 另外，错误码一般分为几种，包括但不限于下面注释的几种
 *
 * @author pang
 */
public enum ErrorCodeEnum{
    //=========== 参数错误(1xxx) ===========
    /**
     * 必填参数没有填写
     */
    HAVE_NO_ID("1000","param 'id' is required"),
    /**
     * 路由的id和route_id不匹配
     */
    NOT_FOUND_ROUTE("1001","not found route,check if id and route_id matched"),
    //=========== 内部错误(2xxx) ===========

    //=========== 网络错误(3xxx) ===========

    //=========== 业务错误(4xxx) ===========
    /**
     * 由于开关关闭导致访问失败
     */
    SWITCH_OFF("4000","switch is off")
    ;

    ErrorCodeEnum(String errCode, String errMsg) {
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    private String errMsg;

    private String errCode;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"errMsg\":\"")
                .append(errMsg).append('\"');
        sb.append(",\"errCode\":\"")
                .append(errCode).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
