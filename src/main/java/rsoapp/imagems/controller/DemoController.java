package rsoapp.imagems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rsoapp.imagems.health.CustomHealthCheck;

@RestController
@RequestMapping("/v1/demo/")
public class DemoController {

    private final CustomHealthCheck customHealthCheck;

    public DemoController(CustomHealthCheck customHealthCheck) {
        this.customHealthCheck = customHealthCheck;
    }

    @PostMapping("break")
    public ResponseEntity<Void> makeUnhealthy() {
        customHealthCheck.setState("DOWN");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
