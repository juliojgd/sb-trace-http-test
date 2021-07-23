package com.example.traceforbidden1;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.boot.actuate.autoconfigure.web.server.ConditionalOnManagementPort;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@ManagementContextConfiguration
@ConditionalOnManagementPort(ManagementPortType.DIFFERENT)
@ConditionalOnWebApplication(type = Type.SERVLET)
public class TraceHttpBlockedManagementConfiguration {

    @Bean
    NoTraceHttpMethodBodyFilter notraceHttpFilter() {
        return new NoTraceHttpMethodBodyFilter();
    }

    @Bean
    public FilterRegistrationBean<NoTraceHttpMethodBodyFilter> managementNoTraceFilterRegistrationBean(
        final NoTraceHttpMethodBodyFilter filter) {
        // Tests were made, without this explicit registration in ManagementContext when different port is
        // launched
        // the filter is not called at all
        final FilterRegistrationBean<NoTraceHttpMethodBodyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(DispatcherType.ERROR);
        return registrationBean;
    }

    static class NoTraceHttpMethodBodyFilter extends HttpFilter {

        private static final long serialVersionUID = -5148586916028111963L;

        @Override
        protected void doFilter(final HttpServletRequest request, final HttpServletResponse response,
            final FilterChain chain) throws IOException, ServletException {
            if (HttpMethod.TRACE.toString().equals(request.getMethod())) {
                response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value(), "TRACE not allowed");
            } else {
                chain.doFilter(request, response);
            }
        }

    }
}
