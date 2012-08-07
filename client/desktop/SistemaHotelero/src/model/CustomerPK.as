package model
{

[RemoteClass (alias="com.cdz.sh.model.CustomerPK")]
public class CustomerPK
{
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

}

}