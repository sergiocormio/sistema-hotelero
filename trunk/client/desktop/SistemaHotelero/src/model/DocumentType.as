package model
{

import com.adobe.fiber.core.model_internal;

[RemoteClass (alias="com.cdz.sh.model.DocumentType")]
public class DocumentType
{
	[Bindable]
	public static var DEFAULT_REGEXP:String = '^[0-9]{9}';
	
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
	
	
	/**
	 * To be used in the document types grid 
	 */
	[Transient]
	public function get isNumericFormat():Boolean
	{
		if(regExp.search("[a-z]") != -1){
			return false;
		}
		else{
			return true;
		}
		
	}
	
	/**
	 * To be used in the document types grid 
	 */
	[Transient]
	public function get format():String
	{
		if(regExp.search("[a-z]") != -1){
			return "Letras y números";
		}
		else{
			return "Solo números";
		}
		
	}
	
	/**
	 * To be used in the document types grid 
	 */
	[Transient]
	public function get digits():Number
	{
		var indexOfOpenKey:int = regExp.indexOf("{");
		var indexOfCloseKey:int = regExp.indexOf("}");
		var digitsQtyStr:String = regExp.substring(indexOfOpenKey+1, indexOfCloseKey);
		
		return new Number(digitsQtyStr);
	}

}

}