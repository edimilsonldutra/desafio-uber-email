package br.com.edimilsonldutra.desafio_uber.controllers;

import br.com.edimilsonldutra.desafio_uber.application.EmailSenderService;
import br.com.edimilsonldutra.desafio_uber.core.EmailRequest;
import br.com.edimilsonldutra.desafio_uber.core.exceptions.EmailServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailSenderController {

    private final EmailSenderService emailSenderService;


    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        //Tenta enviar o e-mail utilizando emailSenderService.sendEmail(), passando os parâmetros to,
        // subject e body extraídos do emailRequest.
        //Se o envio for bem-sucedido, retorna uma resposta HTTP 200 (OK) com a mensagem "E-mail enviado com sucesso!".
        //Captura a exceção EmailServiceException se ocorrer um erro durante o envio do e-mail e retorna
        // uma resposta HTTP 500 (Internal Server Error) com a mensagem "Falha no envio do e-mail."
        try {
            emailSenderService.sendEmail(emailRequest.to(), emailRequest.subject(), emailRequest.body());
            return ResponseEntity.ok("E-mail enviado com sucesso!"); //

        } catch (EmailServiceException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha no envio do e-mail.");
        }
    }
}
