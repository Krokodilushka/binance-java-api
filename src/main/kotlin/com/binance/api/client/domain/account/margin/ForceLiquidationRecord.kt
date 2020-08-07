package com.binance.api.client.domain.account.margin


import com.fasterxml.jackson.annotation.JsonProperty

data class ForceLiquidationRecord(
        @JsonProperty("rows")
        val rows: List<Row>,
        @JsonProperty("total")
        val total: Long
) {
    data class Row(
            @JsonProperty("avgPrice")
            val avgPrice: String,
            @JsonProperty("executedQty")
            val executedQty: String,
            @JsonProperty("orderId")
            val orderId: Long,
            @JsonProperty("price")
            val price: String,
            @JsonProperty("qty")
            val qty: String,
            @JsonProperty("side")
            val side: String,
            @JsonProperty("symbol")
            val symbol: String,
            @JsonProperty("timeInForce")
            val timeInForce: String,
            @JsonProperty("isIsolated")
            val isIsolated: Boolean,
            @JsonProperty("updatedTime")
            val updatedTime: Long
    )
}