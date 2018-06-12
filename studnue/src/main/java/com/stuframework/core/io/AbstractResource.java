package com.stuframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Resource接口的实现类该实现类实现的有意义的方法有：
 *      exists()   File.exists()或者能读到InputStream()
 *
 */
public abstract class AbstractResource implements Resource{

    //默认资源URL协议
    private static final String URL_PROTOCOL_FILE = "file";

    @Override
    public boolean exists() {
        try {
            File file = getFile();
            return file.exists();
        } catch (IOException e) {
            try {
                InputStream inputStream = getInputStream();
                inputStream.close();
                return true;
            } catch (IOException e1) {
                return false;
            }
        }
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    public abstract InputStream getInputStream();
}
