package model
{
	[RemoteClass (alias="com.cdz.sh.model.OccupationPK")]
	public class OccupationPK
	{
		private var _date:Date;
		
		private var _room:Room;
		
		private var _reservationForm:ReservationForm;

		public function OccupationPK()
		{
		}

		public function get reservationForm():ReservationForm
		{
			return _reservationForm;
		}

		public function set reservationForm(value:ReservationForm):void
		{
			_reservationForm = value;
		}

		public function get room():Room
		{
			return _room;
		}

		public function set room(value:Room):void
		{
			_room = value;
		}

		public function get date():Date
		{
			return _date;
		}

		public function set date(value:Date):void
		{
			_date = value;
		}

	}
}