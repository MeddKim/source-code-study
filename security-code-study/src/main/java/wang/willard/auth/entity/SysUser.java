package wang.willard.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser{

    private Long id;
    private String username;
    private String password;

    private List<SysRole> roles;
}
