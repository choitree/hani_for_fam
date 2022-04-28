package com.haniwon.repository.outcome;

import com.haniwon.domain.Outcome;
import com.haniwon.domain.QOutcome;
import com.haniwon.domain.Vendor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
public class OutcomeRepositoryImpl implements OutcomeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public List<Outcome> findAllByVendor(Vendor vendor) {
        return queryFactory.selectFrom(QOutcome.outcome)
                .where(QOutcome.outcome.vendor.eq(vendor))
                .fetch();
    }

    public List<Outcome> findAllByDay(LocalDate date) {
        return queryFactory.selectFrom(QOutcome.outcome)
                .where(QOutcome.outcome.date.eq(date))
                .fetch();
    }

    public List<Outcome> findAllByMonth(YearMonth yearMonth) {
        return queryFactory.selectFrom(QOutcome.outcome)
                .where(QOutcome.outcome.date.between(yearMonth.atDay(1), yearMonth.atEndOfMonth()))
                .fetch();
    }

    public List<Outcome> findAllByYear(Year year) {
        return queryFactory.selectFrom(QOutcome.outcome)
                .where(QOutcome.outcome.date.year().eq(year.getValue()))
                .fetch();
    }

    public List<Outcome> findAllByPeriod(LocalDate startDate, LocalDate endDate) {
        return queryFactory.selectFrom(QOutcome.outcome)
                .where(QOutcome.outcome.date.between(startDate, endDate))
                .fetch();
    }

}
