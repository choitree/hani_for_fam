package com.haniwon.repository.outcome;

import com.haniwon.domain.Outcome;
import com.haniwon.domain.Vendor;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

public interface OutcomeRepositoryCustom {

    public List<Outcome> findAllByVendor(Vendor vendor);

    public List<Outcome> findAllByDay(LocalDate date);

    public List<Outcome> findAllByMonth(YearMonth yearMonth);

    public List<Outcome> findAllByYear(Year year);
}
