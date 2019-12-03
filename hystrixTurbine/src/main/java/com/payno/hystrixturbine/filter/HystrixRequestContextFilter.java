package com.payno.hystrixturbine.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author payno
 * @date 2019/12/3 09:01
 * @description
 *      注册HystrixContext，保证跨线程的信息共享
 */
@Component
@WebFilter(filterName = "hystrixRequestContextFilter", urlPatterns = "/*", asyncSupported = true)
public class HystrixRequestContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        filterChain.doFilter(servletRequest, servletResponse);
        context.close();
    }
}
