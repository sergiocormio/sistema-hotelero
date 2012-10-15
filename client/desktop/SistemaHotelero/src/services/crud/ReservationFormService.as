package services.crud
{
	import model.Alternative;
	import model.ReservationForm;

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
		
	}
}