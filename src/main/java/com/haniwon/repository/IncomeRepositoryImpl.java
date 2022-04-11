package com.haniwon.repository;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.domain.QIncome;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class IncomeRepositoryImpl implements IncomeRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    public List<Income> findAllByPatient(Patient patient) {
        return queryFactory.selectFrom(QIncome.income)
                .where(QIncome.income.patient.eq(patient))
                .fetch();
    }

    public Optional<Income> findLastVisitIncomeByPatient (Patient patient) {
        Income income = queryFactory.selectFrom(QIncome.income)
                .where(QIncome.income.patient.eq(patient))
                .orderBy(QIncome.income.date.desc())
                .fetchFirst();
        return Optional.ofNullable(income);
    }


    public LocalDate findLastVisitByPatient(Patient patient) {
        return queryFactory.select(QIncome.income.date)
                .from(QIncome.income)
                .where(QIncome.income.patient.id.eq(patient.getId()))
                .orderBy(QIncome.income.date.desc())
                .fetchFirst();
    }

    public LocalDate findFirstVisitByPatient(Patient patient) {
        return queryFactory.select(QIncome.income.date)
                .from(QIncome.income)
                .where(QIncome.income.patient.id.eq(patient.getId()))
                .orderBy(QIncome.income.date.asc())
                .fetchFirst();
    }

    public Long countVisitByPatient(Patient patient) {
        return queryFactory.selectFrom(QIncome.income)
                .where(QIncome.income.patient.eq(patient))
                .fetchCount();
    }

}
