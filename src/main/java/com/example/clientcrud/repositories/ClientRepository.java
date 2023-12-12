package com.example.clientcrud.repositories;

import com.example.clientcrud.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, String>, CrudRepository<Client,String> {

}
