/*
 * Created on Jan 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
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

import com.studnue.beans.BeansException;
import com.studnue.beans.factory.access.BeanFactoryReference;
import com.studnue.beans.factory.access.JndiBeanFactoryLocator;
import com.studnue.context.support.ClassPathXmlApplicationContext;

/**
 * Subclass of JndiBeanFactoryLocator which creates a
 * ClassPathXmlApplicationContext instead of a BeanFactory.
 * @author Colin Sampaleanu
 * @version $Id: ContextJndiBeanFactoryLocator.java,v 1.2 2004/03/18 02:46:13 trisberg Exp $
 */
public class ContextJndiBeanFactoryLocator extends JndiBeanFactoryLocator {

	protected BeanFactoryReference createBeanFactory(String[] resources) throws BeansException {
		return new ContextBeanFactoryReference(new ClassPathXmlApplicationContext(resources));
	}

}
