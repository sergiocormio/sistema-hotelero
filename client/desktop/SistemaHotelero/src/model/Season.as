package model
{
	[RemoteClass (alias="com.cdz.sh.model.Season")]
	public class Season
	{
		
		private var _id : Object;
		private var _name : String;
		private var _dateFrom:Date;
		private var _dateTo:Date;
		
		public function Season()
		{
		}
		
		public function get dateTo():Date
		{
			return _dateTo;
		}

		public function set dateTo(value:Date):void
		{
			_dateTo = value;
		}

		public function get dateFrom():Date
		{
			return _dateFrom;
		}

		public function set dateFrom(value:Date):void
		{
			_dateFrom = value;
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
	}
}