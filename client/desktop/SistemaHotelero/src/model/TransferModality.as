package model
{
	import mx.collections.ArrayCollection;

	public class TransferModality
	{
		static public const CARRY_BACK:String = "CARRY_BACK";
		static public const TAKE:String = "TAKE";
		
		
		static public const TransferModalityIds:ArrayCollection = new ArrayCollection([CARRY_BACK, TAKE]);
		
		private var _id:String;
		private var _label:String;
		
		public function TransferModality(id:String,loc:Object)
		{
			_id = id;
			_label = loc.TransferModality[id];
		}
		
		public static function convertToLabel(id:String,loc:Object):String{
			return loc.TransferModality[id];
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