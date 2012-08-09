package services
{
	import flash.sampler.NewObjectSample;
	
	import model.DocumentType;
	
	import mx.messaging.ChannelSet;
	import mx.messaging.channels.AMFChannel;
	import mx.rpc.remoting.RemoteObject;

public class DocumentTypeService extends RemoteObjectWrapperService
{
	
	public function DocumentTypeService():void{
		super("documentTypeService");
    }
	
}

}
