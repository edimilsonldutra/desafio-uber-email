package br.com.edimilsonldutra.desafio_uber.infra.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Essa configuração permite que a aplicação envie emails de forma programática usando o serviço SES da AWS,
// aproveitando a injeção de dependência do Spring para gerenciar a criação e configuração do cliente SES.
@Configuration
public class AwsSesConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    //Configura e fornece um bean do tipo AmazonSimpleEmailService que pode ser injetado em outras partes
    // da aplicação Spring para enviar emails através do Amazon SES usando as credenciais e a região especificadas.
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {

        //Cria um objeto de credenciais da AWS usando a chave de acesso e a chave secreta
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);


        return AmazonSimpleEmailServiceClientBuilder //Constrói um cliente Amazon SES
                .standard() //Inicia o construtor padrão
                .withCredentials(new AWSStaticCredentialsProvider(credentials)) //Define as credenciais estáticas da AWS
                .withRegion(awsRegion) //Define a região da AWS
                .build(); //Cria a instância do cliente Amazon SES

    }
}
