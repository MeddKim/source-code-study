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

package com.studnue.web.servlet.view.velocity;

import java.util.Locale;

import com.studnue.web.servlet.View;
import com.studnue.web.servlet.view.UrlBasedViewResolver;

/**
 * Convenience subclass of UrlBasedViewResolver that supports VelocityView
 * (i.e. Velocity templates) and custom subclasses of it.
 *
 * <p>The view class for all views generated by this resolver can be specified
 * via setViewClass. See UrlBasedViewResolver's javadocs for details.
 *
 * @author Juergen Hoeller
 * @since 13.12.2003
 * @see #setViewClass
 * @see #setPrefix
 * @see #setSuffix
 * @see #setRequestContextAttribute
 * @see #setVelocityFormatterAttribute
 * @see #setDateToolAttribute
 * @see #setNumberToolAttribute
 * @see VelocityView
 */
public class VelocityViewResolver extends UrlBasedViewResolver {

	private String velocityFormatterAttribute;

	private String dateToolAttribute;

	private String numberToolAttribute;

	/**
	 * Sets default viewClass to VelocityView.
	 * @see #setViewClass
	 */
	public VelocityViewResolver() {
		setViewClass(VelocityView.class);
	}

	/**
	 * Requires VelocityView.
	 * @see VelocityView
	 */
	protected Class requiredViewClass() {
		return VelocityView.class;
	}

	/**
	 * Set the name of the VelocityFormatter helper object to expose in the
	 * Velocity context of this view, or null if not needed.
	 * VelocityFormatter is part of the standard Velocity distribution.
	 * @see org.apache.velocity.app.tools.VelocityFormatter
	 */
	public void setVelocityFormatterAttribute(String velocityFormatterAttribute) {
		this.velocityFormatterAttribute = velocityFormatterAttribute;
	}

	/**
	 * Set the name of the DateTool helper object to expose in the Velocity context
	 * of this view, or null if not needed. DateTool is part of Velocity Tools 1.0.
	 * @see org.apache.velocity.tools.generic.DateTool
	 */
	public void setDateToolAttribute(String dateToolAttribute) {
		this.dateToolAttribute = dateToolAttribute;
	}

	/**
	 * Set the name of the NumberTool helper object to expose in the Velocity context
	 * of this view, or null if not needed. NumberTool is part of Velocity Tools 1.1.
	 * @see org.apache.velocity.tools.generic.NumberTool
	 */
	public void setNumberToolAttribute(String numberToolAttribute) {
		this.numberToolAttribute = numberToolAttribute;
	}

	protected View loadView(String viewName, Locale locale) {
		VelocityView view = (VelocityView) super.loadView(viewName, locale);
		view.setVelocityFormatterAttribute(this.velocityFormatterAttribute);
		view.setDateToolAttribute(this.dateToolAttribute);
		view.setNumberToolAttribute(this.numberToolAttribute);
		return view;
	}

}
