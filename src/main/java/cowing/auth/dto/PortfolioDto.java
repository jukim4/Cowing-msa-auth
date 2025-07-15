package cowing.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "포트폴리오 조회 DTO")
public record PortfolioDto(
        @Schema(description = "종목명", example = "BTC")
        @JsonProperty("name") @NotBlank String name,
        @Schema(description = "구매 수량", example = "2.000000")
        @JsonProperty("quantity")  @NotNull BigDecimal quantity,
        @Schema(description = "평단가", example = "12000")
        @JsonProperty("average_cost")  @NotNull Long averageCost,
        @Schema(description = "총 구매 가격", example = "24000")
        @JsonProperty("total_cost")  @NotNull Long totalCost) {
}