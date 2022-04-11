package com.haniwon.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate date;
    private Integer weight;
    @Column(name = "package")
    private Integer pack;
    private Integer price;
    private Boolean isBuy;

    //구입으로 발생한 재고가 아니라면, mapping되지 않아서 foreign key가 null인 경우도 포함됨
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "outcome_id")
    private Outcome outcome;


}
