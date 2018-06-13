package com.stuframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 类资源读取工具：
 *     1. 传入的path为类文件的相对路径，
 *     2. 对于  ClassPathResourc(String path)
 *        我们使用 Thread.currentThread().getContextClassLoader().getResource(this.path)获取资源
 *       此时，若path是“/” 开头，则会去掉该符号
 *    3. 对于 ClassPathResourc(String path,class clazz)
 *       此时，若path是“/” 开头，表示在从类路径开始查找文件
 *             若path不是“/” 开头，表示在从clazz类所在位置路径开始查找文件
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
    public InputStream getInputStream() throws IOException {
        InputStream in = null;
        if(null != this.clazz){
            in = this.clazz.getResourceAsStream(this.path);
        }else {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            in = classLoader.getResourceAsStream(this.path);
        }
        if(null == in){
            throw new FileNotFoundException("操作文件"+this.getDescription()+"失败");
        }
        return in;
    }

    @Override
    public URL getURL() throws IOException {
        URL url = null;
        if(this.clazz != null){
            url = this.clazz.getResource(this.path);
        }else {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            url = classLoader.getResource(this.path);
        }
        if(null == url){
            throw new FileNotFoundException("操作文件"+this.getDescription()+"失败");
        }
        return url;
    }

    @Override
    public File getFile() throws IOException {
        URL url = getURL();
        if(!this.URL_PROTOCOL_FILE.equals(url.getProtocol())){
            throw new FileNotFoundException(getDescription() + " 无法被解析为绝对路径，因为文件协议类型不正确 " +
                    "：URL=[" + url + "]");
        }
        return new File(URLDecoder.decode(url.getFile(),"UTF-8"));
    }

    @Override
    public String getDescription() {
        return "类文件资源 [" + this.path + "]";
    }

    public String toString() {
        return getDescription();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Resource && ((Resource) obj).getDescription().equals(getDescription()));
    }

    public int hashCode() {
        return getDescription().hashCode();
    }
}
