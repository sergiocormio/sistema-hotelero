package utils
{
	public class DateRange
	{
		private var _dateFrom:Date;
		private var _dateTo:Date;
		
		public function DateRange(from:Date,to:Date)
		{
			_dateFrom = from;
			_dateTo = to;
		}

		public function get dateTo():Date
		{
			return _dateTo;
		}

		public function set dateTo(value:Date):void
		{
			_dateTo = value;
		}

		public function get dateFrom():Date
		{
			return _dateFrom;
		}

		public function set dateFrom(value:Date):void
		{
			_dateFrom = value;
		}
		
		public static function validRange(dateFrom:Date, dateTo:Date):Boolean{
		
			if(dateFrom.time < dateTo.time){
				return true;
			}
			else{
				return false;
			}
		}

	}
}