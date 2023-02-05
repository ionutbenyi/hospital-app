package com.example.hospitalapp.soap_consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ActivityStatusConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.example.hospitalapp.wsdl");
        return marshaller;
    }

   @Bean
    public ActivityStatusClient activityStatusClient(Jaxb2Marshaller marshaller) {
       ActivityStatusClient client = new ActivityStatusClient();
        client.setDefaultUri("http://localhost:8083/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
