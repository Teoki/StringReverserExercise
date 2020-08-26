package de.check24.teo.springproject.spring.project.extern.in.rest;

import de.check24.teo.springproject.spring.project.core.Service;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AtmRequestController {

    private static Logger LOG = LoggerFactory.getLogger(AtmRequestController.class);

    @Autowired
    private Service service;    //Dependency Injection, findet die passende Implementierung von Service (wenns vererben würde, würde Spring durch @Autowired die erbende Klasse finden)

    //'http://localhost:8080/atms/111/menus/11/menus/1/menus/444?cardId=1234567&pin=0987&amount=500' --> bei mehreren @RequestParam immer ' benutzen
    @GetMapping("/atms/{atmId}/menus/{menuIds}")
    public ResponseEntity<Object> run(@PathVariable(value = "atmId") Optional<String> atmId, @PathVariable(value = "menuIds") Optional<String> menuIds,
                                      @RequestParam(value = "cardId") Optional<String> cardId, @RequestParam(value = "pin") Optional<String> pin,
                                      @RequestParam(value = "amount") Optional<String> amount) {
        LOG.info("EXECUTING : run() method");
        return RunAPIv1.of(atmId, menuIds, cardId, pin, amount)
                .fold(r -> r, in -> { //kann empty sein, falls unvalide eingabe
                    return Try.run(() -> {
                        service.runAction(in.cardId, in.pin, in.atmAndMenus, in.amount);
                    })
                            .toEither()
                            .fold(r -> ResponseEntity.status(500).header("X-ERROR", r.getMessage()).build(),
                                    r -> ResponseEntity.noContent().build());
                });
    }

}
