package me.yh.config;

import me.yh.config.appServlet.ServletConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ServletConfig.class};
    }

    // DispatcherServlet 이 매핑되기 위한 경로를 지정한다.
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //onStartUp은 AbstractContextLoaderInitializer 에서 이미 정의 되어 있음

    //인코딩 필터 설정
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        return new Filter[]{encodingFilter, new HiddenHttpMethodFilter()};
    }
}
