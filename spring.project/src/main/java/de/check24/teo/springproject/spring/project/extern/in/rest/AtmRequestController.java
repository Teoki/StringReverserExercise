package de.check24.teo.springproject.spring.project.extern.in.rest;

import de.check24.teo.springproject.spring.project.core.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SuppressWarnings("ALL")
@RestController
public class AtmRequestController {

    public static final String URI = "/atms/{atmId}/menus/{menuIds}";
    public static final String ATM_ID = "atmId";
    public static final String MENU_IDS = "menuIds";
    public static final String CARD_ID = "cardId";
    public static final String PIN = "pin";
    public static final String AMOUNT = "amount";
    private static Logger LOG = LoggerFactory.getLogger(AtmRequestController.class);

    @Autowired
    private Service service;    //Dependency Injection, findet die passende Implementierung von Service (wenns vererben würde, würde Spring durch @Autowired die erbende Klasse finden)

    //Für Request:  --> bei mehreren @RequestParam immer ' benutzen
    //curl -i -H "Content-Type: application/json" -X GET 'http://localhost:8080/atms/111/menus/11,1,444?cardId=1234567&pin=0987&amount=500' für request mit Body
    //curl -i -X GET 'http://localhost:8080/atms/111/menus/11,1,444?cardId=1234567&pin=0987&amount=500' für request ohne Body
    @GetMapping(URI)
    public ResponseEntity<Object> run(@PathVariable(value = ATM_ID) Optional<String> atmId, @PathVariable(value = MENU_IDS) Optional<String> menuIds,
                                      @RequestParam(value = CARD_ID) Optional<String> cardId, @RequestParam(value = PIN) Optional<String> pin,
                                      @RequestParam(value = AMOUNT) Optional<String> amount) {
        return RunAPIv1.of(atmId, menuIds, cardId, pin, amount)
                .fold(r -> r, in -> { //rechts wird runAction aufgerufen, links nichts
                    return service.runAction(in.cardId, in.pin, in.atmAndMenus, in.amount)
                            .fold(r -> ResponseEntity.status(418).header("X-ERROR", r).build(),
                                    r -> ResponseEntity.ok("Neuer Kontostand: " + r));
                });
    }

}
