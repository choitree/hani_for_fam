package com.haniwon.repository.income;

import com.haniwon.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>, IncomeRepositoryCustom {

    public List<Income> findAllByDate(LocalDate date);
}
