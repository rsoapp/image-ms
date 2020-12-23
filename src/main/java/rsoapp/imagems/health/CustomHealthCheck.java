package rsoapp.imagems.health;

import lombok.Setter;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@Setter
public class CustomHealthCheck implements HealthIndicator {

    private String state = "UP";

    @Override
    public Health health() {
        if (state.equals("UP")) {
            return Health.up().build();
        }
        else {
            return Health.down().build();
        }
    }
}
