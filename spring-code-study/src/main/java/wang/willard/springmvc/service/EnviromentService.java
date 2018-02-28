package wang.willard.springmvc.service;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EnviromentService implements EnvironmentAware {

    private Environment environment = null;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
