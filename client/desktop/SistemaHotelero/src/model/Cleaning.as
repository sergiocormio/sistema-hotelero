package model
{
	[RemoteClass (alias="com.cdz.sh.model.Cleaning")]
	public class Cleaning
	{
		private var _occupation : Occupation;
		private var _cleaningType : String;
		
		public function Cleaning()
		{
		}

		 
		public function get occupation():Occupation
		{
			return _occupation;
		}

		public function set occupation(value:Occupation):void
		{
			_occupation = value;
		}
		
		public function get cleaningType():String
		{
			return _cleaningType;
		}

		public function set cleaningType(value:String):void
		{
			_cleaningType = value;
		}


	}
}