package model
{
	import mx.collections.ArrayCollection;

	[RemoteClass (alias="com.cdz.sh.model.request.EmailRequest")]
	public class EmailRequest
	{
		private var _from:String;
		private var _password:String;
		private var _toList:ArrayCollection;
		private var _subject:String;
		private var _body:String;
		private var _isHtml:Boolean;
		
		public function EmailRequest()
		{
		}
		
		//GETTERS AND SETTERS
		public function get from():String
		{
			return _from;
		}

		public function set from(value:String):void
		{
			_from = value;
		}

		public function get password():String
		{
			return _password;
		}

		public function set password(value:String):void
		{
			_password = value;
		}

		public function get toList():ArrayCollection
		{
			return _toList;
		}

		public function set toList(value:ArrayCollection):void
		{
			_toList = value;
		}

		public function get subject():String
		{
			return _subject;
		}

		public function set subject(value:String):void
		{
			_subject = value;
		}

		public function get body():String
		{
			return _body;
		}

		public function set body(value:String):void
		{
			_body = value;
		}

		public function get isHtml():Boolean
		{
			return _isHtml;
		}

		public function set isHtml(value:Boolean):void
		{
			_isHtml = value;
		}


	}
}