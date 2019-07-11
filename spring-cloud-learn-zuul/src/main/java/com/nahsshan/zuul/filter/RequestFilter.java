package com.nahsshan.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;

/**
 * RequestFilter
 * @author J.zhu
 * @date 2019/7/12
 */
public class RequestFilter extends ZuulFilter {

    /**
     * 这里可以处理逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        System.out.println(String.format( request.getMethod(), request.getRequestURL().toString()));
        // 对该请求进行路由
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        // 设值，让下一个Filter看到上一个Filter的状态
        ctx.set("isSuccess", true);
        return null;
    }

    /**
     * 前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 优先级为0，数字越大，优先级越低
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器 false为不执行
     * @return boolean
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
