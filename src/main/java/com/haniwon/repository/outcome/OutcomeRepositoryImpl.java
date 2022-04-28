package com.haniwon.repository.outcome;

import com.haniwon.domain.Outcome;
import com.haniwon.domain.QOutcome;
import com.haniwon.domain.Vendor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OutcomeRepositoryImpl implements OutcomeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public List<Outcome> findAllByVendor(Vendor vendor) {
        return queryFactory.selectFrom(QOutcome.outcome)
                .where(QOutcome.outcome.vendor.eq(vendor))
                .fetch();
    }

}
