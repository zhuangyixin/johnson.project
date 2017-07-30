package com.johnson.spring.mvc.download.configure;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Component
public class ViewResolver extends InternalResourceViewResolver {
	private static final String PREFIX = "/page/";
	private static final String SUFFIX = ".jsp";

	public ViewResolver() {
		super(PREFIX, SUFFIX);
	}
}
