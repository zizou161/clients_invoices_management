package com.example.clientcrud.dto.response;

import com.example.clientcrud.entities.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponseDto {
    private String id;
    private String name;
    private String address;
    private long invoicesCount;

    public ClientResponseDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.address = client.getAddress();
        this.invoicesCount = client.getInvoices().size();
    }
}
