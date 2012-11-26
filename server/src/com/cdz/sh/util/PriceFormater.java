package com.cdz.sh.util;

import java.text.DecimalFormat;

import com.cdz.sh.model.ExchangeRate;

public class PriceFormater {
	
	
	public static String formatPrice(Double price){
		return "R$ " + new DecimalFormat("#.00").format(price);
	}
	
	
	public static String formatPrice(Double price, ExchangeRate exchangeRate){
		return exchangeRate.getCurrencySymbol() + " " + new DecimalFormat("#.00").format(price);
	}

}
