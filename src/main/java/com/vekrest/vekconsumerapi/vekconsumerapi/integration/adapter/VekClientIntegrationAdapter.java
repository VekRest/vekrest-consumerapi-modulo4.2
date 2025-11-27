package com.vekrest.vekconsumerapi.vekconsumerapi.integration.adapter;

import com.vekrest.vekconsumerapi.vekconsumerapi.entities.Client;
import com.vekrest.vekconsumerapi.vekconsumerapi.integration.dto.response.VekClientRegisterResponse;

public class VekClientIntegrationAdapter {
    private VekClientIntegrationAdapter() {
    }

    public static Client cast(VekClientRegisterResponse response) {
        return new Client(
                response.name(),
                response.birth(),
                response.address()
        );
    }
}
