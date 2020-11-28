package rsoapp.imagems.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "variables")
public class Config {

    private String var1;
    private String var2;
}
