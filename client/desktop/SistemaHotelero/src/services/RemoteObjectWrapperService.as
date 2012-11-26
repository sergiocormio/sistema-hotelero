package services
{
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.AMFChannel;
	import mx.rpc.remoting.RemoteObject;
	
	import utils.config.Config;

	public class RemoteObjectWrapperService
	{
		protected var remoteObject:RemoteObject;
		
		public function RemoteObjectWrapperService(destination:String)
		{
			remoteObject = new RemoteObject(destination);
			var myChannel:AMFChannel = new AMFChannel("my-amf",Config.getServerURL());
			
			// set request timeout to 1 hour
			myChannel.netConnection.httpIdleTimeout = new Number(1000 * 60 * 60);
			
			var cs:ChannelSet = new ChannelSet();
			cs.addChannel(myChannel);
			remoteObject.channelSet = cs;
		}
		
	}
}