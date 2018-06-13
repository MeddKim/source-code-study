package com.stuframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class UrlResource extends AbstractResource {

    public static final String PROTOCOL_FILE = "file";

    private final URL url;

    public UrlResource(URL url){
        this.url = url;
    }

    public UrlResource(String path) throws MalformedURLException {
        this.url = new URL(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return url.openStream();
    }


    @Override
    public URL getURL() throws IOException {
        return this.url;
    }

    @Override
    public File getFile() throws IOException {
        if(PROTOCOL_FILE.equals(this.url.getProtocol())){
            return new File(URLDecoder.decode(this.url.getFile(),"UTF-8"));
        }else {
            throw new FileNotFoundException(getDescription() + " 无法被解析为绝对路径，因为文件协议类型不正确 " +
                    "：URL=[" + url + "]");
        }
    }

    @Override
    public String getDescription() {
        return "URL [" + this.url + "]";
    }
}
