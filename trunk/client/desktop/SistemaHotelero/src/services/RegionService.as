package services
{
	import model.Country;
	
	import mx.collections.ArrayCollection;

	public class RegionService extends RemoteObjectWrapperService
	{
		public function RegionService()
		{
			super("regionService");
		}
		
		public function retrieveRegions(country:Country,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveRegions.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveRegions.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveRegions(country);
		}
	}
}