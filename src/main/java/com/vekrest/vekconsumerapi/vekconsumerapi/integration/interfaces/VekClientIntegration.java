package com.vekrest.vekconsumerapi.vekconsumerapi.integration.interfaces;

import com.vekrest.vekconsumerapi.vekconsumerapi.entities.Client;

public interface VekClientIntegration {
    Client registerClient(Client client, String token);
}
