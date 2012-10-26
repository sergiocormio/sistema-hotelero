package model.exceptions
{
	[RemoteClass (alias="com.cdz.sh.dao.exception.InvalidParameterException")]
	public class InvalidParameterException
	{
		private var _errorCode:String;
		private var _message:String;
		
		public function InvalidParameterException()
		{
		}

		public function get message():String
		{
			return _message;
		}

		public function set message(value:String):void
		{
			_message = value;
		}

		public function get errorCode():String
		{
			return _errorCode;
		}

		public function set errorCode(value:String):void
		{
			_errorCode = value;
		}

	}
}