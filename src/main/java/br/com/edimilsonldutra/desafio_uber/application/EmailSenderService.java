package br.com.edimilsonldutra.desafio_uber.application;

import br.com.edimilsonldutra.desafio_uber.adapters.EmailSenderGateway;
import br.com.edimilsonldutra.desafio_uber.core.cases.EmailSenderUseCase;
import org.springframework.stereotype.Service;

//Esta classe atua como um serviço que encapsula a lógica de envio de e-mails, implementando
// o caso de uso de envio de e-mails (EmailSenderUseCase)
//Ao delegar a tarefa de envio para o EmailSenderGateway, permite a separação de preocupações
// e facilita a troca ou modificação da lógica de envio de e-mails sem afetar outras partes da aplicação
@Service
public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGateway emailSenderGateway;

    public EmailSenderService(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        emailSenderGateway.sendEmail(toEmail, subject, body);
    }
}
