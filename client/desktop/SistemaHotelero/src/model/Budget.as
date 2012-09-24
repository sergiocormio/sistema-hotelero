package model
{
	import mx.collections.ArrayCollection;

	[RemoteClass (alias="com.cdz.sh.model.Budget")]
	public class Budget
	{
		private var _relatedAlternative:Alternative;
		
		private var _basePrice:Number;
		
		/*
		* The price of these ones have to be calculated according to the serciveTypeModality
		*/
		private var _servicesToBeAddedInBasePrice:ArrayCollection;
		
		/*
		* There is no need in calculating the price of the ones below, because they will not be added in the basePrice
		*/
		private var _additionalServices:ArrayCollection;
		
		public function Budget()
		{
		}

		public function get additionalServices():ArrayCollection
		{
			return _additionalServices;
		}

		public function set additionalServices(value:ArrayCollection):void
		{
			_additionalServices = value;
		}

		public function get servicesToBeAddedInBasePrice():ArrayCollection
		{
			return _servicesToBeAddedInBasePrice;
		}

		public function set servicesToBeAddedInBasePrice(value:ArrayCollection):void
		{
			_servicesToBeAddedInBasePrice = value;
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