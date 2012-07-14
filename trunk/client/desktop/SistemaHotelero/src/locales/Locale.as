package locales
{
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	import flash.net.URLLoader;
	import flash.net.URLRequest;

	public class Locale
	{
		static private var instance:Locale;
		[Bindable]
		private var currentLocaleXML:XML;
		
		public function Locale(){
			setCurrentLocale("en_US");
		}
		
		static public function getInstance():Locale{
			if(instance==null){
				instance = new Locale();
			}
			return instance;
		}
		
		public function getCurrentLocale():Object{
			return currentLocaleXML;
		}
		
		public function setCurrentLocale(locale:String):void{
			//load corresponding xml file
			var file:File = File.applicationDirectory.resolvePath("locales/"+locale+".xml");
			var fileStream:FileStream = new FileStream();
			fileStream.open(file, FileMode.READ);
			currentLocaleXML = XML(fileStream.readUTFBytes(fileStream.bytesAvailable));
			fileStream.close();
			trace(currentLocaleXML.customer.singular);
		}
	}
}