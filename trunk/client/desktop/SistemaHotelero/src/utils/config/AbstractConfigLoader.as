package utils.config
{
	import flash.filesystem.File;
	import flash.filesystem.FileStream;
	import flash.filesystem.FileMode;
	import utils.log.DebugLog;

	// ABSTRACT CLASS: not supported by action script
	public class AbstractConfigLoader
	{
		protected var _configXML:XML = null;
		
		
		public function AbstractConfigLoader()
		{
		}
		
		protected function loadConfig(file:File):void
		{
			
			if (file.exists)
			{
				var fileStream:FileStream = new FileStream();
				fileStream.open(file, FileMode.READ);
				_configXML = XML(fileStream.readUTFBytes(fileStream.bytesAvailable));
				fileStream.close();
			}
			else
			{
				//Alert.show("Could not open config.xml");
				DebugLog.log("DANGER: Could not open config.xml file, using default config values.");
			}
		}
		
		
		protected function writeConfig(file:File):void
		{
			var fileStream:FileStream = new FileStream();
			fileStream.open(file, FileMode.WRITE);
			fileStream.writeUTFBytes(_configXML.toString());
			fileStream.close();
			
		}
	}
}