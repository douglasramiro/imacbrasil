package br.com.drsource.imacbrasil.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential,Short> {

    Credential findCredentialByEmail(String email);

}
