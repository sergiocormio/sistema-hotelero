package services.crud
{
	import model.Alternative;
	import model.Customer;
	import model.ReservationForm;
	import model.Room;
	
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
		
		public function retrieveActiveReservationFormsByDateAndRoom(date:Date, room:Room,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveActiveReservationFormsByDateAndRoom.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveActiveReservationFormsByDateAndRoom.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveActiveReservationFormsByDateAndRoom(date, room);
		}
		
		public function retrieveActiveReservationFormsByCustomer(customer:Customer, resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveActiveReservationFormsByCustomer.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveActiveReservationFormsByCustomer.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveActiveReservationFormsByCustomer(customer);
		}
		
		public function retrieveActiveReservationForms(resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveActiveReservationForms.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveActiveReservationForms.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveActiveReservationForms();
		}
	}
}