package cn.zhsit.authority.adapters;

import cn.zhsit.authority.interceptors.AuthorityInterceptor;
import cn.zhsit.authority.interceptors.CharacterEncodingInterceptor;
import cn.zhsit.authority.interceptors.DeviceInterceptor;
import cn.zhsit.authority.interceptors.LoginInterceptor;
import cn.zhsit.common.configs.ZhsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;


@Configuration
public class WebAppConfigAuthority extends WebMvcConfigurerAdapter {
    @Autowired
    private CharacterEncodingInterceptor characterEncodingInterceptor;
    @Autowired
    private DeviceInterceptor deviceInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private AuthorityInterceptor authorityInterceptor;
    @Autowired
    private ZhsConfig zhsConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(characterEncodingInterceptor);
        registry.addInterceptor(deviceInterceptor);
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/m/user/**","/m/**")
                .excludePathPatterns("/m/user/toregister","/m/user/tologin","/m/user/login","/m/user/register","/m/user/logout")
                .addPathPatterns("/p/**")
                .excludePathPatterns("/p/user/tologin","/p/user/login","/p/user/toregister","/p/user/register","/p/user/logout")
        ;
//        registry.addInterceptor(loginInterceptor)//轮询状态的需要权限，是否需要算做一次操作？
//                .addPathPatterns("/**", "")
//                .excludePathPatterns(zhsConfig.getLoginExcludeUrl().toArray(new String[zhsConfig.getLoginExcludeUrl().size()]));
//        registry.addInterceptor(authorityInterceptor).addPathPatterns("/**", "");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/tm/**").setCachePeriod(10).addResourceLocations("classpath:/templates/");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/p/**").addResourceLocations("classpath:/pc/");
//        registry.addResourceHandler("/m/**").addResourceLocations("classpath:/mobile/");

        registry.addResourceHandler("/templates/pc/css/**").addResourceLocations("classpath:/templates/pc/css/");
        registry.addResourceHandler("/templates/pc/fonts/**").addResourceLocations("classpath:/templates/pc/fonts/");
        registry.addResourceHandler("/templates/pc/img/**").addResourceLocations("classpath:/templates/pc/img/");
        registry.addResourceHandler("/templates/pc/js/**").addResourceLocations("classpath:/templates/pc/js/");
        registry.addResourceHandler("/templates/pc/layer/**").addResourceLocations("classpath:/templates/pc/layer/");

        registry.addResourceHandler("/templates/app/css/**").addResourceLocations("classpath:/templates/app/css/");
        registry.addResourceHandler("/templates/app/fonts/**").addResourceLocations("classpath:/templates/app/fonts/");
        registry.addResourceHandler("/templates/app/img/**").addResourceLocations("classpath:/templates/app/img/");
        registry.addResourceHandler("/templates/app/js/**").addResourceLocations("classpath:/templates/app/js/");
        registry.addResourceHandler("/templates/app/layer_mobile/**").addResourceLocations("classpath:/templates/app/layer_mobile/");
//        registry.addResourceHandler("/templates/app/layer_mobile/need/**").addResourceLocations("classpath:/templates/app/layer_mobile/need/");
        super.addResourceHandlers(registry);
    }

//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("app/index");
//        super.addViewControllers(registry);
//    }

    //显示声明CommonsMultipartResolver为mutipartResolver
//    @Bean(name = "multipartResolver")
//    public MultipartResolver multipartResolver(){
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
////        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在UploadAction中捕获文件大小异常
//        resolver.setMaxInMemorySize(10*1024*1024);//10M
//        resolver.setMaxUploadSize(150*1024*1024);//上传文件大小 150M 150*1024*1024
//        return resolver;
//    }


    public CharacterEncodingInterceptor getCharacterEncodingInterceptor() {
        return characterEncodingInterceptor;
    }

    public DeviceInterceptor getDeviceInterceptor() {
        return deviceInterceptor;
    }

    public LoginInterceptor getLoginInterceptor() {
        return loginInterceptor;
    }

    public AuthorityInterceptor getAuthorityInterceptor() {
        return authorityInterceptor;
    }

    public ZhsConfig getZhsConfig() {
        return zhsConfig;
    }
}
