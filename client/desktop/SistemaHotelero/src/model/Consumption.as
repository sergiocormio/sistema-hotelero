package model
{

[RemoteClass (alias="com.cdz.sh.model.Consumption")]
public class Consumption
{
	private var _id:Object;
	
	private var _room:Room;
	
	private var _date:Date;
	
	private var _description:String;
		
	private var _price:Number;
	
	

	public function get id():Object
	{
		return _id;
	}

	public function set id(id:Object):void
	{
		_id = id;
	}
	
	public function get room():Room
	{
		return _room;
	}
	
	public function set room(room:Room):void
	{
		_room = room;
	}
	
	public function get date():Date
	{
		return _date;
	}

	public function set date(date:Date):void
	{
		_date = date;
	}
	
	public function get description():String
	{
		return _description;
	}
	
	public function set description(description:String):void
	{
		_description = description;
	}
	
	
	public function get price():Number
	{
		return _price;
	}
	
	public function get priceWithDefaultCurreny():String
	{
		return "R$ " + _price.toFixed(2);
	}
	
	public function set price(price:Number):void
	{
		_price = price;
	}

}

}