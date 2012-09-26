package model
{
	import mx.collections.ArrayCollection;

	[RemoteClass (alias="com.cdz.sh.model.Alternative")]
	public class Alternative
	{
		private var _peopleQuantity:int;
		private var _occupations:ArrayCollection;
		private var _budget:Budget;
		private var _lastRoom:Room;
		private var _roomChanges:int;
		private var _newRoomAvailableDays:int;
		private var _distinctRooms:ArrayCollection;
		
		public function Alternative()
		{
		}
		
		//GETTERS AND SETTERS
		public function get distinctRooms():ArrayCollection
		{
			return _distinctRooms;
		}

		public function set distinctRooms(value:ArrayCollection):void
		{
			_distinctRooms = value;
		}

		public function get newRoomAvailableDays():int
		{
			return _newRoomAvailableDays;
		}

		public function set newRoomAvailableDays(value:int):void
		{
			_newRoomAvailableDays = value;
		}

		public function get roomChanges():int
		{
			return _roomChanges;
		}

		public function set roomChanges(value:int):void
		{
			_roomChanges = value;
		}

		public function get lastRoom():Room
		{
			return _lastRoom;
		}

		public function set lastRoom(value:Room):void
		{
			_lastRoom = value;
		}

		public function get budget():Budget
		{
			return _budget;
		}

		public function set budget(value:Budget):void
		{
			_budget = value;
		}

		public function get occupations():ArrayCollection
		{
			return _occupations;
		}

		public function set occupations(value:ArrayCollection):void
		{
			_occupations = value;
		}

		public function get peopleQuantity():int
		{
			return _peopleQuantity;
		}

		public function set peopleQuantity(value:int):void
		{
			_peopleQuantity = value;
		}

	}
}