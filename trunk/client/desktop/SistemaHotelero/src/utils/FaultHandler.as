package utils
{
	import locales.Locale;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	
	import utils.log.DebugLog;

	public class FaultHandler
	{
		public function FaultHandler()
		{
		}
		
		// Handle a message fault.
		public static function defaultFaultHandler(event:FaultEvent=null,token:Object=null):void {
			if(event == null){
				return;
			}
			var loc:Object = Locale.getInstance().getCurrentLocale();
			var errMsg:String;
			try
			{
				var errCode:String = event.fault.rootCause.errorCode; 
				errMsg = loc.validator.server.child(errCode).text();
			} 
			catch(error:Error) 
			{   try
				{
					errMsg = event.fault.rootCause.faultDetail;
				} 
				catch(error:Error) 
				{
					errMsg = event.fault.faultString;
				}
			}
			DebugLog.log(errMsg);
			Alert.show(errMsg , loc.validator.error );
		}
	}
}