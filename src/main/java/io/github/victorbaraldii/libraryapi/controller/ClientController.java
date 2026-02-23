package io.github.victorbaraldii.libraryapi.controller;

import io.github.victorbaraldii.libraryapi.model.Client;
import io.github.victorbaraldii.libraryapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('GERENTE')")
    public void salvar(@RequestBody Client client) {
        log.info("Registrando novo Client: {} com Scope: {}", client.getClientId(), client.getScope());
        service.salvar(client);
    }
}
