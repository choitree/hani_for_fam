package com.haniwon.repository.patient;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final JPAQueryFactory queryFactory;
}
