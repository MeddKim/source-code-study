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

package com.studnue.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 *资源描述符的接口，从实际抽象出来
 * 类型的资源，如文件或类路径资源。
 *
 * <p>An InputStream can be opened for every resource if it exists in
 * physical form, but a URL or File handle can just be returned for
 * certain resources. The actual behavior is implementation-specific.
 *
 * @author Juergen Hoeller
 * @since 28.12.2003
 */
public interface Resource extends InputStreamSource {

	/**
	 * 该资源是否存在于物理环境中
	 */
	boolean exists();

	/**
	 * 资源是否已经被打开，true的话应该立即关闭以避免resource leaks
	 * 对于所有常用资源描述符为 false
	 */
	boolean isOpen();

	/**
	 * 返回一个资源的url句柄
	 * @throws IOException 如果资源不能够被解析为URL抛出该以藏
	 * i.e. if the resource is not available as descriptor
	 */
	URL getURL() throws IOException;

	/**
	 * 返回该资源的文件句柄
	 * @throws IOException 如果该资源不能被解析绝对文件路径则抛出该异常
	 * file path, i.e. if the resource is not available in a file system
	 */
	File getFile() throws IOException;

	/**
	 * Return a description for this resource,
	 * to be used for error output when working with the resource.
	 * <p>Implementations are also encouraged to return this value
	 * from their toString method.
	 * @see Object#toString
	 */
	String getDescription();

}
