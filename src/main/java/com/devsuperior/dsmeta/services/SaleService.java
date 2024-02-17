package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<ReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate newMaxDate = !maxDate.isEmpty() ? LocalDate.parse(maxDate) : LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate newMinDate = !minDate.isEmpty() ? LocalDate.parse(minDate) : newMaxDate.minusYears(1L);
		return repository.getReport(newMinDate, newMaxDate, name, pageable);
	}
}
