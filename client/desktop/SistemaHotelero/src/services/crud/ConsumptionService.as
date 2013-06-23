package services.crud
{
	import model.ReservationForm;
	import model.Room;
	
	import services.RemoteObjectWrapperService;

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
	
	public function retrieveConsumptionsByReservationForm(reservationForm:ReservationForm, resultHandler:Function,faultHandler:Function=null):void{
		remoteObject.retrieveConsumptions.addEventListener("result", resultHandler);
		if(faultHandler!=null){
			remoteObject.retrieveConsumptions.addEventListener("fault", faultHandler);
		}
		remoteObject.retrieveConsumptions(reservationForm);
	}
               
}

}
