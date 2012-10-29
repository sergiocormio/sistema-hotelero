package model
{

[RemoteClass (alias="com.cdz.sh.model.Customer")]
public class Customer
{
	private var _id:CustomerPK;
	
	private var _firstName:String;
	
	private var _lastName:String;
	
	private var _dateOfBirth:Date;
	
	private var _region:Region;
	
	private var _language:Language;
	
	private var _email:String;
	
	private var _profession:String;
	
	private var _lastLodgementDate:Date;
	
	private var _cellphoneNumber:String;
	
	private var _phoneNumber:String;
	
	public function get name():String
	{
		return _lastName + ", " + firstName + " [" + id.docType.name + ": " + id.idNumber + "]";
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

	public function get region():Region
	{
		return _region;
	}

	public function set region(value:Region):void
	{
		_region = value;
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

	public function get id():CustomerPK
	{
		return _id;
	}

	public function set id(value:CustomerPK):void
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


}

}