package com.haniwon.repository.vendor;

import com.haniwon.domain.Vendor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class VendorRepositoryImpl {

    private final JPAQueryFactory queryFactory;


}
