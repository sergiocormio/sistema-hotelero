package model
{
	import flashx.textLayout.formats.Float;

	[RemoteClass (alias="com.cdz.sh.model.ExchangeRate")]
	public class ExchangeRate
	{
		
		private var _id : Object;
		private var _name : String;
		private var _currencySymbol : String;
		private var _valueAgainstReal : Float;
		
		public function ExchangeRate()
		{
		}
		
		public function get valueAgainstReal():Float
		{
			return _valueAgainstReal;
		}

		public function set valueAgainstReal(value:Float):void
		{
			_valueAgainstReal = value;
		}

		public function get currencySymbol():String
		{
			return _currencySymbol;
		}

		public function set currencySymbol(value:String):void
		{
			_currencySymbol = value;
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