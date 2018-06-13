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

package com.studnue.aop.framework.adapter;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.Interceptor;
import com.studnue.aop.Advisor;
import com.studnue.aop.ThrowsAdvice;

/**
 * 
 * @author Rod Johnson
 * @version $Id: ThrowsAdviceAdapter.java,v 1.5 2004/03/19 16:54:41 johnsonr Exp $
 */
class ThrowsAdviceAdapter implements AdvisorAdapter {

	/**
	 * @see com.studnue.aop.framework.adapter.AdvisorAdapter#supportsAdvice(Object)
	 */
	public boolean supportsAdvice(Advice advice) {
		return advice instanceof ThrowsAdvice;
	}

	/**
	 * @see com.studnue.aop.framework.adapter.AdvisorAdapter#getInterceptor(com.studnue.aop.Advisor)
	 */
	public Interceptor getInterceptor(Advisor advisor) {
		return new ThrowsAdviceInterceptor(advisor.getAdvice());
	}

}