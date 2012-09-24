package model
{
	[RemoteClass (alias="com.cdz.sh.model.Occupation")]
	public class Occupation
	{
		private var _id:OccupationPK; 
		
		public function Occupation()
		{
		}

		public function get id():OccupationPK
		{
			return _id;
		}

		public function set id(value:OccupationPK):void
		{
			_id = value;
		}

	}
}