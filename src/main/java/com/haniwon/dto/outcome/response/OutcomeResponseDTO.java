package com.haniwon.dto.outcome.response;

import com.haniwon.domain.Outcome;
import com.haniwon.domain.Stock;
import com.haniwon.dto.stock.StockResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder
@Getter
public class OutcomeResponseDTO {

    private final String item;
    //금액
    private final Integer amount;
    private final String memo;

    private final List<StockResponseDTO> stocks;

    private static List<StockResponseDTO> convertStocks(List<Stock> stocks) {
        return stocks.stream()
                .map(stock -> StockResponseDTO.from(stock))
                .collect(Collectors.toList());

    }
    public static OutcomeResponseDTO from(Outcome outcome, List<Stock> stocks) {
        return OutcomeResponseDTO.builder()
                .item(outcome.getItem())
                .amount(outcome.getAmount())
                .memo(outcome.getMemo())
                .stocks(convertStocks(stocks))
                .build();
    }

    public static OutcomeResponseDTO from(Outcome outcome) {
        return OutcomeResponseDTO.builder()
                .item(outcome.getItem())
                .amount(outcome.getAmount())
                .memo(outcome.getMemo())
                .build();
    }
}
