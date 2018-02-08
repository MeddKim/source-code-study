package wang.willard.auth.entity;

import lombok.Data;

@Data
public class SysUser{

    private Long id;
    private String username;
    private String password;
}
