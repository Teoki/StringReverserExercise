package de.check24.teo.springproject.spring.project.extern.out.database.h2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDataRepository extends CrudRepository<CardDataEntity, Long> { //CrudRepository ermöglicht die Erbung von verschiedenen Methoden, um CardDataEntity entities zu manipulieren

    CardDataEntity findByCardId(String cardId);

}
