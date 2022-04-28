package com.haniwon.repository.outcome;

import com.haniwon.domain.Outcome;
import com.haniwon.domain.Vendor;

import java.util.List;

public interface OutcomeRepositoryCustom {

    public List<Outcome> findAllByVendor(Vendor vendor);

}
