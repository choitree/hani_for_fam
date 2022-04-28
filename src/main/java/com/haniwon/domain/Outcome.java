package com.haniwon.domain;

import com.haniwon.dto.outcome.request.OutcomeRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "outcome")
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String item;
    //금액
    private Integer amount;
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(mappedBy = "outcome")
    private List<Stock> stocks = new ArrayList<>();

    public static Outcome createOutcome(OutcomeRequestDTO outcomeRequestDTO, Vendor vendor) {
        return Outcome.builder()
                .date(outcomeRequestDTO.getDate())
                .item(outcomeRequestDTO.getItem())
                .amount(outcomeRequestDTO.getAmount())
                .memo(outcomeRequestDTO.getMemo())
                .vendor(vendor)
                .build();
    }
}
