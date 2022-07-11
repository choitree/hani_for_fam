package com.haniwon.repository.income;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.domain.QIncome;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static com.haniwon.domain.QIncome.income;

@RequiredArgsConstructor
public class IncomeRepositoryImpl implements IncomeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public List<Income> findAllByPatient(Patient patient) {
        return queryFactory.selectFrom(income)
                .where(income.patient.eq(patient))
                .orderBy(income.date.desc())
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
        return queryFactory.select(income.date)
                .from(income)
                .where(income.patient.id.eq(patient.getId()))
                .orderBy(income.date.desc())
                .fetchFirst();
    }

    public LocalDate findFirstVisitByPatient(Patient patient) {
        return queryFactory.select(income.date)
                .from(income)
                .where(income.patient.id.eq(patient.getId()))
                .orderBy(income.date.asc())
                .fetchFirst();
    }

    public Long countVisitByPatient(Patient patient) {
        return queryFactory.selectFrom(income)
                .where(income.patient.eq(patient))
                .fetchCount();
    }

    public List<Income> findAllByMonth(YearMonth yearMonth) {
        return queryFactory.selectFrom(income)
                .where(income.date.year().eq(yearMonth.getYear()).and(income.date.month().eq(yearMonth.getMonthValue())))
                .fetch();
    }

    public List<Income> findAllByYear(Year year) {
        return queryFactory.selectFrom(income)
                .where(income.date.year().eq(year.getValue()))
                .fetch();
    }

    public List<Income> findAllByPeriod(LocalDate date1, LocalDate date2) {
        return queryFactory.selectFrom(income)
                .where(income.date.between(date1, date2))
                .fetch();
    }

//    InvalidDataAccessApiUsageException
//    환자 1명에 대한 전체 매출 조회를 위한 메소드
//    public List<CountAmountDTO> findAllIncomeAndSummeryByPatient(Patient patient) {
//        return queryFactory.select(Projections.fields(CountAmountDTO.class,
//                        income.isAcupuncture.eq(true).count().as("count"),
//                new CaseBuilder()
//                        .when(income.isAcupuncture.eq(true))
//                        .then(income.amount.sum())
//                        .otherwise(income.amount.sum()).as("amount")
//                        ))
//                .from(income)
//                .where(income.patient.eq(patient))
//                .fetch();
//    }


    //    환자 1명에 대한 전체 매출 조회를 위한 메소드
    //추출되는데 값이 정확하게 나오지 않음
//    public List<IncomeCaseSumResponseDTO> findAllIncomeAndSummeryByPatient(Patient patient) {
//        List<IncomeCaseSumResponseDTO> result =  queryFactory.select(Projections.fields(IncomeCaseSumResponseDTO.class,
//                        income.patient.eq(patient).count().as("case"),
//                        ExpressionUtils.as(
//                                JPAExpressions
//                                        .select(income.amount.sum())
//                                        .where(income.patient.eq(patient).and(income.isAcupuncture.eq(true)))
//                                        .from(income), "sum")
//
//                        ))
//                .from(income)
//                .where(income.patient.eq(patient))
//                .groupBy(income.isAcupuncture)
//                .orderBy(income.isAcupuncture.asc())
//                .fetch();
//
//        return result;
//    }

    //환자 1명에 해당하는 매출 전체 삭제
    public Long deleteAllByPatient(Patient patient) {
        return queryFactory.delete(income)
                .where(income.patient.eq(patient))
                .execute();
    }

}
