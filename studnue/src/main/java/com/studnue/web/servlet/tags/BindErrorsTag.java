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

package com.studnue.web.servlet.tags;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;

import com.studnue.validation.Errors;
import com.studnue.web.util.ExpressionEvaluationUtils;

/**
 * Evaluates content if there are bind errors for a certain bean.
 * Exports an "errors" variable of type Errors for the given bean.
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see BindTag
 */
public class BindErrorsTag extends RequestContextAwareTag {

	public static final String ERRORS_VARIABLE_NAME = "errors";

	private String name;

	/**
	 * Set the name of the bean that this tag should check.
	 */
	public void setName(String name) throws JspException {
		this.name = name;
	}

	protected int doStartTagInternal() throws ServletException, JspException {
		String resolvedName = ExpressionEvaluationUtils.evaluateString("name", name, pageContext);
		Errors errors = getRequestContext().getErrors(resolvedName, isHtmlEscape());
		if (errors != null && errors.hasErrors()) {
			this.pageContext.setAttribute(ERRORS_VARIABLE_NAME, errors);
			return EVAL_BODY_INCLUDE;
		}
		else {
			return SKIP_BODY;
		}
	}

}