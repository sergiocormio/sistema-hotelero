package model
{
	[RemoteClass (alias="com.cdz.sh.model.RoomType")]
	public class RoomType
	{
			private var _id : Object;
			private var _name : String;
			private var _description : String;

			public function RoomType(){
			}
			
			public function get name():String
			{
				return _name;
			}
			
			public function set name(value:String):void
			{
				_name = value;
			}
			
			public function get description():String
			{
				return _description;
			}
			
			public function set description(value:String):void
			{
				_description = value;
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