package model
{
	[RemoteClass (alias="com.cdz.sh.model.ServiceAddedInBasePrice")]
	public class ServiceAddedInBasePrice
	{
		
		private var _serviceType:ServiceType; 
		
		private var _price:Number;

		
				
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
		
	}
}