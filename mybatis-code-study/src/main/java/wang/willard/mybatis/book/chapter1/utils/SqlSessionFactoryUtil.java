package wang.willard.mybatis.book.chapter1.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory = null;
    //类线程锁
    private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;
    //单例
    private SqlSessionFactoryUtil(){}

    /**
     * 构建SqlSessionFactory
     */
    public static SqlSessionFactory initSqlSesionFactory(){
        String resource = "chapter1/mybatis-config.xml";
        InputStream inputStream = null;
        try{
            inputStream = Resources.getResourceAsStream(resource);
        }catch (IOException ex){
            Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Level.SEVERE,null, ex);
        }
        synchronized (CLASS_LOCK){
            if(sqlSessionFactory == null){
                sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
            }
        }
        return sqlSessionFactory;
    }
    /**
     * 打开SqlSession
     */
    public static SqlSession openSqlSession(){
        if(sqlSessionFactory == null){
            initSqlSesionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
