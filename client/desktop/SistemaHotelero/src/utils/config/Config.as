package utils.config
{
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	
	import mx.controls.Alert;
	
	import utils.Utils;
	import utils.log.DebugLog;

	public class Config
	{
		private static var _configLoaded:Boolean = false;
		private static var _configXML:XML = null;
		
		public function Config()
		{
		}
		
		private static function readConfig():void
		{
			var file:File = File.applicationDirectory.resolvePath("config.xml");
			if (file.exists)
			{
				var fileStream:FileStream = new FileStream();
				fileStream.open(file, FileMode.READ);
				_configXML = XML(fileStream.readUTFBytes(fileStream.bytesAvailable));
				fileStream.close();
				_configLoaded = true;
			}
			else
			{
				//Alert.show("Could not open config.xml");
				DebugLog.log("DANGER: Could not open config.xml file, using default config values.");
			}
		}
		
		private static function loadConfig():void{
			if(_configLoaded==false){
				readConfig();
			}
		}
		
		public static function getJavaHomePath():String{
			loadConfig();
			if(_configXML!=null){
				return _configXML.javaHomePath;
			}else{
			//"C:\\Program Files (x86)\\Java\\jdk1.6.0_21";
				return "C:\\Program Files\\Java\\jre7";
			}
		}
		
		public static function getTomcatHomePath():String{
			loadConfig();
			if(_configXML!=null){
				return _configXML.tomcatHomePath;
			}else{
				return "C:\\apache-tomcat-7.0.32";
			}
		}
		
		public static function isEnabledLocalServer():Boolean{ 
			loadConfig();
			if(_configXML!=null){
				return Utils.stringToBoolean(_configXML.enableLocalServer);
			}else{
				return true;
			}
		}
		
		public static function getServerURL():String{ 
			loadConfig();
			if(_configXML!=null){
				return _configXML.serverURL;
			}else{
				return "http://localhost:8400/SistemaHotelero/messagebroker/amf";
			}
		}
		
	}
}