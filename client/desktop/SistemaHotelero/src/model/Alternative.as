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
		
		//other attributtes
		private var _dateFrom:Date;
		private var _dateTo:Date;
		
		public function Alternative()
		{
		}
		
		public function get dateTo():Date
		{
			if(_dateTo == null){
				var oc:Occupation = getMaxOccupation();
				if(oc!=null){
					_dateTo = oc.id.date;
				}
			}
			return _dateTo;
		}
		
		private function getMinOccupation():Occupation{
			var result:Occupation = null;
			
			if(occupations!=null){
				for each (var oc:Occupation in occupations){
					if(result==null || result.id.date > oc.id.date){
						result = oc;
					}
				}
			}
			return result;
		}
		
		private function getMaxOccupation():Occupation{
			var result:Occupation = null;
			
			if(occupations!=null){
				for each (var oc:Occupation in occupations){
					if(result==null || result.id.date < oc.id.date){
						result = oc;
					}
				}
			}
			return result;
		}
		
		public function set dateTo(value:Date):void
		{
			_dateTo = value;
		}
		
		public function get dateFrom():Date
		{
			if(_dateFrom == null){
				var oc:Occupation = getMinOccupation();
				if(oc!=null){
					_dateFrom = oc.id.date;
				}
			}
			return _dateFrom;
		}
		
		public function set dateFrom(value:Date):void
		{
			_dateFrom = value;
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