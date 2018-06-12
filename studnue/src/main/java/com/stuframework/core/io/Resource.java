package com.stuframework.core.io;


import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 资源抽象接口
 *   这里的资源指就是各种不同的协议定位定位到的文件
 */
public interface Resource {
    boolean exists();
    boolean isOpen();
    URL getURL() throws IOException;
    File getFile() throws IOException;
    String getDescription();
}
