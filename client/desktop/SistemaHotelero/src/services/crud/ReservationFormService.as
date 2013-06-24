package services.crud
{
	import model.Alternative;
	import model.Customer;
	import model.ReservationForm;
	import model.Room;
	
	import mx.collections.ArrayCollection;
	
	import view.forms.crud.individual.ReservationFormIndividualCRUDForm;

	public class ReservationFormService extends CRUDService
	{
		public function ReservationFormService()
		{
			super("reservationFormService");
		}
		
		public function book(chosenAlternative:Alternative, reservationForm:ReservationForm,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.book.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.book.addEventListener("fault", faultHandler);
			}
			remoteObject.book(chosenAlternative,reservationForm);
		}
		
		public function exportData(reservationForm:ReservationForm, selectedLocale:String, resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.exportData.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.exportData.addEventListener("fault", faultHandler);
			}
			remoteObject.exportData(reservationForm, selectedLocale);
		}
		
		public function retrieveReservationForms(dateFrom:Date, dateTo:Date, customer:Customer, state:String ,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveReservationForms.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveReservationForms.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveReservationForms(dateFrom, dateTo, customer, state);
		}
		
		
		
		public function retrieveReservationFormsByDateAndRoom(date:Date, room:Room, states:ArrayCollection, resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveReservationFormsByDateAndRoom.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveReservationFormsByDateAndRoom.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveReservationFormsByDateAndRoom(date, room, states);
		}
		
		public function retrieveReservationFormsByCustomer(customer:Customer, states:ArrayCollection, resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveReservationFormsByCustomer.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveReservationFormsByCustomer.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveReservationFormsByCustomer(customer, states);
		}
		
		
		
		public function retrieveReservationFormsByStates(states:ArrayCollection, resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveReservationForms.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveReservationForms.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveReservationForms(states);
		}
	}
}