package com.binance.api.examples

import com.binance.api.client.BinanceApiClientFactory.Companion.newInstance
import com.binance.api.client.domain.CandlestickInterval
import com.binance.api.client.domain.rest.SymbolFilter
import com.binance.api.client.exception.BinanceApiException

class RestMarketDataEndpointsExample {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val factory = newInstance(
                args.getOrElse(0) { "API_KEY" },
                args.getOrElse(1) { "API_SECRET" }
            )
            val client = factory.newMarketDataRestClient()

            client.ping().let {
                println("binance headers: " + it.headers().toMultimap().filter { it.key.startsWith("x-mbx") })
                println("body: " + it.body()!!)
            }

            client.time().let {
                println("time: ${it.body()}")
            }

            client.exchangeInfo().let {
                return@let it.body()!!
            }.let {
                println("exchangeInfo: ${it}")
                it.exchangeFilters.forEach {
                    print("$it")
                }
                it.symbols.filter { it.filters.any { it is SymbolFilter.PriceFilter } }.forEach {
                    println("PriceFilter for ${it.symbol}: ${it.filters.find { it is SymbolFilter.PriceFilter }}")
                }
            }

            client.depth("BTCUSDT", 5).let {
                println("depth: ${it.body()}")
            }
            client.trades("BTCUSDT", 5).let {
                println("trades: ${it.body()}")
            }

            client.historicalTrades("BTCUSDT", 5, null).let {
                println("historicalTrades: ${it.body()}")
            }

            client.aggTrades("BTCUSDT", null, null, null, null).let {
                println("aggTrades: ${it.body()}")
            }

            client.candles("BTCUSDT", CandlestickInterval.HOURLY, null, null, 5).let {
                println("klines: ${it.body()}")
            }

            client.avgPrice("BTCUSDT").let {
                println("avgPrice: ${it.body()}")
            }

            try {
                client.ticker24hr("UNKNOWN").let {
                    println("ticker24hr: ${it.body()}")
                }
            } catch (e: BinanceApiException) {
                println("BinanceApiException: ${e.apiError.code} ${e.apiError.msg}")
            }

            client.tickers24hr().let {
                println("tickers24hr: ${it.body()}")
            }

            client.tickerPrice("BTCUSDT").let {
                println("tickerPrice: ${it.body()}")
            }

            client.tickersPrice().let {
                println("tickersPrice: ${it.body()}")
            }

            client.tickerBookTicker("BTCUSDT").let {
                println("tickerPrice: ${it.body()}")
            }

            client.tickersBookTicker().let {
                println("tickersBookTicker: ${it.body()}")
            }
        }

    }
}