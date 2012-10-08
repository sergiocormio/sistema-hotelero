package services
{
	import model.CheckAvailabilityRequest;

	public class OccupationService extends RemoteObjectWrapperService
	{
		public function OccupationService()
		{
			super("occupationService");
		}
		
		public function checkAvailability(request:CheckAvailabilityRequest, resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.checkAvailability.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.checkAvailability.addEventListener("fault", faultHandler);
			}
			remoteObject.checkAvailability(request);
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