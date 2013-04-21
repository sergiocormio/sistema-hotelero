package utils.config
{
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	
	import mx.controls.Alert;
	
	import utils.Utils;
	import utils.log.DebugLog;

	public class Config extends AbstractConfigLoader
	{
		private static var FILENAME:String = "config.xml";
		
		private static var _instance:Config;
		
		public static function getInstance():Config{
			if(_instance == null){
				_instance = new Config();
			}
			return _instance;
		}
		
		// DO NOT use this from outside this class
		// (Runtime check in lack of private constructor)
		public function Config()
		{
			if(_instance != null)
			{
				throw new Error("You must use the 'getInstance' method because it's a singleton!");
			}
			else
			{
				var file:File = File.applicationDirectory.resolvePath(FILENAME);
				loadConfig(file);
			}
		}
		
		public function getJavaHomePath():String{
			if(_configXML!=null){
				return _configXML.javaHomePath;
			}else{
			//"C:\\Program Files (x86)\\Java\\jdk1.6.0_21";
				return "C:\\Program Files\\Java\\jre7";
			}
		}
		
		public function getTomcatHomePath():String{
			if(_configXML!=null){
				return _configXML.tomcatHomePath;
			}else{
				return "C:\\apache-tomcat-7.0.32";
			}
		}
		
		public function isEnabledLocalServer():Boolean{ 
			if(_configXML!=null){
				return Utils.stringToBoolean(_configXML.enableLocalServer);
			}else{
				return true;
			}
		}
		
		public function getServerURL():String{ 
			if(_configXML!=null){
				return _configXML.serverURL;
			}else{
				return "http://localhost:8400/SistemaHotelero/messagebroker/amf";
			}
		}
		
		public function getActivationServerURL():String{ 
			if(_configXML!=null){
				return _configXML.activationServerURL;
			}else{
				return "http://fededz.dyndns.org:8400/ActivationServer/messagebroker/amf";
			}
		}
		
		public function getDefaultLocale():String{
			if(_configXML!=null){
				return _configXML.defaultLocale;
			}else{
				return "es_AR";
			}
		}
		
		public function getHotelWebSite():String{
			if(_configXML!=null){
				return _configXML.hotelWebSite;
			}else{
				return "www.dellosky.com";
			}
		}
		
		
	}
}