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

    private static Logger LOG = LoggerFactory.getLogger(AtmRequestController.class);

    @Autowired
    private Service service;    //Dependency Injection, findet die passende Implementierung von Service (wenns vererben würde, würde Spring durch @Autowired die erbende Klasse finden)

    //Für Request: curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET 'http://localhost:8080/atms/111/menus/11/menus/1/menus/444?cardId=1234567&pin=0987&amount=500' --> bei mehreren @RequestParam immer ' benutzen
    @GetMapping("/atms/{atmId}/menus/{menuIds}")
    public ResponseEntity<Object> run(@PathVariable(value = "atmId") Optional<String> atmId, @PathVariable(value = "menuIds") Optional<String> menuIds,
                                      @RequestParam(value = "cardId") Optional<String> cardId, @RequestParam(value = "pin") Optional<String> pin,
                                      @RequestParam(value = "amount") Optional<String> amount) {
        return RunAPIv1.of(atmId, menuIds, cardId, pin, amount)
                .fold(r -> r, in -> { //rechts wird runAction aufgerufen, links nichts
                    return service.runAction(in.cardId, in.pin, in.atmAndMenus, in.amount)
                            .fold(r -> ResponseEntity.status(418).header("X-ERROR", r).build(),
                                    r -> ResponseEntity.ok("Neuer Kontostand: " + r));
                });
    }

}
