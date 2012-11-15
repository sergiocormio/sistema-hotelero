package utils.log
{
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	import flash.globalization.DateTimeStyle;
	
	import mx.controls.Alert;
	import mx.states.State;
	
	import utils.DateTimeUtils;
	import utils.Utils;
	

	public class DebugLog
	{
		private static var file:File;
		private static var wasInitialized:Boolean = false;
		private static var fileStream:FileStream = new FileStream();
		public static var enable:Boolean = true;
		
		public function DebugLog()
		{
		}
		
		private static function initializeFile():void{
			var date:Date = new Date();
			var month:int = date.month+1;
			var dateString:String = date.fullYear+'_'+month+'_'+date.date;
			file = File.applicationStorageDirectory.resolvePath("debugLog"+dateString +".txt");
			if(file.exists){
				fileStream.open(file, FileMode.APPEND);
			}else{
				fileStream.open(file, FileMode.WRITE);
			}
			wasInitialized = true;
		}
		
		public static function log(msg:String):void{
			//if not enabled -> don't logging
			if (!enable || msg == null){
				return;
			}
			if(!wasInitialized){
				initializeFile();
			}
			//puts the current datetime first
			var date:Date = new Date();
			var dateString:String = DateTimeUtils.formatTime(date);
			//converts all "enters" in "spaces"
			msg = Utils.stringReplaceAll(msg,'\r\n',' ');
			//write msg in the debugFile
			//fileStream.writeUTFBytes(dateString + ': '+ msg+'\r\n');
			fileStream.writeMultiByte(dateString + ': '+ msg+'\r\n','us-ascii');
			//every time after a msg is written -> close the LogFile in order to be able open it whenever.
			closeLogFile();
		}
		
		public static function closeLogFile():void{
			fileStream.close();
			wasInitialized=false;
		}

		public static function openDebugLogFile():void{
			if(!wasInitialized){
				initializeFile();
				closeLogFile();
			}
			file.openWithDefaultApplication();
		}
		
		public static function getDebugLogPath():String{
			if(!wasInitialized){
				initializeFile();
				closeLogFile();
			}
			return file.nativePath;
		}
		
	}
}