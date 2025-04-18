package esprit.microservice1.services;

public interface IMailCheckService {

    void sendMail(String toEmail, String subject, String body );

}
