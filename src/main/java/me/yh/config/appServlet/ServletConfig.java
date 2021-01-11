package me.yh.config.appServlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration //servlet-context 설정파일
@EnableWebMvc  //<mvc:annotation-driven> mvc bean 활성화
@ComponentScan(basePackages = {"me.yh.controller"}) //<context:component-scan base-package="me.yh.controller"/>
public class ServletConfig extends WebMvcConfigurerAdapter {
//Spring 5부터는 자바8을 사용하므로 WebMvcConfigurer 인터페이스를 구현하자.

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setOrder(1);
        registry.viewResolver(tilesViewResolver);

        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setOrder(2);
        registry.viewResolver(internalResourceViewResolver);
    }

    //타일즈 설정파일
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        //configurer.setDefinitions("classpath:tiles/*.xml");
        //설정 파일 위치 지정
        configurer.setDefinitions("/WEB-INF/tiles.xml");
        configurer.setCheckRefresh(true);
        return configurer;
    }

    //정적인 자료 접근 허용
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/static/");
    }

    //스프링이 처리하지 못한 경로에 대한 처리는 디폴트 서블릿에게 전달하여 처리하게 된다.
    //정적 리소스와 같은 특정 요청에 대해 컨테이너의 기본 서블릿을 사용하도록 Spring에 알리는데
    //위에 이미 정의 하였으므로 굳이 하지 안해도 됨
    //<mvc:default-servlet-handler>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //<mvc:message-converters> <!-- @ResponseBody로 응답할때 인코딩 -->
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream()
            .filter(converter -> converter instanceof StringHttpMessageConverter)
            .forEach(converter -> ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8));
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(10485760);  //한 파일당 업로드가 허용되는 최대 용량 바이트 단위 10mb
        resolver.setMaxUploadSize(52428800); //한 요청당 업로드가 허용되는 최대 용량 바이트 단위  50mb
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
}
