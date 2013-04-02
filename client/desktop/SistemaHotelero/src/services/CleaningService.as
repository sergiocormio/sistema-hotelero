package services
{
	import mx.collections.ArrayCollection;
		
	public class CleaningService extends RemoteObjectWrapperService
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
		
		public function exportData(cleanings:ArrayCollection, selectedLocale:String, resultHandler:Function, faultHandler:Function=null):void{
			remoteObject.exportData.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.exportData.addEventListener("fault", faultHandler);
			}
			remoteObject.exportData(cleanings, selectedLocale);
		}
		

	}
}