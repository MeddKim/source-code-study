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

package com.studnue.web.servlet.mvc.multiaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Exception thrown when there's no request handling method for
 * this request.
 * @author Rod Johnson
 */
public class NoSuchRequestHandlingMethodException extends ServletException {
	
	private String name;
	
	public NoSuchRequestHandlingMethodException(HttpServletRequest request) {
		super("No handling method can be found for request [" + request + "]");
	}
	
	public NoSuchRequestHandlingMethodException(String name, MultiActionController multiActionRequestController) {
		super(
			"No request handling method with name '" + name + "' in class " + multiActionRequestController.getClass().getName());
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
