package com.stuframework.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类加载器
 */
public final class ClassUtil {

    private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);
    /**
     * 获取类加载器
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     *
     * @param className
     * @param isInitialized 是否初始化（是否执行类的静态代码块）
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            log.error("load class failure",e);
            throw  new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 加载类（默认将初始化类）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
     * 获取指定包名下的所有类
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url != null){
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")){
                        String packagePath = url.getPath().replaceAll("%20","");
                        addClass(classSet, packagePath, packageName);
                    }else {
                        JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
                        if(jarURLConnection != null){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(jarFile != null){
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()){
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0,jarEntryName.lastIndexOf("."))
                                                .replaceAll(".","/");
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            log.error("get class set failure",e);
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 找出一个目录下的所有类
     * @param classSet
     * @param packagePath
     * @param packakgeName
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packakgeName){
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            //我们只需要.class类型的文件和目录（用于继续遍历）
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class"))
                        || file.isDirectory();
            }
        });
        for(File file : files){
            String fileName = file.getName();
            if(file.isFile()){
                String className = fileName.substring(0,fileName.lastIndexOf("."));
                if(StringUtil.isNotEmpty(packakgeName)){
                    className = packakgeName + "." + className;
                }
                doAddClass(classSet,className);
            }else {
                String subPackagePath = fileName;
                if(StringUtil.isNotEmpty(packagePath)){
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPacakgeName = fileName;
                if(StringUtil.isNotEmpty(packakgeName)){
                    subPacakgeName = packakgeName + "." + subPacakgeName;
                }
                addClass(classSet,subPackagePath,subPacakgeName);
            }

        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className){
        Class<?> cls = loadClass(className,false);
        classSet.add(cls);
    }
}
