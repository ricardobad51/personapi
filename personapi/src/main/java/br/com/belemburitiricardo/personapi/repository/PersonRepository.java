package br.com.belemburitiricardo.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.com.belemburitiricardo.personapi.entity.Person;


@Repository
@Component
@EnableJpaRepositories
public interface PersonRepository extends JpaRepository<Person, Long>{ //<>generics
}
