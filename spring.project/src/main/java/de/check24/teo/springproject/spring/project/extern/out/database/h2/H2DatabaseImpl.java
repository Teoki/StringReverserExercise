package de.check24.teo.springproject.spring.project.extern.out.database.h2;

import de.check24.teo.springproject.spring.project.core.dtos.Amount;
import de.check24.teo.springproject.spring.project.core.dtos.CardData;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;
import de.check24.teo.springproject.spring.project.core.externalinterfaces.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class H2DatabaseImpl implements Database {

    private static Logger LOG = LoggerFactory.getLogger(H2DatabaseImpl.class);
    private final CardDataRepository cardDataRepository;

    @Autowired
    public H2DatabaseImpl(CardDataRepository cardDataRepository) {
        this.cardDataRepository = cardDataRepository;
        int otherData = 1;
        final List<CardDataEntity> list = Arrays.asList(
                new CardDataEntity("0987", 1000, otherData, "1234567"),
                new CardDataEntity("1122", 500, otherData, "1122334"),
                new CardDataEntity("0000", 250, otherData, "0302010"),
                new CardDataEntity("7897", 125, otherData, "7897897"),
                new CardDataEntity("9876", 0, otherData, "9876543")
        );
        cardDataRepository.saveAll(list);
    }

    @Override
    public Optional<CardData> getCardDataByCardId(CardId cardId) {
        try {
            return Optional.of(cardDataRepository.findByCardId(cardId.value).toDto());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public void withdraw(CardId cardId, Amount requestedAmount) {
        final CardDataEntity entity = cardDataRepository.findByCardId(cardId.value);
        LOG.info("Ihr Kontostand vor dem Abheben betrug: " + entity.getCurrentAmount());
        entity.setCurrentAmount(entity.getCurrentAmount() - requestedAmount.amount);
        //Datenbank updaten, sobald die entity verändert wurde wird
        cardDataRepository.save(entity);
        LOG.info("Ihr Kontostand nach dem Abheben beträgt: " + cardDataRepository.findByCardId(cardId.value).getCurrentAmount());
    }

    @Override
    public void deposit(CardId cardId, Amount depositedAmount) {
        final CardDataEntity entity = cardDataRepository.findByCardId(cardId.value);
        LOG.info("Ihr Kontostand vor der Einzahlung: " + entity.getCurrentAmount());
        entity.setCurrentAmount(entity.getCurrentAmount() + depositedAmount.amount);
        LOG.info("Ihr Kontostand nach der Einzahlung: " + cardDataRepository.findByCardId(cardId.value).getCurrentAmount());
    }

}
