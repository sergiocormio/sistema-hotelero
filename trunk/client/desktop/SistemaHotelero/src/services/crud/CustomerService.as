package services.crud
{
	import services.RemoteObjectWrapperService;
	import model.Country;

public class CustomerService extends CRUDService
{
	public function CustomerService(){
		super("customerService");
	}
	
	
	public function retrieveCustomers(countries:Country, resultHandler:Function, faultHandler:Function=null):void{
		remoteObject.retrieveCustomers.addEventListener("result", resultHandler);
		if(faultHandler!=null){
			remoteObject.retrieveCustomers.addEventListener("fault", faultHandler);
		}
		remoteObject.retrieveCustomers(countries);
	}
               
}

}
