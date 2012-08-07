package model
{
public class Language
{
	private var _id : Object;
	private var _name : String;
	
	
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
}

}