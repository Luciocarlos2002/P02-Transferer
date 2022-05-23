package com.microservice.service;

import com.microservice.model.Transferer;
import com.microservice.repository.TransfererRepository;
import com.microservice.service.impl.TransfererServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TransfererService implements TransfererServiceImpl {

    private final TransfererRepository transfererRepository;

    @Override
    public Flux<Transferer> getAllTransferer() {
        return transfererRepository.findAll();
    }

    @Override
    public Mono<Transferer> getByIdTransferer(String id) {
        return transfererRepository.findById(id);
    }

    @Override
    public Mono<Transferer> createTransferer(Transferer transferer) {
        return transfererRepository.save(transferer);
    }

    @Override
    public Mono<Transferer> updateTransferer(String id, Transferer transferer) {
        return transfererRepository.findById(id).flatMap(transferer1 -> {
            transferer1.setAmount(transferer.getAmount());
            transferer1.setTypeTransfer(transferer.getTypeTransfer());
            transferer1.setDescription(transferer.getDescription());
            return transfererRepository.save(transferer1);
        }).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteTransferer(String id) {
        return transfererRepository.findById(id).flatMap(transferer -> transfererRepository.deleteById(transferer.getId()));
    }
}
