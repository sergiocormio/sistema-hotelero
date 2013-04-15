package services.crud
{
	import services.RemoteObjectWrapperService;
	
	public class ServiceTypeService extends CRUDService
	{
		public function ServiceTypeService() 
		{
			super("serviceTypeService");
		}
		
		public function getTransferServiceTypes(resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.getTransferServiceTypes.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.getTransferServiceTypes.addEventListener("fault", faultHandler);
			}
			remoteObject.getTransferServiceTypes();
		}
	}
}