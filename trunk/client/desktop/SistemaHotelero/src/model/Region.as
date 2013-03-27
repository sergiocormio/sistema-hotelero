package model
{

[RemoteClass (alias="com.cdz.sh.model.Region")]
public class Region implements IPlace
{
	private var _id:Object;
	private var _name:String;
	private var _country:Country;
	
	public function get country():Country
	{
		return _country;
	}

	public function set country(value:Country):void
	{
		_country = value;
	}

	public function get name():String
	{
		return _name;
	}
	
	public function get qualifiedName():String
	{
		return _country.name + " - " + _name;
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

}

}