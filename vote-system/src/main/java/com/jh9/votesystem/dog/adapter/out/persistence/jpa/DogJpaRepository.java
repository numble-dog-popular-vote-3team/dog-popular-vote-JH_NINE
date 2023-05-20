package com.jh9.votesystem.dog.adapter.out.persistence.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

interface DogJpaRepository extends JpaRepository<DogJpaEntity, Long>, DogJpaRepositoryCustom {
}
