package model
{
	

	[RemoteClass (alias="com.cdz.sh.model.EmailTemplate")]
	public class EmailTemplate
	{
		private var _id : EmailTemplatePK;
		private var _header : String;
		private var _footer : String;
		
		public function EmailTemplate()
		{
		}
		 

		

		public function get id():EmailTemplatePK
		{
			return _id;
		}

		public function set id(value:EmailTemplatePK):void
		{
			_id = value;
		}

		public function get header():String
		{
			return _header;
		}

		public function set header(value:String):void
		{
			_header = value;
		}

		public function get footer():String
		{
			return _footer;
		}

		public function set footer(value:String):void
		{
			_footer = value;
		}


	}
}