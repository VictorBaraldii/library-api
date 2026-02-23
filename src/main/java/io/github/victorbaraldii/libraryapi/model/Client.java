package io.github.victorbaraldii.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "client", schema = "public")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "redirect_uri", nullable = false)
    private String redirectURI;

    @Column(nullable = false)
    private String scope;
}
