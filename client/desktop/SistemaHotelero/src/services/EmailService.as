package services
{
	import model.EmailRequest;
	
	public class EmailService extends RemoteObjectWrapperService
	{
		public function EmailService()
		{
			super("emailService");
		}
		
		public function sendEmail(emailRequest:EmailRequest, resultHandler:Function, faultHandler:Function=null):void{
			remoteObject.sendEmail.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.sendEmail.addEventListener("fault", faultHandler);
			}
			remoteObject.sendEmail(emailRequest);
		}
		

	}
}