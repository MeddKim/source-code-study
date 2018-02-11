package wang.willard.auth.service;

public interface IPasswordService
{
    /**
     * bcrypt加密 加密明文密码
     * @param password
     * @return
     */
    String generateBCryptPassword(String password);

    /**
     * bcrypt密码认证
     * @param rawPassword 明文密码
     * @param bcryptPassword 加密后的密码
     * @return
     */
    Boolean authenticate(String rawPassword,String bcryptPassword);

}
