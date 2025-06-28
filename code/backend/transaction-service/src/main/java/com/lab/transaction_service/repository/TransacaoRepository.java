package com.lab.transaction_service.repository;

import com.lab.transaction_service.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByIdRemetenteOrderByDataHoraDesc(String idRemetente);
    List<Transacao> findByIdDestinatarioOrderByDataHoraDesc(String idDestinatario);
    
    @Query("SELECT COALESCE(SUM(CASE WHEN t.idRemetente = :userId THEN -t.valor ELSE t.valor END), 0) " +
           "FROM Transacao t " +
           "WHERE t.idRemetente = :userId OR t.idDestinatario = :userId")
    BigDecimal calculateBalanceByUserId(@Param("userId") String userId);
} 