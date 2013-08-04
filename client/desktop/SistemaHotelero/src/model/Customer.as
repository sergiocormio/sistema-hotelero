package model
{

[RemoteClass (alias="com.cdz.sh.model.Customer")]
public class Customer
{
	private var _id:Object;
	
	private var _firstName:String;
	
	private var _lastName:String;
	
	private var _dateOfBirth:Date;
	
	private var _address:Address;
	
	private var _language:Language;
	
	private var _email:String;
	
	private var _profession:String;
	
	private var _lastLodgementDate:Date;
	
	private var _cellphoneNumber:String;
	
	private var _phoneNumber:String;
	
	private var _docType:DocumentType;    
	private var _idNumber:String;
	
	
	public function get docType():DocumentType
	{
		return _docType;
	}
	
	public function set docType(value:DocumentType):void
	{
		_docType = value;
	}
	
	public function get idNumber():String
	{
		return _idNumber;
	}
	
	public function set idNumber(value:String):void
	{
		_idNumber = value;
	}
	
	public function get name():String
	{
		var name:String  = "";
		if(_lastName != null && _lastName != ""){
			name += _lastName;
		}
		if(_firstName != null && _firstName != ""){
			name += ", " + _firstName;
		}
		if(email != null && email != ""){
			name += " [" + email + "]";
		}
		return name;
	}


	public function get lastLodgementDate():Date
	{
		return _lastLodgementDate;
	}

	public function set lastLodgementDate(value:Date):void
	{
		_lastLodgementDate = value;
	}

	public function get profession():String
	{
		return _profession;
	}

	public function set profession(value:String):void
	{
		_profession = value;
	}

	public function get email():String
	{
		return _email;
	}

	public function set email(value:String):void
	{
		_email = value;
	}

	public function get language():Language
	{
		return _language;
	}

	public function set language(value:Language):void
	{
		_language = value;
	}

	
	public function get dateOfBirth():Date
	{
		return _dateOfBirth;
	}

	public function set dateOfBirth(value:Date):void
	{
		_dateOfBirth = value;
	}

	public function get lastName():String
	{
		return _lastName;
	}

	public function set lastName(value:String):void
	{
		_lastName = value;
	}

	public function get firstName():String
	{
		return _firstName;
	}

	public function set firstName(value:String):void
	{
		_firstName = value;
	}

	public function get id():Object
	{
		return _id;
	}

	public function set id(value:Object):void
	{
		_id = value;
	}

	public function get cellphoneNumber():String
	{
		return _cellphoneNumber;
	}

	public function set cellphoneNumber(value:String):void
	{
		_cellphoneNumber = value;
	}

	public function get phoneNumber():String
	{
		return _phoneNumber;
	}

	public function set phoneNumber(value:String):void
	{
		_phoneNumber = value;
	}

	public function get address():Address
	{
		return _address;
	}

	public function set address(value:Address):void
	{
		_address = value;
	}


	public function get addressAsText():String
	{
		if(_address != null){
			return _address.street + ", " + _address.city + ",\n" +
				   _address.zipCode + ", " + _address.state + ",\n" +
				   _address.region.country.name;
		}
		else{
			return "";
		}
	}
}

}