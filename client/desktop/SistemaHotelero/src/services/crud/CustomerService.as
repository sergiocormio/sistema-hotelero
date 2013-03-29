package services.crud
{
	import model.Country;
	
	import mx.collections.ArrayCollection;
	
	import services.RemoteObjectWrapperService;

public class CustomerService extends CRUDService
{
	public function CustomerService(){
		super("customerService");
	}
	
	
	public function retrieveCustomers(regions:ArrayCollection, months:ArrayCollection, resultHandler:Function, faultHandler:Function=null):void{
		remoteObject.retrieveCustomers.addEventListener("result", resultHandler);
		if(faultHandler!=null){
			remoteObject.retrieveCustomers.addEventListener("fault", faultHandler);
		}
		remoteObject.retrieveCustomers(regions, months);
	}
               
}

}
