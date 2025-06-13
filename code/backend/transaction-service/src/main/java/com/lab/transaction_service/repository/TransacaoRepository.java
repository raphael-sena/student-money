package com.lab.transaction_service.repository;

import com.lab.transaction_service.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByIdRemetenteOrderByDataHoraDesc(String idRemetente);
    List<Transacao> findByIdDestinatarioOrderByDataHoraDesc(String idDestinatario);
} 