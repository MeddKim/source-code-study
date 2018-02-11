package wang.willard.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wang.willard.auth.service.IPasswordService;

@Service
public class PasswordServiceImpl implements IPasswordService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public String generateBCryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Boolean authenticate(String rawPassword, String bcryptPassword) {
        return passwordEncoder.matches(rawPassword,bcryptPassword);
    }
}
