package utils
{
	import flash.globalization.DateTimeStyle;
	
	import flashx.textLayout.elements.SpanElement;
	
	import locales.Locale;
	
	import mx.collections.ArrayCollection;
	
	import spark.components.gridClasses.GridColumn;
	import spark.formatters.DateTimeFormatter;
	

	public class HtmlUtils
	{
		private static var textPlaceHolder:String = "{text}";		
		private static var p:String = "<P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\">" + textPlaceHolder + "</FONT></P>";
		private static var boldP:String = "<P ALIGN=\"left\"><FONT FACE=\"Arial\" SIZE=\"12\" COLOR=\"#000000\" LETTERSPACING=\"0\" KERNING=\"1\"><B>" + textPlaceHolder + "</B></FONT></P>";
		
		public function HtmlUtils()
		{
		}
		
		public static function getParagraph(text:String):String{
			
			return p.replace(textPlaceHolder, text);
		}
		
		public static function getBoldParagraph(text:String):String{
			
			return boldP.replace(textPlaceHolder, text);
		}
		
		public static function getLineBreak():String{
			
			return "<P/>";
		}
		
		
		public static function getTable(headers:ArrayCollection, data:ArrayCollection):String{
			
			var table:String = "<TABLE STYLE=\"BORDER:1PX SOLID BLACK;BORDER-COLLAPSE:COLLAPSE;\"><TR>";
			//headers
			for each(var header:String in headers){
				table += "<TH STYLE=\"BORDER:1PX SOLID BLACK;\" BGCOLOR=\"#C0C0C0\">" + header + "</TH>";
			}
			table += "</TR>";
			// data
			for each(var row:Array in data){
				table += "<TR>";
				for each(var column:String in headers){
					table += "<TD STYLE=\"BORDER:1PX SOLID BLACK;\">" + row[column] + "</TD>";
				}
				table += "</TR>";
			}
			table += "</TABLE>";
			
			return table;
		}
		
		public static function getTableWithDivs(headers:ArrayCollection, data:ArrayCollection):String{
			
			var table:String = "<DIV WIDTH=\"50%\" STYLE=\"CLEAR:BOTH;BORDER:1PX SOLID;\">";
			//headers
			for each(var header:String in headers){
				table += "<DIV WIDTH=\"25%\" STYLE=\"FLOAT:LEFT;MARGIN:0 5PX 0 0;BORDER:1PX SOLID;\">";
				table += header;
				table += "</DIV>";
				
				// data
				/*for each(var row:Array in data){
					table += "<DIV STYLE=\"display: table-row;\">";
					for each(var column:String in headers){
						table += "<TD STYLE=\"BORDER:1PX SOLID BLACK;\">" + row[column] + "</TD>";
					}
					table += "</TR>";
				}*/
			}
			table += "<DIV STYLE=\"CLEAR:BOTH\"></DIV>"; 
			table += "</DIV>";
			
			return table;
		}
		
		public static function getList(headers:ArrayCollection, data:ArrayCollection):String{
			var list:String = "<UL>";
			// data
			for each(var row:Array in data){
				
				list += "<LI>";
				for each(var column:String in headers){
					
					list += getTextWithTabs(row[column]);
				}
				list += "</LI>"
				
			}
			list += "</UL>";
			
			return list;
		}
		
		public static function getTextWithTabs(text:String):String{
			
			var tabsQty:int = 1;
			if(text.length < 18){
				tabsQty++;
			}
			if(text.length < 8){
				tabsQty++;
			}
			
			var textWithTabs:String = text;
			for(var i:int = 0; i < tabsQty; i++){
				
				textWithTabs += "\t";
			}
						
			return textWithTabs;
		}
			
	}
}