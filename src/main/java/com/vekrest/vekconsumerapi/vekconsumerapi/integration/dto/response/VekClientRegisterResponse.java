package com.vekrest.vekconsumerapi.vekconsumerapi.integration.dto.response;

import com.vekrest.vekconsumerapi.vekconsumerapi.entities.Address;
import java.time.LocalDate;

public record VekClientRegisterResponse(
        String id,
        String name,
        LocalDate birth,
        Address address
) {}