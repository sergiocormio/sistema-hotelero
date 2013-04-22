package services.crud
{
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.AMFChannel;
	import mx.rpc.remoting.RemoteObject;
	import utils.config.Config;
	
	public class ActivationService extends CRUDService
	{
		
		protected var remoteActivationObject:RemoteObject;
		
		public function ActivationService()
		{
			super("activationService");
			initRemoteActivationObject("activationService");
		}
		
		
		private function initRemoteActivationObject(destination:String):void
		{
			remoteActivationObject = new RemoteObject(destination);
			var myChannel:AMFChannel = new AMFChannel("my-amf-activation", Config.getInstance().getActivationServerURL());
			
			// set request timeout to 1 hour
			myChannel.netConnection.httpIdleTimeout = new Number(1000 * 60 * 60);
			
			var cs:ChannelSet = new ChannelSet();
			cs.addChannel(myChannel);
			remoteActivationObject.channelSet = cs;
		}
		
		
		public function activateApplication(resultHandler:Function,faultHandler:Function=null):void{
			remoteActivationObject.activateApplication.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteActivationObject.activateApplication.addEventListener("fault", faultHandler);
			}
			remoteActivationObject.activateApplication();
		}
	}
}