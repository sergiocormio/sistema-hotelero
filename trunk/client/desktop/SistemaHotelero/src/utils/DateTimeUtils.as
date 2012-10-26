package utils
{
	import flash.globalization.DateTimeStyle;
	
	import locales.Locale;
	
	import spark.components.gridClasses.GridColumn;
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
		
		//Returns a column for a Date
		public static function getColumnForDate(date:Date,dataField:String="date"):GridColumn{
			var headerText:String = DateTimeUtils.formatDateWithPattern(date,Locale.getInstance().getCurrentLocale().dateFormat);
			var col:GridColumn = new GridColumn();
			col.headerText = headerText;
			col.dataField = dataField;
			return col;
		}
		
		//returns the amount of days between dateFrom and dateTo
		public static function getQuantityOfDays(dateFrom:Date,dateTo:Date):Number{
			var dateDiff:Number;
			dateDiff = removeHoursToDate(dateTo).time-removeHoursToDate(dateFrom).time;
			return Math.round((dateDiff/86400000));
		}
		
		//For example removes "12:07:32" from "12/12/2012 12:07:32"
		public static function removeHoursToDate(dateToModify:Date):Date{
			var result:Date = new Date(dateToModify.fullYear,dateToModify.month,dateToModify.date);
			return result;
			
		}
		
	}
}