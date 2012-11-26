package model
{
	import flashx.textLayout.formats.Float;

	[RemoteClass (alias="com.cdz.sh.model.ExchangeRate")]
	public class ExchangeRate
	{
		
		private var _id : String;
		private var _name : String;
		private var _currencySymbol : String;
		private var _valueAgainstReal : Number;
		
		public function ExchangeRate()
		{
		}
		
		public function get valueAgainstReal():Number
		{
			return _valueAgainstReal;
		}
		
		public function get valueAgainstRealFormatted():String
		{
			return _valueAgainstReal.toFixed(2);
		}

		public function set valueAgainstReal(value:Number):void
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
		
		public function get id():String
		{
			return _id;
		}
		
		public function set id(value:String):void
		{
			_id = value;
		}    
		
		[Transient]
		static public function getDefault(loc:Object):ExchangeRate
		{
			var er:ExchangeRate = new ExchangeRate();
			
			er._name = loc.exchangeRate.defaultCurrency.name.singular;
			er._currencySymbol = loc.exchangeRate.defaultCurrency.symbol;
			er._valueAgainstReal = new Number(1);
			
			return er;
		}
	}
}