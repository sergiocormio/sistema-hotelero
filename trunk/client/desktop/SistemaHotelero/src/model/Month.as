package model
{
	import mx.collections.ArrayCollection;

	public class Month
	{
		static public const JANUARY:String = "january";
		static public const FEBRUARY:String = "february";
		static public const MARCH:String = "march";
		static public const APRIL:String = "april";
		static public const MAY:String = "may";
		static public const JUNE:String = "june";
		static public const JULY:String = "july";
		static public const AUGUST:String = "august";
		static public const SEPTEMBER:String = "september";
		static public const OCTOBER:String = "october";
		static public const NOVEMBER:String = "november";
		static public const DECEMBER:String = "december";
		
		
		static public const monthIds:ArrayCollection = new ArrayCollection([JANUARY, FEBRUARY, MARCH, APRIL, MAY,
																			JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER,
																			NOVEMBER, DECEMBER]);
		
		private var _id:String;
		private var _label:String;
		
		public function Month(id:String,loc:Object)
		{
			_id = id;
			_label = loc.month[id];
		}
		
		public static function convertToLabel(id:String,loc:Object):String{
			return loc.month[id];
		}
		
		public function get label():String
		{
			return _label;
		}
		
		public function set label(value:String):void
		{
			_label = value;
		}
		
		public function get id():String
		{
			return _id;
		}
		
		public function set id(value:String):void
		{
			_id = value;
		}
		
		public function toString() : String 
		{
			return _label;
		}
	}
}