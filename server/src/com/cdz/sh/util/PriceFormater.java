package com.cdz.sh.util;

import java.text.DecimalFormat;

import com.cdz.sh.model.ExchangeRate;

public class PriceFormater {
	
	
	public static String formatPrice(Double price){
		if(price != null){
			String formattedPrice = "R$ ";
			if(price.equals(new Double(0))){
				formattedPrice += "0.00";
			}
			else{
				formattedPrice += new DecimalFormat("#.00").format(price); 
			}
			return formattedPrice;
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
