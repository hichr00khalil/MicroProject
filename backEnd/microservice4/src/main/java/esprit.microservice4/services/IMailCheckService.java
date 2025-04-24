package esprit.microservice4.services;

public interface IMailCheckService {

    void sendMail(String toEmail, String subject, String body );

}
