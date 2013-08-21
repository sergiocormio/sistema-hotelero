package model
{
	[RemoteClass (alias="com.cdz.sh.model.EmailTemplatePK")]
	public class EmailTemplatePK
	{
		public static var BUDGET_RESPONSE:String = "budgetResponse";
		
		private var _templateId:String;
		private var _locale:String;
		
		public function EmailTemplatePK()
		{
		}

		
		public function get templateId():String
		{
			return _templateId;
		}

		public function set templateId(value:String):void
		{
			_templateId = value;
		}

		public function get locale():String
		{
			return _locale;
		}

		public function set locale(value:String):void
		{
			_locale = value;
		}
	}
}