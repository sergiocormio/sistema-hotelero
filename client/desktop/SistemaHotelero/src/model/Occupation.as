package model
{
	import utils.DateTimeUtils;

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

		public function compareTo(occupationj:Occupation):int
		{
			if(this._id.date.time > occupationj.id.date.time){
				return 1;
			}
			else if(this._id.date.time < occupationj.id.date.time){
				return -1;
			}
			else{
				return 0;
			}
		}
	}
}