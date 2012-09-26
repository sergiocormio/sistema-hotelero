package utils
{
	import flash.globalization.DateTimeStyle;
	
	import locales.Locale;
	
	import spark.formatters.DateTimeFormatter;
	

	public class DateTimeUtils
	{
		private static var locale:String = Locale.getInstance().getLocaleName();
		private static var df:DateTimeFormatter = new DateTimeFormatter();
		
		public function DateTimeUtils()
		{
		}
		
		public static function setLocale(newLocale:String):void{
			locale = newLocale;
		}
		
		public static function formatDate(date:Date,showTime:Boolean=false,dateStyle:String=DateTimeStyle.SHORT):String{
			if(!showTime){
				df.timeStyle = DateTimeStyle.NONE;
			}else{
				df.timeStyle = DateTimeStyle.SHORT;
			}
			df.dateStyle = dateStyle;
			df.setStyle("locale",locale);
			return df.format(date);
		}
		
		public static function formatDateWithPattern(date:Date,pattern:String):String{
			df.timeStyle = DateTimeStyle.NONE;
			df.dateStyle = DateTimeStyle.SHORT;
			df.dateTimePattern = pattern;
			return df.format(date);
		}
		
		public static function formatTime(date:Date):String{
			df.setStyle("locale",locale);
			df.timeStyle = DateTimeStyle.SHORT;
			df.dateStyle = DateTimeStyle.NONE;
			return df.format(date);
		}
		
		public static function rollDaysToDate(date:Date,days:int):Date{
			var mili:int = 1000;
			var secs:int = 60;
			var mins:int = 60;
			var hours:int = 24;
			var day:int = hours * mins * secs * mili;
			var result:Date = new Date();
			result.setTime(date.getTime() + (day * days));
			return result;
		}
	}
}