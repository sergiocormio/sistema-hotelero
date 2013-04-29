package services.crud
{
	import model.Transfer;
	
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
		
		public function createRecords(transfer1:Transfer, transfer2:Transfer , resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.createRecords.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.createRecords.addEventListener("fault", faultHandler);
			}
			remoteObject.createRecords(transfer1,transfer2);
		}
	}
}