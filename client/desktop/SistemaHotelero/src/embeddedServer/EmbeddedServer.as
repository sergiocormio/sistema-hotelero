package embeddedServer
{
	import flash.desktop.NativeProcess;
	import flash.desktop.NativeProcessStartupInfo;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.ProgressEvent;
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	import flash.system.Capabilities;
	
	import locales.Locale;
	
	import mx.controls.Alert;
	
	import utils.config.Config;
	import utils.log.DebugLog;

	public class EmbeddedServer
	{
		private var startTomcatProcess:NativeProcess;
		private var stopTomcatProcess:NativeProcess;
		
		private var tomcatHomeDir:File;
		private var tomcatHomePath:String;
		private var javaHomePath:String;
		
		private var _initialized:Boolean = false;
		private var _started:Boolean = false;
		
		
		public function EmbeddedServer()
		{
		}
		
		public function initialize():Boolean
		{
			_initialized = true;
			if (!NativeProcess.isSupported)
			{
				_initialized = false;
				Alert.show("NativeProcess not supported");
			}
			//TODO: Read all Path from a configuration FILE
			tomcatHomePath = Config.getTomcatHomePath();
			tomcatHomeDir = new File(tomcatHomePath);
//			tomcatHomeDir = File.applicationStorageDirectory.resolvePath("tomcat");
			if (!tomcatHomeDir.exists)
			{
				/*	var tomcatOriginalDir:File = File.applicationDirectory.resolvePath("embeddedServer\\tomcat");
				trace("Copying tomcat to appStorageDirectory...");
				tomcatOriginalDir.addEventListener(Event.COMPLETE, fileMoveCompleteHandler); 
				tomcatOriginalDir.addEventListener(IOErrorEvent.IO_ERROR, fileMoveIOErrorEventHandler); 
				tomcatOriginalDir.copyTo(tomcatHomeDir);*/
				_initialized = false;
				DebugLog.log(Locale.getInstance().getCurrentLocale().errorMessage.serverNotFound);
				Alert.show(Locale.getInstance().getCurrentLocale().errorMessage.serverNotFound + File.lineEnding + tomcatHomePath);
			}
			

			
			// If no known last home, present default/sample values
			if (Capabilities.os.toLowerCase().indexOf("win") > -1){
				// default/sample values for Windows users
				javaHomePath = Config.getJavaHomePath();
			}
			
			var javaDir:File = new File(javaHomePath);
			if(!javaDir.exists){
				_initialized = false;
				DebugLog.log(Locale.getInstance().getCurrentLocale().errorMessage.javaHomeNotFound);
				Alert.show(Locale.getInstance().getCurrentLocale().errorMessage.javaHomeNotFound + File.lineEnding + javaHomePath);
			}
			return _initialized;
		}
		
	/*	private function fileMoveCompleteHandler(event:Object):void{ 
			Alert.show(event.target); // [object File] 
			startTomcat();
		} 
		private function fileMoveIOErrorEventHandler(event:Object):void { 
			Alert.show("I/O Error.");  
		}*/
		
		public function startTomcat():void
		{
			// Write the Java home path to config.xml so that it can be presented 
			// to the user the next time the application is started
//			writeConfig();
			if(!_initialized){
				initialize();
			}
			DebugLog.log("Starting Tomcat..." + File.lineEnding);
			startTomcatProcess = new NativeProcess();
			execute(startTomcatProcess, "start");
			_started = true;
		}
		
		public function stopTomcat():void
		{
			if(!_started){ 
				return; 
			}
			DebugLog.log("Stopping Tomcat..." + File.lineEnding);
			stopTomcatProcess = new NativeProcess();
			execute(startTomcatProcess, "stop");
			_started = false;
		}
		
		private function execute(process:NativeProcess, arg:String):void
		{
			// Get a file reference to the JVM
			var file:File = new File(javaHomePath);
			if (Capabilities.os.toLowerCase().indexOf("win") > -1)
			{
				file = file.resolvePath("bin/javaw.exe");
			}
			else
			{
				file = file.resolvePath("Home/bin/java");
			}
			
			// Start the process
			try
			{
				var nativeProcessStartupInfo:NativeProcessStartupInfo = new NativeProcessStartupInfo();
				nativeProcessStartupInfo.executable = file;
				nativeProcessStartupInfo.workingDirectory = tomcatHomeDir.resolvePath("bin");
				var processArgs:Vector.<String> = new Vector.<String>();
				processArgs[0] = "-Dcatalina.home="+tomcatHomeDir.nativePath;
				processArgs[1] = "-classpath";
				processArgs[2] = tomcatHomeDir.resolvePath("bin/bootstrap.jar").nativePath +";" + tomcatHomeDir.resolvePath("bin/tomcat-juli.jar").nativePath;
				processArgs[3] = "org.apache.catalina.startup.Bootstrap";
				processArgs[4] = arg;
				nativeProcessStartupInfo.arguments = processArgs;
				startTomcatProcess = new NativeProcess();
				DebugLog.log("Trying to run: " + file.nativePath + File.lineEnding + " " + processArgs[0]  + " " + processArgs[1] + " " + processArgs[2] + " " + processArgs[3] + " " + processArgs[4]);
				startTomcatProcess.start(nativeProcessStartupInfo);
				startTomcatProcess.addEventListener(ProgressEvent.STANDARD_OUTPUT_DATA,
					outputDataHandler);
				startTomcatProcess.addEventListener(ProgressEvent.STANDARD_ERROR_DATA,
					errorOutputDataHandler);
			}
			catch (e:Error)
			{
				Alert.show(e.message, "Error");
			}
		}
		
		private function outputDataHandler(event:ProgressEvent):void
		{
			var process:NativeProcess = event.target as NativeProcess;
			var data:String = process.standardOutput.readUTFBytes(process.standardOutput.bytesAvailable);
			log(data);
		}
		
		private function errorOutputDataHandler(event:ProgressEvent):void
		{
			var process:NativeProcess = event.target as NativeProcess;
			var data:String = process.standardError.readUTFBytes(startTomcatProcess.standardError.bytesAvailable);
			log(data);
		}
		
		private function log(msg:String):void{
			DebugLog.log("Tomcat says: " + msg);
		}
		
	}
}