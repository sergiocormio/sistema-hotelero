package model
{
	import mx.collections.ArrayCollection;

	public class TransferType
	{
		static public const ONE_WAY_BUS:String = "ONE_WAY_BUS";
		static public const ROUND_TRIP_BUS:String = "ROUND_TRIP_BUS";
		static public const ONE_WAY_FLIGHT:String = "ONE_WAY_FLIGHT";
		static public const ROUND_TRIP_FLIGHT:String = "ROUND_TRIP_FLIGHT";
		
		
		static public const TransferTypeIds:ArrayCollection = new ArrayCollection([ONE_WAY_BUS, ROUND_TRIP_BUS, ONE_WAY_FLIGHT, ROUND_TRIP_FLIGHT]);
		
		private var _id:String;
		private var _label:String;
		
		public function TransferType(id:String,loc:Object)
		{
			_id = id;
			_label = loc.transferType[id];
		}
		
		public static function convertToLabel(id:String,loc:Object):String{
			return loc.transferType[id];
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