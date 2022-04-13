package com.haniwon.repository;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>, IncomeRepositoryCustom {

    public List<Income> findAllByDate(LocalDate date);
}
