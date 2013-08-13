package model
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;

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
		
		private var _roomType:RoomType;
		
		private var _pricePerDay:Number;
		
		private var _daysQuantity:Number;
		
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

		[Bindable]
		public function get additionalServices():ArrayCollection
		{
			return _additionalServices;
		}

		public function set additionalServices(value:ArrayCollection):void
		{
			_additionalServices = value;
		}

		[Bindable]
		public function get servicePricesAddedInBasePrice():ArrayCollection
		{
			return _servicePricesAddedInBasePrice;
		}

		public function set servicePricesAddedInBasePrice(value:ArrayCollection):void
		{
			_servicePricesAddedInBasePrice = value;
		}

		public function get pricePerDay():Number
		{
			return _pricePerDay;
		}
		
		public function set pricePerDay(value:Number):void
		{
			_pricePerDay = value;
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
		
		
		/* availability screen */
		
		public function get pricePerDayWithDefaultCurrency():String
		{
			return "R$ " + _pricePerDay.toFixed(2);
		}
		
		public function get basePriceWithDefaultCurrency():String
		{
			return "R$ " + _basePrice.toFixed(2);
		}
		
		public function get basePricePlusAllServicesIncludedInBasePriceWithDefaultCurrency():String
		{
			return "R$ " + _basePricePlusAllServicesIncludedInBasePrice..toFixed(2);
		}
		
		/**************/
		
		/* budget screen */
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
		
		public function get pricePerDayWithCurrency():String
		{
			return _exchangeRate.currencySymbol + " " + _pricePerDay.toFixed(2);
		}
		
		public function get basePriceWithCurrency():String
		{
			return _exchangeRate.currencySymbol + " " + _basePrice.toFixed(2);
		}
		
		public function get basePricePlusAllServicesIncludedInBasePriceWithCurrency():String
		{
			return _exchangeRate.currencySymbol + " " + _basePricePlusAllServicesIncludedInBasePrice..toFixed(2);
		}

		[Bindable]
		public function get roomType():RoomType
		{
			return _roomType;
		}

		public function set roomType(value:RoomType):void
		{
			_roomType = value;
		}

		[Bindable]
		public function get daysQuantity():Number
		{
			return _daysQuantity;
		}

		public function set daysQuantity(value:Number):void
		{
			_daysQuantity = value;
		}

		
	}
}