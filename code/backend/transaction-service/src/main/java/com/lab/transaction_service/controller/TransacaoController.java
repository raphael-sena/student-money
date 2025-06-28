package com.lab.transaction_service.controller;

import com.lab.transaction_service.dto.SendCoinsDTO;
import com.lab.transaction_service.dto.TransacaoDTO;
import com.lab.transaction_service.mapper.TransacaoMapper;
import com.lab.transaction_service.model.Transacao;
import com.lab.transaction_service.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final TransacaoService transactionService;
    private final TransacaoMapper transacaoMapper;

    public TransacaoController(TransacaoService transactionService, TransacaoMapper transacaoMapper) {
        this.transactionService = transactionService;
        this.transacaoMapper = transacaoMapper;
    }

    @PostMapping("/send")
    public ResponseEntity<TransacaoDTO> sendCoins(@Valid @RequestBody SendCoinsDTO sendCoinsDTO) {
        Transacao transacao = transactionService.sendCoins(
            sendCoinsDTO.getSenderId(),
            sendCoinsDTO.getRecipientId(),
            sendCoinsDTO.getAmount(),
            sendCoinsDTO.getDescription()
        );
        return ResponseEntity.ok(transacaoMapper.paraDTO(transacao));
    }

    @GetMapping("/historico/{idUsuario}")
    public ResponseEntity<List<TransacaoDTO>> obterHistoricoTransacoes(@PathVariable String idUsuario) {
        List<Transacao> historico = transactionService.obterHistoricoTransacoes(idUsuario);
        List<TransacaoDTO> historicoDTO = historico.stream()
            .map(transacaoMapper::paraDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(historicoDTO);
    }

    @GetMapping("/saldo/{idUsuario}")
    public ResponseEntity<BigDecimal> obterSaldo(@PathVariable String idUsuario) {
        BigDecimal saldo = transactionService.obterSaldo(idUsuario);
        return ResponseEntity.ok(saldo);
    }
} 