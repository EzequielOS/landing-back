package br.edu.iftm.imbMary.repository;

import br.edu.iftm.imbMary.domain.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository  extends CrudRepository<Admin, Integer> {
    Admin findByName(String username);
}
