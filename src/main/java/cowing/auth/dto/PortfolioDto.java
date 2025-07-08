package cowing.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PortfolioDto(
        @JsonProperty("name") @NotBlank String name,
        @JsonProperty("quantity")  @NotNull BigDecimal quantity,
        @JsonProperty("average_cost")  @NotNull Long averageCost,
        @JsonProperty("total_cost")  @NotNull Long totalCost) {
}