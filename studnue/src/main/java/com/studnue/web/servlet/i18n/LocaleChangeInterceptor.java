/*
 * Copyright 2002-2004 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 

package com.studnue.web.servlet.i18n;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.studnue.beans.propertyeditors.LocaleEditor;
import com.studnue.web.servlet.LocaleResolver;
import com.studnue.web.servlet.handler.HandlerInterceptorAdapter;
import com.studnue.web.servlet.support.RequestContextUtils;

/**
 * Interceptor that allows for changing the current locale on every request,
 * via a configurable request parameter.
 * @author Juergen Hoeller
 * @since 20.06.2003
 * @see com.studnue.web.servlet.LocaleResolver
 */
public class LocaleChangeInterceptor extends HandlerInterceptorAdapter {

	public static final String DEFAULT_PARAM_NAME = "locale";

	private String paramName = DEFAULT_PARAM_NAME;

	/**
	 * Set the name of the parameter that contains a locale specification
	 * in a locale change request. Default is "locale".
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		String newLocale = request.getParameter(this.paramName);
		if (newLocale != null) {
			LocaleEditor localeEditor = new LocaleEditor();
			localeEditor.setAsText(newLocale);
			localeResolver.setLocale(request, response, (Locale) localeEditor.getValue());
		}
		// proceed in any case
		return true;
	}

}
