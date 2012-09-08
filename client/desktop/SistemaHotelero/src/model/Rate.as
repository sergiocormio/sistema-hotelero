package model
{
	[RemoteClass (alias="com.cdz.sh.model.Rate")]
	public class Rate
	{
		private var _id:RatePK;
		private var _price:Number;
		
		public function Rate()
		{
		}

		public function get price():Number
		{
			return _price;
		}

		public function set price(value:Number):void
		{
			_price = value;
		}

		public function get id():RatePK
		{
			return _id;
		}

		public function set id(value:RatePK):void
		{
			_id = value;
		}

	}
}