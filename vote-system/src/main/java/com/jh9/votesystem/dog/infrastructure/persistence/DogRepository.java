package com.jh9.votesystem.dog.infrastructure.persistence;


import com.jh9.votesystem.dog.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {

}
