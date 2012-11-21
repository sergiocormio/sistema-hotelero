package model
{
	import mx.collections.ArrayCollection;

	[RemoteClass (alias="com.cdz.sh.model.Budget")]
	public class Budget
	{
		// only to tell Blaze DS the arrayCollection data type
		private var _serviceAddedInBasePriceStupid:ServiceAddedInBasePrice; 
		
		// only to tell Blaze DS the arrayCollection data type
		private var _serviceTypeStupid:ServiceType;
		
		
		
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

	}
}