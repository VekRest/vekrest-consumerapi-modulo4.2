package com.vekrest.vekconsumerapi.vekconsumerapi.integration.dto.request;

import com.vekrest.vekconsumerapi.vekconsumerapi.entities.Address;
import java.time.LocalDate;

public record VekClientRegisterRequest(
        String name,
        LocalDate birth,
        Address address
) {}