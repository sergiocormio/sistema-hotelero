package services.crud
{
	import services.RemoteObjectWrapperService;
	
	public class TransferService extends CRUDService
	{
		public function TransferService()
		{
			super("transferService");
		}
		
		public function retrieveTransfers(dateFrom:Date , dateTo:Date,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveTransfers.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveTransfers.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveTransfers(dateFrom,dateTo);
		}
	}
}