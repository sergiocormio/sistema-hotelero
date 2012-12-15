package com.cdz.sh.util;

import java.text.DecimalFormat;

import com.cdz.sh.model.ExchangeRate;

public class PriceFormater {
	
	
	public static String formatPrice(Double price){
		if(price != null){
			return "R$ " + new DecimalFormat("#.00").format(price);
		}
		else{
			return "";
		}
	}
	
	
	public static String formatPrice(Double price, ExchangeRate exchangeRate){
		if(price != null){
			return exchangeRate.getCurrencySymbol() + " " + new DecimalFormat("#.00").format(price);
		}
		else{
			return "";
		}
	}

}
