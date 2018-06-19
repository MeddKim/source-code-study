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

package com.studnue.web.multipart.support;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.studnue.web.multipart.MultipartFile;

/**
 * Custom PropertyEditor for converting MultipartFiles to byte arrays.
 * @author Juergen Hoeller
 * @since 13.10.2003
 */
public class ByteArrayMultipartFileEditor extends PropertyEditorSupport {

	protected final Log logger = LogFactory.getLog(getClass());

	public void setValue(Object value) {
		if (value instanceof MultipartFile) {
			MultipartFile multipartFile = (MultipartFile) value;
			try {
				super.setValue(multipartFile.getBytes());
			}
			catch (IOException ex) {
				logger.error("Cannot read contents of multipart file", ex);
				throw new IllegalArgumentException("Cannot read contents of multipart file: " + ex.getMessage());
			}
		}
	}

}
