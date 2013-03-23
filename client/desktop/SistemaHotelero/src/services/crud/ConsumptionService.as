package services.crud
{
	import services.RemoteObjectWrapperService;
	import model.Room;

public class ConsumptionService extends CRUDService
{
	public function ConsumptionService(){
		super("consumptionService");
	}
	
	public function retrieveConsumptions(dateFrom:Date, dateTo:Date, room:Room, resultHandler:Function,faultHandler:Function=null):void{
		remoteObject.retrieveConsumptions.addEventListener("result", resultHandler);
		if(faultHandler!=null){
			remoteObject.retrieveConsumptions.addEventListener("fault", faultHandler);
		}
		remoteObject.retrieveConsumptions(dateFrom, dateTo, room);
	}
               
}

}
