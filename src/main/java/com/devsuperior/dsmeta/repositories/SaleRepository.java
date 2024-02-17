package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(
            "SELECT new com.devsuperior.dsmeta.dto.ReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :sellerName, '%')) " +
            "AND obj.date BETWEEN :minDate AND :maxDate " +
            "ORDER BY obj.id"
    )
    Page<ReportDTO> getReport(LocalDate minDate, LocalDate maxDate, String sellerName, Pageable pageable);
}
