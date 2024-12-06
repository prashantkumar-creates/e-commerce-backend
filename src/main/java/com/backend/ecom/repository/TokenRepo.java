package com.backend.ecom.repository;

import com.backend.ecom.model.AuthenticaitonToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticaitonToken, Integer> {
}
