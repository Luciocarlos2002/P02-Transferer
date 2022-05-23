package com.microservice.controller;

import com.microservice.model.Transferer;
import com.microservice.service.impl.TransfererServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transferer")
public class TransfererController {

    private final TransfererServiceImpl transfererService;

    private static final String TRANSFERER = "transferer";

    @GetMapping(value = "/getAllTransferer")
    public Flux<Transferer> getAllTransferer(){
        return transfererService.getAllTransferer();
    }

    @GetMapping(value = "/{id}")
    public Mono<Transferer> getByIdTransferer(@PathVariable String id){
        return transfererService.getByIdTransferer(id);
    }

    @PostMapping(value = "/create")
    @CircuitBreaker(name = TRANSFERER, fallbackMethod = "transferer")
    public Mono<Transferer> createTransferer(@RequestBody Transferer transferer){
        return transfererService.createTransferer(transferer);
    }

    @PutMapping(value = "/update/{id}")
    @CircuitBreaker(name = TRANSFERER, fallbackMethod = "transferer")
    public Mono<Transferer> updateTransferer(@PathVariable String id, @RequestBody Transferer transferer){
        return transfererService.updateTransferer(id, transferer);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<Void> deleteTransferer(@PathVariable String id){
        return transfererService.deleteTransferer(id);
    }

}
