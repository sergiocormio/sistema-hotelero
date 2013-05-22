package model
{
	import locales.Locale;

	[RemoteClass (alias="com.cdz.sh.model.ReservationForm")]
	public class ReservationForm
	{
		private var _id : Object;
		private var _customer:Customer;
		private var _state:String; //On Server side it is a Enum;
		private var _creationDate:Date;
		private var _dateFrom:Date;
		private var _dateTo:Date;
		private var _adultsQuantity:int;
		private var _childrenQuantity:int;
		
		private var _pricePerDay:Number;
		private var _totalPrice:Number;
		private var _monetaryReserve:Number;
		private var _remainingAmount:Number;
				
		private var _bank:Bank;
		private var _bankDocumentNumber:String;
		
		public function ReservationForm()
		{
		}
		
		public function get name():String
		{
			return _id + " - " + _customer.name + " - " + StateReservationForm.convertToLabel(_state,Locale.getInstance().getCurrentLocale());
		}

		public function get bankDocumentNumber():String
		{
			return _bankDocumentNumber;
		}

		public function set bankDocumentNumber(value:String):void
		{
			_bankDocumentNumber = value;
		}

		public function get bank():Bank
		{
			return _bank;
		}

		public function set bank(value:Bank):void
		{
			_bank = value;
		}

		public function get monetaryReserve():Number
		{
			return _monetaryReserve;
		}

		public function set monetaryReserve(value:Number):void
		{
			_monetaryReserve = value;
		}

		public function get pricePerDay():Number
		{
			return _pricePerDay;
		}

		public function set pricePerDay(value:Number):void
		{
			_pricePerDay = value;
		}
		
		public function get totalPrice():Number
		{
			return _totalPrice;
		}
		
		public function set totalPrice(value:Number):void
		{
			_totalPrice = value;
		}
		
		public function get remainingAmount():Number
		{
			return _remainingAmount;
		}
		
		public function set remainingAmount(value:Number):void
		{
			_remainingAmount = value;
		}
		
		

		public function get childrenQuantity():int
		{
			return _childrenQuantity;
		}

		public function set childrenQuantity(value:int):void
		{
			_childrenQuantity = value;
		}

		public function get adultsQuantity():int
		{
			return _adultsQuantity;
		}

		public function set adultsQuantity(value:int):void
		{
			_adultsQuantity = value;
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

		public function get creationDate():Date
		{
			return _creationDate;
		}

		public function set creationDate(value:Date):void
		{
			_creationDate = value;
		}

		public function get state():String
		{
			return _state;
		}

		public function set state(value:String):void
		{
			_state = value;
		}

		public function get customer():Customer
		{
			return _customer;
		}

		public function set customer(value:Customer):void
		{
			_customer = value;
		}

		public function get id():Object
		{
			return _id;
		}

		public function set id(value:Object):void
		{
			_id = value;
		}
		
		/*** CRUD form screen */
		
		public function get pricePerDayWithDefaultCurrency():String
		{
			return "R$ " + _pricePerDay.toFixed(2);
		}
		
		public function get totalPriceWithDefaultCurrency():String
		{
			return "R$ " + _totalPrice.toFixed(2);
		}
		
		public function get monetaryReserveWithDefaultCurrency():String
		{
			return "R$ " + _monetaryReserve.toFixed(2);
		}

		
		
	}
}