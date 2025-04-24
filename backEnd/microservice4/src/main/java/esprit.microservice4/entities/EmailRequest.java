package esprit.microservice4.entities;

import jakarta.validation.constraints.NotBlank;



public class EmailRequest {
    @NotBlank
    private String toEmail;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;

    // Constructor
    public EmailRequest(String toEmail, String subject, String body) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
    }

    // No-argument constructor
    public EmailRequest() {
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
