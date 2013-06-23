package model
{
	[RemoteClass (alias="com.cdz.sh.model.Address")]
	public class Address
	{
		private var _id : Object;
		private var _street : String;
		private var _city : String;
		private var _state : String;
		private var _country : String;
		private var _zipCode : String;
		
		public function Address()
		{
		}

		
		
		public function get id():Object
		{
			return _id;
		}
		
		public function set id(value:Object):void
		{
			_id = value;
		}    

		public function get street():String
		{
			return _street;
		}

		public function set street(value:String):void
		{
			_street = value;
		}

		public function get city():String
		{
			return _city;
		}

		public function set city(value:String):void
		{
			_city = value;
		}

		public function get state():String
		{
			return _state;
		}

		public function set state(value:String):void
		{
			_state = value;
		}

		public function get country():String
		{
			return _country;
		}

		public function set country(value:String):void
		{
			_country = value;
		}

		public function get zipCode():String
		{
			return _zipCode;
		}

		public function set zipCode(value:String):void
		{
			_zipCode = value;
		}

		
	}
}