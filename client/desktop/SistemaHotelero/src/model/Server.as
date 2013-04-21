package model
{
	[RemoteClass (alias="com.cdz.sh.model.Server")]
	public class Server
	{
		private var _id : Object;
		private var _active : Boolean;
		
		public function Server()
		{
		}
		
		
		public function get id():Object
		{
			return _id;
		}
		
		public function set id(value:Object):void
		{
			_id = value;
		}    
		
		public function get active():Boolean
		{
			return _active;
		}
		
		public function set active(value:Boolean):void
		{
			_active = value;
		}
		
		
	}
}