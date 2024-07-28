package br.com.edimilsonldutra.desafio_uber.core.cases;

public interface EmailSenderUseCase {
    void sendEmail(String toEmail, String subject, String body);
}
