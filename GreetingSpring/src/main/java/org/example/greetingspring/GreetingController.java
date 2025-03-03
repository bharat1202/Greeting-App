package org.example.greetingspring;

import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting") // Base path for all endpoints
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // GET request - Retrieve a greeting
    @GetMapping
    public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    // POST request - Create a greeting (simulated)
    @PostMapping
    public Greeting postGreeting(@RequestParam(value = "name") String name) {
        return new Greeting(counter.incrementAndGet(), "Created: " + String.format(template, name));
    }

    // PUT request - Update a greeting (simulated)
    @PutMapping
    public Greeting putGreeting(@RequestParam(value = "name") String name) {
        return new Greeting(counter.incrementAndGet(), "Updated: " + String.format(template, name));
    }

    // DELETE request - Delete a greeting (simulated)
    @DeleteMapping
    public String deleteGreeting() {
        return "{\"message\": \"Greeting deleted successfully\"}";
    }
}
