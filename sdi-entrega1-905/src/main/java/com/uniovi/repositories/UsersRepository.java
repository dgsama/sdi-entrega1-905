package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	

	@Query("SELECT r FROM User r where (LOWER(r.email) NOT LIKE LOWER(?1))")
	List<User> findAllButUser(String email);

	@Query("SELECT r FROM User r WHERE (LOWER(r.name) LIKE LOWER(?1) OR LOWER(r.email) LIKE LOWER(?1) AND LOWER(r.email) NOT LIKE LOWER(?2))")
	List<User> searchUsersByNameAndEmail(String text, String email);

}
