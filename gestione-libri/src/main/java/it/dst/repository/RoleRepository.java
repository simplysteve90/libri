package it.dst.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dst.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
