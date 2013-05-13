package model
{
	import mx.collections.ArrayCollection;

	/**
	 * This class is a Wrapper of the model Object Alternative.
	 * It offers some pre-processed data like involvedRooms,dateFrom and dateTo.
	 */ 
	public class AlternativeWrapper
	{
		[Bindable]
		private var _alternative:Alternative;
		private var _involvedRooms:String;
		private var _dateFrom:Date;
		private var _dateTo:Date;
		
		//returns an arrayCollection of AlternativeWrapper objects
		public static function wrapAlternatives(alternatives:ArrayCollection):ArrayCollection{
			var result:ArrayCollection = new ArrayCollection();
			if(alternatives != null){
				for each(var a:Alternative in alternatives){
					result.addItem(new AlternativeWrapper(a));
				}
			}
			return result;
		}
		
		public function AlternativeWrapper(alternative:Alternative)
		{
			_alternative = alternative;
			calculateInvolvedRooms();
			calculateDateFrom();
			calculateDateTo();
		}
		
	
		private function calculateInvolvedRooms():void
		{
			var distinctRooms:ArrayCollection = _alternative.distinctRooms; 
			var result:String = "";
			if(distinctRooms != null){
				for each (var room:Room in distinctRooms){
					if(result!=""){ 
						result += ", ";
					}
					result += room.number;
				}
			}
			_involvedRooms = result;
		}
		
		private function calculateDateFrom():void
		{
			var occupations:ArrayCollection = _alternative.occupations; 
			var dateFromAux:Date = null;
			if(occupations != null){
				for each (var oc:Occupation in occupations){
					if(dateFromAux==null || dateFromAux > oc.id.date){
						dateFromAux = oc.id.date;
					}
				}
			}
			_dateFrom = dateFromAux;
		}
		
		private function calculateDateTo():void
		{
			var occupations:ArrayCollection = _alternative.occupations; 
			var dateToAux:Date = null;
			if(occupations != null){
				for each (var oc:Occupation in occupations){
					if(dateToAux==null || dateToAux < oc.id.date){
						dateToAux = oc.id.date;
					}
				}
			}
			_dateTo = dateToAux;
		}
		
		public function get dateTo():Date
		{
			return _dateTo;
		}
		
		public function get dateFrom():Date
		{
			return _dateFrom;
		}
		
		public function get involvedRooms():String
		{
			return _involvedRooms;
		}
		
		public function get alternative():Alternative
		{
			return _alternative;
		}

	}
}