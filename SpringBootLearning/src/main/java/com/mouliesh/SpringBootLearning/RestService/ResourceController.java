package com.mouliesh.SpringBootLearning.RestService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ResourceController {
    private static final String temple = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(temple, name));
    }

    @PostMapping("/greeting")
    public Greeting greetingCreate(@RequestParam(value = "name", defaultValue = "Guys") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(temple, name));
    }
}