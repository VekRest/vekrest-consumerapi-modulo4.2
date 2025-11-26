package com.vekrest.vekconsumerapi.vekconsumerapi.entities;

import java.time.LocalDate;

public record Client(
        String name,
        LocalDate birth,
        Address address
) {}