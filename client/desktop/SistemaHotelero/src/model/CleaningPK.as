package model
{
	[RemoteClass (alias="com.cdz.sh.model.CleaningPK")]
	public class CleaningPK
	{
		private var _date:Date;
		private var _room:Room;
		
		public function CleaningPK()
		{
		}

		public function get date():Date
		{
			return _date;
		}

		public function set date(value:Date):void
		{
			_date = value;
		}

		public function get room():Room
		{
			return _room;
		}

		public function set room(value:Room):void
		{
			_room = value;
		}

	}
}