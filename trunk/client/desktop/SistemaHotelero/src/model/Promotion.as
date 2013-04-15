package model
{
	[RemoteClass (alias="com.cdz.sh.model.Promotion")]
	public class Promotion
	{
		private var _id : Object;
		private var _name : String;
		private var _description : String;
		private var _roomType:RoomType;
		private var _dateFrom : Date;
		private var _dateTo : Date;
		private var _price : Number;
		
		public function Promotion()
		{
		}

		public function get name():String
		{
			return _name;
		}
		
		public function set name(value:String):void
		{
			_name = value;
		}
		
		public function get id():Object
		{
			return _id;
		}
		
		public function set id(value:Object):void
		{
			_id = value;
		}    

		public function get description():String
		{
			return _description;
		}

		public function set description(value:String):void
		{
			_description = value;
		}

		public function get dateFrom():Date
		{
			return _dateFrom;
		}

		public function set dateFrom(value:Date):void
		{
			_dateFrom = value;
		}

		public function get dateTo():Date
		{
			return _dateTo;
		}

		public function set dateTo(value:Date):void
		{
			_dateTo = value;
		}

		public function get price():Number
		{
			return _price;
		}

		public function set price(value:Number):void
		{
			_price = value;
		}

		public function get roomType():RoomType
		{
			return _roomType;
		}

		public function set roomType(value:RoomType):void
		{
			_roomType = value;
		}


		[Transient]
		public function get priceWithDefaultCurrency():String
		{
			return "R$ " + _price.toFixed(2);
		}
	}
}