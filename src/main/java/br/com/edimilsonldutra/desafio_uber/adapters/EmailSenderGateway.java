package br.com.edimilsonldutra.desafio_uber.adapters;

public interface EmailSenderGateway {
    void sendEmail(String toEmail, String subject, String body);
}
