package br.com.edimilsonldutra.desafio_uber.infra.ses;

import br.com.edimilsonldutra.desafio_uber.adapters.EmailSenderGateway;
import br.com.edimilsonldutra.desafio_uber.core.exceptions.EmailServiceException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService sesClient;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService sesClient) {
        this.sesClient = sesClient;
    }

    //Implementa o método definido na interface EmailSenderGateway para enviar um e-mail
    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest() //Cria uma nova requisição de envio de e-mail
                .withSource("edimilsonldutra@hotmail.com") //Define o destinatário do e-mail
                .withDestination(new Destination().withToAddresses(toEmail)) //Define o assunto e o corpo do e-mail
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );
        //Tenta enviar o e-mail usando sesClient.sendEmail(request). Se ocorrer uma exceção AmazonServiceException,
        // captura a exceção e lança uma EmailServiceException personalizada com uma mensagem de erro.
        try {
            sesClient.sendEmail(request);
        } catch (AmazonServiceException exception) {
            throw  new EmailServiceException("Falha no envio de e-mail", exception);
        }
    }
}
