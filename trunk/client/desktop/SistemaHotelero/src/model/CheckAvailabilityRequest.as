package model
{
	[RemoteClass (alias="com.cdz.sh.model.request.CheckAvailabilityRequest")]
	public class CheckAvailabilityRequest
	{
		private var _dateFrom:Date;
		private var _dateTo:Date;
		private var _adultsQty:int;
		private var _childrenQty:int;
		private var _withMaritalBed:Boolean;
		
		public function CheckAvailabilityRequest()
		{
		}

		public function get withMaritalBed():Boolean
		{
			return _withMaritalBed;
		}

		public function set withMaritalBed(value:Boolean):void
		{
			_withMaritalBed = value;
		}

		public function get childrenQty():int
		{
			return _childrenQty;
		}

		public function set childrenQty(value:int):void
		{
			_childrenQty = value;
		}

		public function get adultsQty():int
		{
			return _adultsQty;
		}

		public function set adultsQty(value:int):void
		{
			_adultsQty = value;
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

	}
}