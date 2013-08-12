package model
{
	import utils.DateTimeUtils;
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
		[Bindable]
		private var _promotion:Promotion;
		
		public function Alternative()
		{
			_occupations = new ArrayCollection();
			_distinctRooms = new ArrayCollection();
		}
		
		//GETTERS AND SETTERS
		[Bindable] 
		public function get promotion():Promotion
		{
			return _promotion;
		}

		public function set promotion(value:Promotion):void
		{
			_promotion = value;
		}

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
		
		public function get dateFrom():String {
			if(occupations != null && occupations.length != 0){
				var dateFrom:Date = (occupations.getItemAt(0) as Occupation).id.date;
				return DateTimeUtils.formatDate(dateFrom);
			}
			return null;
		}
		
		
		public function get dateTo():String {
			if(occupations != null && occupations.length != 0){
				var dateTo:Date = (occupations.getItemAt(occupations.length-1) as Occupation).id.date;
				return DateTimeUtils.formatDate(dateTo);
			}
			return null;
		}

	}
}