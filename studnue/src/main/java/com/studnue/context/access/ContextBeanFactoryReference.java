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

package com.studnue.context.access;

import com.studnue.beans.factory.BeanFactory;
import com.studnue.beans.factory.access.BeanFactoryReference;
import com.studnue.context.ApplicationContext;
import com.studnue.context.ConfigurableApplicationContext;

/**
 * ApplicationContext-specific implementation of BeanFactoryReference,
 * wrapping a newly created ApplicationContext, closing it on release.
 * @author Juergen Hoeller
 * @since 13.02.2004
 */
public class ContextBeanFactoryReference implements BeanFactoryReference {

	private final ApplicationContext applicationContext;

	public ContextBeanFactoryReference(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public BeanFactory getFactory() {
		return applicationContext;
	}

	public void release() {
		if (this.applicationContext instanceof ConfigurableApplicationContext) {
			((ConfigurableApplicationContext) this.applicationContext).close();
		}
	}

}