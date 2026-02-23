package io.github.victorbaraldii.libraryapi.service;

import io.github.victorbaraldii.libraryapi.model.Client;
import io.github.victorbaraldii.libraryapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final PasswordEncoder encoder;

    public Client salvar(Client client) {
        var senhaCriptografada = encoder.encode(client.getClientSecret());
        client.setClientSecret(senhaCriptografada);
        return repository.save(client);
    }

    public Client obterPorId(String clientId) {
        return repository.findByClientId(clientId);
    }

    public Client obterPorClientID(String clientId) {
        return repository.findByClientId(clientId);
    }

    public Client obterPorID(UUID id) {
        return repository.findById(id).orElse(null);
    }
}