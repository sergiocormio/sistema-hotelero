package model
{
	public class Url
	{
		private var _label: String;
		private var _value: String;
		
		public function Url()
		{
		}
		
		public function get label():String
		{
			return _label;
		}

		public function set label(value:String):void
		{
			_label = value;
		}

		public function get value():String
		{
			return _value;
		}

		public function set value(value:String):void
		{
			_value = value;
		}


	}
}