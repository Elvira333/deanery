package tech.inno.demodeanery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Value("deanery")
    private String name;

    @GetMapping
    public String getNameApplication() {
        return name;
    }
}
