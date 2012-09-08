package model
{
	[RemoteClass (alias="com.cdz.sh.model.RatePK")]
	public class RatePK 
	{
		private var _roomType :RoomType;
		private var _season:Season;
		
		public function RatePK()
		{
		}

		public function get season():Season
		{
			return _season;
		}

		public function set season(value:Season):void
		{
			_season = value;
		}

		public function get roomType():RoomType
		{
			return _roomType;
		}

		public function set roomType(value:RoomType):void
		{
			_roomType = value;
		}

	}
}