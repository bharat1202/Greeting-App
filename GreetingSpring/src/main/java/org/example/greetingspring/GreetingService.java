package org.example.greetingspring;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting createGreeting(String firstName, String lastName) {
        String message;

        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            message = String.format("Hello, %s %s!", firstName, lastName);
        } else if (firstName != null && !firstName.isEmpty()) {
            message = String.format("Hello, %s!", firstName);
        } else if (lastName != null && !lastName.isEmpty()) {
            message = String.format("Hello, %s!", lastName);
        } else {
            message = "Hello World";
        }

        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    public Greeting updateGreeting(Long id, String newMessage) {
        return greetingRepository.findById(id)
                .map(greeting -> {
                    greeting.setMessage(newMessage);
                    return greetingRepository.save(greeting);
                })
                .orElseThrow(() -> new RuntimeException("Greeting not found with ID: " + id));
    }
}
