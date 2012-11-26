package model
{
	[RemoteClass (alias="com.cdz.sh.model.ServiceAddedInBasePrice")]
	public class ServiceAddedInBasePrice
	{
		
		private var _serviceType:ServiceType; 
		
		private var _price:Number;

		//to be used only in UI
		private var _exchangeRate:ExchangeRate;
		
				
		public function get price():Number
		{
			return _price;
		}

		public function set price(value:Number):void
		{
			_price = value;
		}

		public function get serviceType():ServiceType
		{
			return _serviceType;
		}

		public function set serviceType(value:ServiceType):void
		{
			_serviceType = value;
		}

		public function ServiceAddedInBasePrice()
		{
		}
		
		[Transient]
		public function setExchangeRate(exchangeRate:ExchangeRate):void
		{
			_exchangeRate = exchangeRate;
		}
		
		[Transient]
		public function get priceWithCurrency():String
		{
			return _exchangeRate.currencySymbol + " " + _price.toFixed(2);
		}
	}
}