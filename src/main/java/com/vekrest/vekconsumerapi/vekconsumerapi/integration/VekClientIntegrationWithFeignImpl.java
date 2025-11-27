package com.vekrest.vekconsumerapi.vekconsumerapi.integration;

import com.vekrest.vekconsumerapi.vekconsumerapi.entities.Client;
import com.vekrest.vekconsumerapi.vekconsumerapi.integration.client.VekClientIntegrationWithFeign;
import com.vekrest.vekconsumerapi.vekconsumerapi.integration.dto.request.VekClientRegisterRequest;
import com.vekrest.vekconsumerapi.vekconsumerapi.integration.interfaces.VekClientIntegration;
import org.springframework.stereotype.Component;

@Component
public class VekClientIntegrationWithFeignImpl implements VekClientIntegration {
    private final VekClientIntegrationWithFeign integration;

    public VekClientIntegrationWithFeignImpl(
            VekClientIntegrationWithFeign vekSecurityIntegrationWithFeign
    ) {
        this.integration = vekSecurityIntegrationWithFeign;
    }

    @Override
    public Client registerClient(Client client, String token) {
        var response = integration.login(
                "Bearer " + token,
                new VekClientRegisterRequest(
                        client.name(),
                        client.birth(),
                        client.address()
                )
        );

        return new Client(
                response.name(),
                response.birth(),
                response.address()
        );
    }
}