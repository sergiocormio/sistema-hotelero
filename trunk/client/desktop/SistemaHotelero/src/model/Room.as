package model
{
	[RemoteClass (alias="com.cdz.sh.model.Room")]
	public class Room
	{
		
		private var _id : Object;
		private var _number : int;
		private var _description : String;
		private var _roomType : RoomType;
		private var _peopleQuantity : int;
		private var _withMaritalBed : Boolean;
		private var _withExtraBed : Boolean;
		
		public function Room()
		{
		}
		

		public function get withExtraBed():Boolean
		{
			return _withExtraBed;
		}

		public function set withExtraBed(value:Boolean):void
		{
			_withExtraBed = value;
		}

		public function get withMaritalBed():Boolean
		{
			return _withMaritalBed;
		}

		public function set withMaritalBed(value:Boolean):void
		{
			_withMaritalBed = value;
		}

		public function get peopleQuantity():int
		{
			return _peopleQuantity;
		}

		public function set peopleQuantity(value:int):void
		{
			_peopleQuantity = value;
		}

		public function get roomType():RoomType
		{
			return _roomType;
		}

		public function set roomType(value:RoomType):void
		{
			_roomType = value;
		}

		public function get number():int
		{
			return _number;
		}

		public function set number(value:int):void
		{
			_number = value;
		}

		public function get description():String
		{
			return _description;
		}
		
		public function set description(value:String):void
		{
			_description = value;
		}
		
		public function get id():Object
		{
			return _id;
		}
		
		public function set id(value:Object):void
		{
			_id = value;
		}
	}
}