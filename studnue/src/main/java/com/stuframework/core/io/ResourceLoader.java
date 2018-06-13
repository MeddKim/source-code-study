package com.stuframework.core.io;

/**
 * 资源加载工具
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
