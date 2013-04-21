package model
{
	[RemoteClass (alias="com.cdz.sh.model.ReservationFormExpirationDays")]
	public class ReservationFormExpirationDays
	{
		private var _id : Object;
		private var _daysToExpire : int;
		
		public function ReservationFormExpirationDays()
		{
		}

		public function get daysToExpire():int
		{
			return _daysToExpire;
		}
		
		public function set daysToExpire(value:int):void
		{
			_daysToExpire = value;
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