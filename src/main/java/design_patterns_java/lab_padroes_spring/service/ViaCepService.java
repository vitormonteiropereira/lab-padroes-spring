package design_patterns_java.lab_padroes_spring.service;

import design_patterns_java.lab_padroes_spring.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "viacep",  url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping ("/{cep}/json/")
    Endereco retornaCep (@PathVariable("cep")  String cep);




}
