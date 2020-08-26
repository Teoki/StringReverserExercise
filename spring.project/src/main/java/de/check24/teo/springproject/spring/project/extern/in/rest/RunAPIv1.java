package de.check24.teo.springproject.spring.project.extern.in.rest;

import com.google.common.collect.ImmutableList;
import de.check24.teo.springproject.spring.project.core.dtos.Amount;
import de.check24.teo.springproject.spring.project.core.dtos.AtmAndMenus;
import de.check24.teo.springproject.spring.project.core.dtos.CardId;
import de.check24.teo.springproject.spring.project.core.dtos.Pin;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.hibernate.internal.util.StringHelper;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.ValidationException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.vavr.control.Validation.combine;
import static io.vavr.control.Validation.fromTry;

public abstract class RunAPIv1 {

    public static Either<ResponseEntity<Object>, Request> of(
            Optional<String> atmId,
            Optional<String> menuIds,
            Optional<String> cardId,
            Optional<String> pin,
            Optional<String> amount) {
        return combine(
                fromTry(Try.of(() -> {
                    final String value = cardId.get();
                    if (StringHelper.isEmptyOrWhiteSpace(value)) {
                        throw new ValidationException("CardId is strange");
                    }
                    return new CardId(value);
                })),
                fromTry(Try.of(() -> {
                    if (StringHelper.isEmptyOrWhiteSpace(pin.get())) {
                        throw new ValidationException("Pin is strange");
                    }
                    return new Pin(pin.get());
                })),
                fromTry(Try.of(() -> amount.flatMap(value -> {
                    if (StringHelper.isEmptyOrWhiteSpace(value)) {
                        return Optional.empty();
                    }
                    return Optional.of(new Amount(Integer.valueOf(value)));
                }))),
                fromTry(Try.of(() ->
                        new AtmAndMenus(Integer.valueOf(atmId.get()), ImmutableList.copyOf(Arrays.stream(menuIds.get().split(",")).map(Integer::valueOf).collect(Collectors.toList())))
                ))
        )
                .ap(Request::new)
                .toEither()
                .mapLeft(e -> ResponseEntity.badRequest()
                        .header("X-ERROR", e.map(Throwable::getMessage).collect(Collectors.joining()))
                        .build());
    }

    public static class Request {

        public final CardId cardId;
        public final Pin pin;
        public final Optional<Amount> amount;
        public final AtmAndMenus atmAndMenus;

        private Request(CardId cardId, Pin pin, Optional<Amount> amount, AtmAndMenus atmAndMenus) {
            this.cardId = cardId;
            this.pin = pin;
            this.amount = amount;
            this.atmAndMenus = atmAndMenus;
        }

    }

    /*public static class Response {
        @JsonValue
        private final List<CustomerRequestData> value;

        private Response(List<CustomerRequestData> value) {
            this.value = value;
        }

        public static ResponseEntity<Response> ok(List<CustomerRequestData> value) {
            return ResponseEntity.ok(new Response(value));
        }
    }*/
}
