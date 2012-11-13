package services
{
	public class MigrationService extends RemoteObjectWrapperService
	{
		public function MigrationService()
		{
			super("migrationService");
		}
		
		public function backupDatabase(destZipFile:String, resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.backupDatabase.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.backupDatabase.addEventListener("fault", faultHandler);
			}
			remoteObject.backupDatabase(destZipFile);
		}
		
		
		public function restoreDatabase(sourceZipFile:String, resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.restoreDatabase.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.restoreDatabase.addEventListener("fault", faultHandler);
			}
			remoteObject.restoreDatabase(sourceZipFile);
		}
		
		
	}
}