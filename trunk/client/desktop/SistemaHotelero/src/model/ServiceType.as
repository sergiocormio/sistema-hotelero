package model
{
	[RemoteClass (alias="com.cdz.sh.model.ServiceType")]
	public class ServiceType
	{
		private var _id : Object;
		private var _name : String;
		
		private var _description:String;
		
		private var _includedInBudget:Boolean;
		
		private var _includedInBasePrice:Boolean;
		
		private var _modality:String; //On Server side it is a Enum
		
		private var _price:Number;

		
		
		public function get price():Number
		{
			return _price;
		}

		public function set price(value:Number):void
		{
			_price = value;
		}

		public function get modality():String
		{
			return _modality;
		}

		public function set modality(value:String):void
		{
			_modality = value;
		}

		public function get includedInBasePrice():Boolean
		{
			return _includedInBasePrice;
		}

		public function set includedInBasePrice(value:Boolean):void
		{
			_includedInBasePrice = value;
		}

		public function get includedInBudget():Boolean
		{
			return _includedInBudget;
		}

		public function set includedInBudget(value:Boolean):void
		{
			_includedInBudget = value;
		}

		public function get description():String
		{
			return _description;
		}

		public function set description(value:String):void
		{
			_description = value;
		}

		public function get name():String
		{
			return _name;
		}
		
		public function set name(value:String):void
		{
			_name = value;
		}
		
		public function get id():Object
		{
			return _id;
		}
		
		public function set id(value:Object):void
		{
			_id = value;
		}    
		
		public function ServiceType()
		{
		}
	}
}