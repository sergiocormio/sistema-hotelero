package model
{
	import mx.collections.ArrayCollection;

	public class StateReservationForm
	{
		static public const PRE_BOOKING:String = "PRE_BOOKING";
		static public const CONFIRMED:String = "CONFIRMED";
		static public const CANCELLED:String = "CANCELLED";
		static public const EXPIRED:String = "EXPIRED";
		
		static public const stateReservationIds:ArrayCollection = new ArrayCollection([PRE_BOOKING,CONFIRMED,CANCELLED,EXPIRED]);
		
		private var _id:String;
		private var _label:String;
		public function StateReservationForm(id:String,loc:Object)
		{
			_id = id;
			_label = loc.reservationForm.state[id];
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