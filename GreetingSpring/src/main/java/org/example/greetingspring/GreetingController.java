package org.example.greetingspring;



import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private final AtomicLong counter = new AtomicLong();
    private final GreetingService greetingService;

    // Constructor Injection of the GreetingService
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // Endpoint to return "Hello World"
    @GetMapping("/simple")
    public String getSimpleGreeting() {
        return greetingService.getSimpleGreeting();
    }

    // Endpoint to return a personalized greeting
    @GetMapping
    public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
    }
}
