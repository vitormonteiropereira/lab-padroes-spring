package design_patterns_java.lab_padroes_spring.service.impl;


import design_patterns_java.lab_padroes_spring.model.Cliente;
import design_patterns_java.lab_padroes_spring.model.ClienteRepository;
import design_patterns_java.lab_padroes_spring.model.Endereco;
import design_patterns_java.lab_padroes_spring.model.EnderecoRepository;
import design_patterns_java.lab_padroes_spring.service.ClienteService;
import design_patterns_java.lab_padroes_spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ViaCepService viaCepService;


    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarComCep(cliente);


  }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> cliente1 = clienteRepository.findById(id);
        if (cliente1.isPresent()){
            salvarComCep(cliente);
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }

    public void salvarComCep(Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(()->{
            Endereco novoEndereco = viaCepService.retornaCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
