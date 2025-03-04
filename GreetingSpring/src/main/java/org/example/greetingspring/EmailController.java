package org.example.greetingspring;



import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Send a simple email
    @PostMapping("/send")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String text) {
        emailService.sendEmail(to, subject, text);
        return "Email sent successfully!";
    }





}
