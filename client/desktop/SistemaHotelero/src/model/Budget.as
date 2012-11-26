package model
{
	import mx.collections.ArrayCollection;
	import flash.events.Event;

	[RemoteClass (alias="com.cdz.sh.model.Budget")]
	public class Budget
	{
		// only to tell Blaze DS the arrayCollection data type
		private var _serviceAddedInBasePriceStupid:ServiceAddedInBasePrice; 
		
		// only to tell Blaze DS the arrayCollection data type
		private var _serviceTypeStupid:ServiceType;
		
		//to be used only in UI
		private var _exchangeRate:ExchangeRate;
		
		private var _relatedAlternative:Alternative;
		
		private var _basePrice:Number;
		
		private var _basePricePlusAllServicesIncludedInBasePrice:Number;
		
		/*
		* The price of these ones are calculated according to the serciveTypeModality
		*/
		private var _servicePricesAddedInBasePrice:ArrayCollection;
		
		/*
		* There is no need in calculating the price of the ones below, because they will not be added in the basePrice
		*/
		private var _additionalServices:ArrayCollection;
		
		public function Budget()
		{
		}

		public function get basePricePlusAllServicesIncludedInBasePrice():Number
		{
			return _basePricePlusAllServicesIncludedInBasePrice;
		}

		public function set basePricePlusAllServicesIncludedInBasePrice(value:Number):void
		{
			_basePricePlusAllServicesIncludedInBasePrice = value;
		}

		public function get additionalServices():ArrayCollection
		{
			return _additionalServices;
		}

		public function set additionalServices(value:ArrayCollection):void
		{
			_additionalServices = value;
		}

		public function get servicePricesAddedInBasePrice():ArrayCollection
		{
			return _servicePricesAddedInBasePrice;
		}

		public function set servicePricesAddedInBasePrice(value:ArrayCollection):void
		{
			_servicePricesAddedInBasePrice = value;
		}

		public function get basePrice():Number
		{
			return _basePrice;
		}

		public function set basePrice(value:Number):void
		{
			_basePrice = value;
		}

		public function get relatedAlternative():Alternative
		{
			return _relatedAlternative;
		}

		public function set relatedAlternative(value:Alternative):void
		{
			_relatedAlternative = value;
		}
		
		public function set exchangeRate(exchangeRate:ExchangeRate):void
		{
			_exchangeRate = exchangeRate;
			
			for each (var serviceAddedInBasePrice:ServiceAddedInBasePrice in _servicePricesAddedInBasePrice) 
			{
				serviceAddedInBasePrice.setExchangeRate(exchangeRate);
			}
			
			for each (var serviceType:ServiceType in _additionalServices) 
			{
				serviceType.setExchangeRate(exchangeRate);
			}
		}
		
		public function get basePriceWithCurrency():String
		{
			return _exchangeRate.currencySymbol + " " + _basePrice.toFixed(2);
		}
		
		public function get basePricePlusAllServicesIncludedInBasePriceWithCurrency():String
		{
			return _exchangeRate.currencySymbol + " " + _basePricePlusAllServicesIncludedInBasePrice..toFixed(2);
		}

		
	}
}