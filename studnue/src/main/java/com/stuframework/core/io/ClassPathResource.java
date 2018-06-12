package com.stuframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 类资源读取工具：
 *     1. 传入的path为类文件的相对路径，
 *     2. 对于  ClassPathResourc(String path)
 */
public class ClassPathResource extends AbstractResource {

    private String path;
    private Class clazz;

    public ClassPathResource(String path){
        if(path.startsWith("/")){
            path = path.substring(1);
        }
        this.path = path;
    }

    public ClassPathResource(String path,Class clazz){
        this.path = path;
        this.clazz = clazz;
    }

    @Override
    public InputStream getInputStream() {
        if(null != this.clazz)
        return null;
    }

    @Override
    public URL getURL() throws IOException {
        return null;
    }

    @Override
    public File getFile() throws IOException {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
