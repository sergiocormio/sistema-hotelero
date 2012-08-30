package model
{

import com.adobe.fiber.core.model_internal;

[RemoteClass (alias="com.cdz.sh.model.DocumentType")]
public class DocumentType
{
	[Bindable]
	public static var DEFAULT_REGEXP:String = '^[0-9]';
	
	private var _id : Object;
	private var _regExp : String;
	private var _name : String;
	
	
	public function get name():String
	{
		return _name;
	}

	public function set name(value:String):void
	{
		_name = value;
	}

	public function get regExp():String
	{
		return _regExp;
	}

	public function set regExp(value:String):void
	{
		_regExp = value;
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