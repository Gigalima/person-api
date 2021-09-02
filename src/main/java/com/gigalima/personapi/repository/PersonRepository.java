package com.gigalima.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gigalima.personapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
