package services
{
	public class MasterDataService extends RemoteObjectWrapperService
	{
		public function MasterDataService()
		{
			super("masterDataService");
		}
		
		public function createMasterData(resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.createMasterData.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.createMasterData.addEventListener("fault", faultHandler);
			}
			remoteObject.createMasterData();
		}
	}
}