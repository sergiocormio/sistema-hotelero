package model
{
	import locales.Locale;
	
	import mx.collections.ArrayCollection;

	public class ServiceTypeModality
	{
		static public const PER_PERSON:String = "PER_PERSON";
		static public const PER_TIME:String = "PER_TIME";
		static public const PER_UNIT:String = "PER_UNIT";
		static public const PER_ROOM:String = "PER_ROOM";
		static public const PER_NIGHT:String = "PER_NIGHT";
		
		static public const modalityIds:ArrayCollection = new ArrayCollection([PER_PERSON,PER_TIME,PER_UNIT,PER_ROOM,PER_NIGHT]);
		
		private var _id:String;
		private var _label:String;
		
		public function ServiceTypeModality(id:String,loc:Object)
		{
			_id = id;
			_label = loc.serviceType.modality[id];
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