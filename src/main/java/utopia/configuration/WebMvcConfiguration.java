package utopia.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import utopia.interceptor.LoggerInterceptor;
import utopia.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
		registry.addInterceptor(new LoginCheckInterceptor());	
		
	}
}
