package services.crud
{
	import services.RemoteObjectWrapperService;

	public class CRUDService extends RemoteObjectWrapperService
	{
		public function CRUDService(destination:String)
		{
			super(destination);
		}
		
		public function retrieveAll(resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveAll.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveAll.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveAll();
		}
		
		public function createRecord(obj:Object,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.createRecord.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.createRecord.addEventListener("fault", faultHandler);
			}
			remoteObject.createRecord(obj);
		}
		
		public function updateRecord(obj:Object,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.updateRecord.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.updateRecord.addEventListener("fault", faultHandler);
			}
			remoteObject.updateRecord(obj);
		}
		
		public function deleteRecord(obj:Object,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.deleteRecord.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.deleteRecord.addEventListener("fault", faultHandler);
			}
			remoteObject.deleteRecord(obj);
		}
	}
}