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
		
		public function retrieveAll(resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.retrieveAll.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.retrieveAll.addEventListener("fault", faultHandler);
			}
			remoteObject.retrieveAll();
		}
		
		public function createRecord(obj:Object,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.createRecord.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.createRecord.addEventListener("fault", faultHandler);
			}
			remoteObject.createRecord(obj);
		}
		
		public function updateRecord(obj:Object,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.updateRecord.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.updateRecord.addEventListener("fault", faultHandler);
			}
			remoteObject.updateRecord(obj);
		}
		
		public function deleteRecord(obj:Object,resultHandler:Function,faultHandler:Function=null):void{
			remoteObject.deleteRecord.addEventListener("result", resultHandler);
			if(faultHandler!=null){
				remoteObject.deleteRecord.addEventListener("fault", faultHandler);
			}
			remoteObject.deleteRecord(obj);
		}
	}
}