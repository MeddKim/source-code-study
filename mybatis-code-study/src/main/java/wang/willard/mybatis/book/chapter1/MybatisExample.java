package wang.willard.mybatis.book.chapter1;

import org.apache.ibatis.session.SqlSession;
import wang.willard.mybatis.book.chapter1.mapper.SysRolesMapper;
import wang.willard.mybatis.book.chapter1.pojo.SysRoles;
import wang.willard.mybatis.book.chapter1.utils.MyBatisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisExample {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.getSqlSessionFactory().openSession();
            SysRolesMapper sysRolesMapper = sqlSession.getMapper(SysRolesMapper.class);
//            SysRoles role = sysRolesMapper.getRole(1L);
//            System.out.println(role.getRole());

            Map<String,Object> paramMap = new HashMap<>(3);
            paramMap.put("role","ROLE");
            paramMap.put("available",true);
            List<SysRoles> sysRoles = sysRolesMapper.findByParams(paramMap);
            sysRoles.forEach(sysRoles1 -> {
                System.out.println(sysRoles1.getDescription());
            });

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            if(null != sqlSession){
                sqlSession.close();
            }
        }
    }
}
