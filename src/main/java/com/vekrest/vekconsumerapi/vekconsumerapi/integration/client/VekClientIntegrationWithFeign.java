package com.vekrest.vekconsumerapi.vekconsumerapi.integration.client;

import com.vekrest.vekconsumerapi.vekconsumerapi.integration.dto.request.VekClientRegisterRequest;
import com.vekrest.vekconsumerapi.vekconsumerapi.integration.dto.response.VekClientRegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "VekSecurityIntegration",
        url = "${vekrest.vekclient.api.url}"
)
public interface VekClientIntegrationWithFeign {
    @PostMapping("/client")
    VekClientRegisterResponse login(
            @RequestHeader("Authorization") String authorization,
            @RequestBody VekClientRegisterRequest request
    );
}