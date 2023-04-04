package com.example.demo.filter.one;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author WuYingBin
 * date: 2023/4/3
 */
@WebFilter(filterName = "filter-one", urlPatterns = "/*")
public class FilterOne implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Web容器（如Tomcat）初始化Filter时调用，一般用于初始化一些资源
        Filter.super.init(filterConfig);
        System.out.println("Filter过滤器1号资源初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //执行Filter逻辑
        System.out.println("这是Filter过滤器1号");
        //让请求继续进入Filter链的下一个节点
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //Web容器（如Tomcat）销毁Filter时调用，一般用于资源关闭
        Filter.super.destroy();
        System.out.println("Filter过滤器1号资源释放");
    }
}
