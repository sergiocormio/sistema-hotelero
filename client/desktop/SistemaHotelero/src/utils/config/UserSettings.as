package utils.config
{
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	
	import mx.controls.Alert;
	
	import utils.Utils;
	import utils.log.DebugLog;

	public class UserSettings extends AbstractConfigLoader
	{
		private static var FILENAME:String = "user_settings.xml";
		
		private static var _instance:UserSettings;
		
		public static function getInstance():UserSettings{
			if(_instance == null){
				_instance = new UserSettings();
			}
			return _instance;
		}
		
		// DO NOT use this from outside this class
		// (Runtime check in lack of private constructor)
		public function UserSettings()
		{
			if(_instance != null)
			{
				throw new Error("You must use the 'getInstance' method because it's a singleton!");
			}
			else
			{
				// it is not allowed to write a file in applicationDirectory
				var file:File = File.applicationStorageDirectory.resolvePath(FILENAME);
				if(!file.exists){
					//load default user settings
					file = File.applicationDirectory.resolvePath(FILENAME);
					loadConfig(file);
				}
				else{
					//load default user settings
					loadConfig(file);
				}
			}
		}
		
				
		public function getReservationFormExpDays():String{
			if(_configXML!=null){
				return _configXML.reservationForm.expirationDays;
			}else{
				throw new Error(FILENAME + " is not loaded");
			}
		}
		
		public function setReservationFormExpDays(value:String):void{
			if(_configXML!=null){
				_configXML.reservationForm.expirationDays = value;
			}else{
				throw new Error(FILENAME + " is not loaded");
			}
		}
		
		public function getEmailSender():String{
			if(_configXML!=null){
				return _configXML.mail.emailSender;
			}else{
				throw new Error(FILENAME + " is not loaded");
			}
		}
		
		public function setEmailSender(value:String):void{
			if(_configXML!=null){
				_configXML.mail.emailSender = value;
			}else{
				throw new Error(FILENAME + " is not loaded");
			}
		}
		
		public function getPassword():String{
			if(_configXML!=null){
				return _configXML.mail.password;
			}else{
				throw new Error(FILENAME + " is not loaded");
			}
		}
		
		public function setpassword(value:String):void{
			if(_configXML!=null){
				_configXML.mail.password = value;
			}else{
				throw new Error(FILENAME + " is not loaded");
			}
		}
		
		public function getEmailTest():String{
			if(_configXML!=null){
				return _configXML.mail.emailTest;
			}else{
				throw new Error(FILENAME + " is not loaded");
			}
		}
		
		
		public function setEmailTest(value:String):void{
			if(_configXML!=null){
				_configXML.mail.emailTest = value;
			}else{
				throw new Error(FILENAME + " is not loaded");
			}
		}
		
		public function update():void {
			var file:File = File.applicationStorageDirectory.resolvePath(FILENAME);
			this.writeConfig(file);
		}
	}
}