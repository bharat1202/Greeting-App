package org.example.greetingspring;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getPersonalizedGreeting(String firstName, String lastName) {
        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
            return String.format("Hello, %s %s!", firstName, lastName); // Full Name ✅
        } else if (firstName != null && !firstName.isEmpty()) {
            return String.format("Hello, %s!", firstName); // First Name Only ✅
        } else if (lastName != null && !lastName.isEmpty()) {
            return String.format("Hello, %s!", lastName); // Last Name Only ✅
        } else {
            return "Hello World"; // Default Greeting ✅
        }
    }
}
