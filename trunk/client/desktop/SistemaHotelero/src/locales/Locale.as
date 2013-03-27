package locales
{
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	
	import utils.config.Config;

	public class Locale
	{
		static private var instance:Locale;
		private var localeName:String;
		[Bindable]
		private var currentLocaleXML:XML;
		[Bindable]
		public var dayNames:Array;
		[Bindable]
		public var dayNamesAbbr:Array;
		[Bindable]
		public var monthNames:Array;
		
		public function Locale(){
			setCurrentLocale(Config.getInstance().getDefaultLocale());
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
			localeName = locale;
			//loads corresponding xml file
			var file:File = File.applicationDirectory.resolvePath("locales/"+locale+".xml");
			var fileStream:FileStream = new FileStream();
			fileStream.open(file, FileMode.READ);
			currentLocaleXML = XML(fileStream.readUTFBytes(fileStream.bytesAvailable));
			fileStream.close();
			setDayNames();
			setMonthNames();
		}
		
		private function setDayNames():void{
			//complete names of days
			dayNames =[currentLocaleXML.day.sunday.name, currentLocaleXML.day.monday.name, currentLocaleXML.day.tuesday.name, 
				currentLocaleXML.day.wednesday.name, currentLocaleXML.day.thursday.name, currentLocaleXML.day.friday.name, 
				currentLocaleXML.day.saturday.name];
			//abbreviations
			dayNamesAbbr =[currentLocaleXML.day.sunday.abbr, currentLocaleXML.day.monday.abbr, currentLocaleXML.day.tuesday.abbr, 
				currentLocaleXML.day.wednesday.abbr, currentLocaleXML.day.thursday.abbr, currentLocaleXML.day.friday.abbr, 
				currentLocaleXML.day.saturday.abbr];
		}
		
		private function setMonthNames():void{
			monthNames =[currentLocaleXML.month.january, currentLocaleXML.month.february, currentLocaleXML.month.march,
				currentLocaleXML.month.april,currentLocaleXML.month.may,currentLocaleXML.month.june,
				currentLocaleXML.month.july,currentLocaleXML.month.august,currentLocaleXML.month.september,
				currentLocaleXML.month.october, currentLocaleXML.month.november,currentLocaleXML.month.december];
		}
		
		public function getLocaleName():String{
			return localeName;
		}
		
				
		
	}
}