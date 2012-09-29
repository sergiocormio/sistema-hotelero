package services
{
	public class OccupationService extends RemoteObjectWrapperService
	{
		public function OccupationService()
		{
			super("occupationService");
		}
		
		public function checkAvailability(dateFrom:Date , dateTo:Date, adultsQty:int, childrenQty:int,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.checkAvailability.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.checkAvailability.addEventListener("fault", faultHandler);
			}
			remoteObject.checkAvailability(dateFrom,dateTo,adultsQty,childrenQty);
		}
		
		public function retrieveOccupations(dateFrom:Date , dateTo:Date,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveOccupations.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveOccupations.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveOccupations(dateFrom,dateTo);
		}
	}
}