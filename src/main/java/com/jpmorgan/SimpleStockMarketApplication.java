package com.jpmorgan;

import java.math.BigDecimal;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpmorgan.api.stock_market.StockMarket;
import com.jpmorgan.context.Context;
import com.jpmorgan.model.stock.StockSymbol;

@SpringBootApplication
public class SimpleStockMarketApplication {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final StockMarket stockMarket;

	public SimpleStockMarketApplication() {
		stockMarket = Context.getInjector().getInstance(StockMarket.class);
	}

	public static void main(String[] args) {
		new SimpleStockMarketApplication().run(args);
	}

	public void run(String... args) {
		logger.info("Starting the Application...");
		Scanner in = new Scanner(System.in);

		boolean repeat = true;

		while (repeat) {
			System.out.println("Please enter Stock Symbol.....");
			String input = in.next().toUpperCase();
			StockSymbol stockSymbol = StockSymbol.valueOf(input);

			System.out.println("Please enter Price.......");
			BigDecimal price = in.nextBigDecimal();

			stockMarket.process(stockSymbol, price);

			System.out.println("More Stock? (Y/N)");
			String answer = in.next();

			if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("NO")) {
				repeat = false;
			}
		}
		in.close();
		System.out.println("Application Stopped");
	}

}
