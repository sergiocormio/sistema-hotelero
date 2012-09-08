package services
{
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.AMFChannel;
	import mx.rpc.remoting.RemoteObject;

	public class RemoteObjectWrapperService
	{
		protected var remoteObject:RemoteObject;
		
		public function RemoteObjectWrapperService(destination:String)
		{
			remoteObject = new RemoteObject(destination);
			var myChannel:AMFChannel = new AMFChannel("my-amf", "http://localhost:8400/SistemaHotelero/messagebroker/amf");
			var cs:ChannelSet = new ChannelSet();
			cs.addChannel(myChannel);
			remoteObject.channelSet = cs;
		}
		
	}
}