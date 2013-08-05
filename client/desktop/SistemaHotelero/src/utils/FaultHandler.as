package utils
{
	import locales.Locale;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	
	import utils.log.DebugLog;
	
	import view.components.Mask;

	public class FaultHandler
	{
		public function FaultHandler()
		{
		}
		
		// Handle a message fault.
		public static function defaultFaultHandler(event:FaultEvent=null,token:Object=null):void {
			//closes possible progress bar
			Mask.close();
			if(event == null){
				return;
			}
			
			if (event.fault != null && ((event.fault.faultDetail != null &&
				 	(event.fault.faultDetail.indexOf("Detected duplicate HTTP-based FlexSessions") != -1 ||
				  	 event.fault.faultDetail.indexOf("The FlexClient is invalid") != -1))
				|| event.fault.faultCode == "Server.Processing.DuplicateSessionDetected"))
			{
				return;
			}

			var loc:Object = Locale.getInstance().getCurrentLocale();
			var errMsg:String;
			var faultString:String;
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
			
			try
			{
				faultString =  event.fault.faultString;
			} 
			catch(error:Error) 
			{
				faultString = "";
			}
			
			if(errMsg == null || errMsg == ""){
				errMsg = loc.errorMessage.genericServerError;
			}
			
			DebugLog.log(errMsg + " " + faultString +"\n" + event.fault.getStackTrace());
			Alert.show(errMsg, loc.validator.error);
		}
	}
}