package services.crud
{
	import services.RemoteObjectWrapperService;

	public class CountryService extends CRUDService
	{
		public function CountryService()
		{
			super("countryService");
		}
		
		
		
		public function retrieveCountriesWithoutRegions(resultHandler:Function, faultHandler:Function=null):void{
			remoteObject.retrieveCountriesWithoutRegions.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveCountriesWithoutRegions.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveCountriesWithoutRegions();
		}
		
	}
}