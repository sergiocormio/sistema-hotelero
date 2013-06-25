package services
{
	import mx.collections.ArrayCollection;
	
	import services.crud.CRUDService;
		
	public class CleaningService extends CRUDService
	{
		public function CleaningService()
		{
			super("cleaningService");
		}
		
		public function retrieveRoomsToClean(date:Date, resultHandler:Function, faultHandler:Function=null):void{
			remoteObject.retrieveRoomsToClean.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveRoomsToClean.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveRoomsToClean(date);
		}
		
		public function regenerateRoomsToClean(date:Date, resultHandler:Function, faultHandler:Function=null):void{
			remoteObject.regenerateRoomsToClean.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.regenerateRoomsToClean.addEventListener("fault", faultHandler);
			}
			remoteObject.regenerateRoomsToClean(date);
		}
		
		public function exportData(cleanings:ArrayCollection, selectedLocale:String, resultHandler:Function, faultHandler:Function=null):void{
			remoteObject.exportData.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.exportData.addEventListener("fault", faultHandler);
			}
			remoteObject.exportData(cleanings, selectedLocale);
		}
		

	}
}