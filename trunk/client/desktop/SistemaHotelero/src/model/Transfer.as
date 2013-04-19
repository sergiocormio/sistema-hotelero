package model
{
	[RemoteClass (alias="com.cdz.sh.model.Transfer")]
	public class Transfer
	{
		private var _id:Object;
		private var _reservationForm:ReservationForm;
		private var _serviceType:ServiceType;
		private var _source:String;
		private var _flightOrBusNumber:String;
		private var _companyName:String;
		private var _date:Date;
		
		public function Transfer()
		{
		}

		public function get id():Object
		{
			return _id;
		}

		public function set id(value:Object):void
		{
			_id = value;
		}

		public function get reservationForm():ReservationForm
		{
			return _reservationForm;
		}

		public function set reservationForm(value:ReservationForm):void
		{
			_reservationForm = value;
		}

		public function get serviceType():ServiceType
		{
			return _serviceType;
		}

		public function set serviceType(value:ServiceType):void
		{
			_serviceType = value;
		}

		public function get source():String
		{
			return _source;
		}

		public function set source(value:String):void
		{
			_source = value;
		}

		public function get flightOrBusNumber():String
		{
			return _flightOrBusNumber;
		}

		public function set flightOrBusNumber(value:String):void
		{
			_flightOrBusNumber = value;
		}

		public function get companyName():String
		{
			return _companyName;
		}

		public function set companyName(value:String):void
		{
			_companyName = value;
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