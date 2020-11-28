package rsoapp.imagems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ImageMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageMsApplication.class, args);
    }

}
