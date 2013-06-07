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
	import flash.utils.Dictionary;
	
	import locales.Locale;
	
	import mx.collections.ArrayCollection;
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
		
		private var eventToFunctionsMap:Dictionary = new Dictionary();
		
		public static const SERVER_STARTED:String = "ServerStarted";
		public static const SERVER_STOPPED:String = "ServerStopped";
		
		
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
			
			
			
			tomcatHomePath = getTomcatHomePath();
			tomcatHomeDir = new File(tomcatHomePath);
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
			
			javaHomePath = getJavaHomePath();
			
			var javaDir:File = new File(javaHomePath);
			if(!javaDir.exists){
				_initialized = false;
				DebugLog.log(Locale.getInstance().getCurrentLocale().errorMessage.javaHomeNotFound);
				Alert.show(Locale.getInstance().getCurrentLocale().errorMessage.javaHomeNotFound + File.lineEnding + javaHomePath);
			}
			return _initialized;
		}
		
		/**
		 * Tries to return a valid tomcat path
		 * Searching in all users drive letters
		 */ 
		private function getTomcatHomePath():String{ 
			var systemConfig:Config = Config.getInstance();
			var tomcatHomePathAux:String = systemConfig.getTomcatHomePath();
			var originalTomcatHomePathAux:String = tomcatHomePathAux; 
			var tomcatDirAux:File = new File(tomcatHomePathAux);
			if (!tomcatDirAux.exists){
				//extracts the drive letter
				var currentDrives:Array = File.getRootDirectories() ;
				for each(var drive:File in currentDrives){
					tomcatHomePathAux = drive.name + tomcatHomePathAux.substring(2);
					tomcatDirAux = new File(tomcatHomePathAux);
					if (tomcatDirAux.exists){
						systemConfig.setTomcatHomePath(tomcatHomePathAux);
						systemConfig.update();
						return tomcatHomePathAux;
					}
				}
			}
			return originalTomcatHomePathAux;
		}
		
		/**
		 * Tries to return a valid java path
		 * Searching in all users drive letters
		 */ 
		private function getJavaHomePath():String{ 
			var systemConfig:Config = Config.getInstance();
			var javaHomePathAux:String = systemConfig.getJavaHomePath();
			var originalJavaHomePathAux:String = javaHomePathAux; 
			var javaDirAux:File = new File(javaHomePathAux);
			if (!javaDirAux.exists){
				//extracts the drive letter
				var currentDrives:Array = File.getRootDirectories() ;
				for each(var drive:File in currentDrives){
					javaHomePathAux = drive.name + javaHomePathAux.substring(2);
					javaDirAux = new File(javaHomePathAux);
					if (javaDirAux.exists){
						systemConfig.setJavaHomePath(javaHomePathAux);
						systemConfig.update();
						return javaHomePathAux;
					}
				}
			}
			return originalJavaHomePathAux;
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
			execute(stopTomcatProcess, "stop");
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
				DebugLog.log("Trying to run: " + file.nativePath + File.lineEnding + " " + processArgs[0]  + " " + processArgs[1] + " " + processArgs[2] + " " + processArgs[3] + " " + processArgs[4]);
				process.start(nativeProcessStartupInfo);
				process.addEventListener(ProgressEvent.STANDARD_OUTPUT_DATA,
					outputDataHandler);
				process.addEventListener(ProgressEvent.STANDARD_ERROR_DATA,
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
			verifyServerOutputAndDispatchEvents(data);
		}
		
		private function errorOutputDataHandler(event:ProgressEvent):void
		{
			var process:NativeProcess = event.target as NativeProcess;
			var data:String = process.standardError.readUTFBytes(startTomcatProcess.standardError.bytesAvailable);
			log(data);
			verifyServerOutputAndDispatchEvents(data);
		}
		
		private function verifyServerOutputAndDispatchEvents(data:String):void{
			if(data.indexOf("INFO: Server startup in")>-1){
				dispatchEvent(new Event(SERVER_STARTED));
			}else if(data.indexOf("INFO: Server stopped")>-1){
				dispatchEvent(new Event(SERVER_STOPPED));
			}
		}
		
		private function log(msg:String):void{
			DebugLog.log("Tomcat says: " + msg);
		}
		
		//EVENT Dispatcher Implementation
		public function addEventListener(type:String, listener:Function):void{
			if(eventToFunctionsMap[type]==null){
				eventToFunctionsMap[type] = new ArrayCollection();
			}
			eventToFunctionsMap[type].addItem(listener);
		}
		
		public function removeEventListener(type:String, listener:Function):void{
			if(eventToFunctionsMap[type]!=null){
				var ac:ArrayCollection = eventToFunctionsMap[type];
				var index:int = -1;
				for (var i:int=0; i<ac.length ; i++){
					var f:Function = ac.getItemAt(i) as Function;
					if(f==listener){
						index=i;
						break;
					}
				}
				if(index!=-1){
					ac.removeItemAt(index);
				}
			}
		}
		
		public function dispatchEvent(event:Event):void{
			if(eventToFunctionsMap[event.type]!=null){
				var ac:ArrayCollection = eventToFunctionsMap[event.type];
				for (var i:int=0; i<ac.length ; i++){
					var f:Function = ac.getItemAt(i) as Function;
					f(event);
				}
			}
		}
		//END of EVENT Dispatcher Implementation
	}
}