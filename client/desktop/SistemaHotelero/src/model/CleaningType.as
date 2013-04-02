package model
{
	import mx.collections.ArrayCollection;

	public class CleaningType
	{
		static public const GENERAL:String = "GENERAL";
		static public const BASIC:String = "BASIC";
		static public const BED_CLOTHE_CHANGE:String = "BED_CLOTHE_CHANGE";
		
		
		static public const cleanigTypeIds:ArrayCollection = new ArrayCollection([GENERAL, BASIC, BED_CLOTHE_CHANGE]);
		
		private var _id:String;
		private var _label:String;
		public function CleaningType(id:String,loc:Object)
		{
			_id = id;
			_label = loc.cleaningType.state[id];
		}
		
		public static function convertToLabel(id:String,loc:Object):String{
			return loc.cleaningType.state[id];
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