package model
{
	import mx.collections.ArrayCollection;

	[RemoteClass (alias="com.cdz.sh.model.Cleaning")]
	public class Cleaning
	{
		private var _id : CleaningPK;
		private var _assignedCleaning : ArrayCollection;
		
		public function Cleaning()
		{
		}
		 

		public function get assignedCleaning():ArrayCollection
		{
			return _assignedCleaning;
		}

		public function set assignedCleaning(value:ArrayCollection):void
		{
			_assignedCleaning = value;
		}

		public function get id():CleaningPK
		{
			return _id;
		}

		public function set id(value:CleaningPK):void
		{
			_id = value;
		}
		
		public function removeAssignedCleaning(cleaningType:String):void{
			if(this.assignedCleaning.contains(cleaningType)){
				assignedCleaning.removeItemAt(assignedCleaning.getItemIndex(cleaningType));
			}
		}
		
		public function addAssignedCleaning(cleaningType:String):void{
			if(!this.assignedCleaning.contains(cleaningType)){
				assignedCleaning.addItem(cleaningType);
			}
		}

	}
}