package utils
{
	import flash.desktop.NativeApplication;
	import flash.display.Screen;
	import flash.events.TimerEvent;
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	import flash.utils.Dictionary;
	import flash.utils.Timer;
	
	import flashx.textLayout.operations.InsertTextOperation;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.Alert;
	import mx.formatters.CurrencyFormatter;
	import mx.validators.NumberValidator;
	
	import spark.components.TextInput;
	import spark.components.WindowedApplication;
	import spark.components.gridClasses.GridColumn;
	import spark.events.TextOperationEvent;
	import spark.globalization.SortingCollator;

	/**
	 * Class to hold utility methods
	 */
	public class Utils
	{
		private static var currencyFormatter:CurrencyFormatter = new CurrencyFormatter();
		private static var numberValidator:NumberValidator = new NumberValidator();
		{
			currencyFormatter.currencySymbol = "$";
			currencyFormatter.decimalSeparatorFrom = ".";
			currencyFormatter.thousandsSeparatorFrom = ",";
		}
		
		public function Utils()
		{
		}
		
		/**
		 * Formats the value of a text input into currency
		 */
		public static function formatCurrency(ti:TextInput):void {
			//if the value is not a number, then don't do anything
			if (numberValidator.validate(ti.text, true).results != null){
				return;
			}
			
			var formatted:String = currencyFormatter.format(ti.text);
			if (formatted) {
				ti.text = formatted;
			}
		}
		
		/**
		 * Strips the given currency-formatted value from all the currency related symbols.
		 */
		public static function unformatCurrency(value:String):String{
			var currencySymbol:String ="$";
			var decimalSeparatorFrom:String =".";
			var thousandsSeparatorFrom:String=",";
			var result:String = value;
			
			if(value==null){ return null;}
			
			var i:int = result.indexOf(currencySymbol);
			if(i>-1){
				result = result.substr(i+1);
			}
			
			while(result.indexOf(thousandsSeparatorFrom)>-1){
				result = result.replace(thousandsSeparatorFrom, "");
			}
			
			return result;
		}
		
		/**
		 * Creates a new collection containing the result of appling the given function
		 * to each element in the given collection.
		 */
		public static function select(collection:ArrayCollection, func:Function) : ArrayCollection {
			var result:ArrayCollection = new ArrayCollection();
			for each (var val:Object in collection) {
				result.addItem(func(val));
			}
			return result;
		}
		
		/**
		 * Returns the fist element in the given collection that makes the given function return "true"
		 */
		public static function first(collection:ArrayCollection, func:Function):Object {
			for each (var val:Object in collection) {
				if (func(val)) {
					return val;
				}
			}
			return null;
		}

		/**
		* Sorts Alphabetically an ArrayCollection
		*/
		public static function sortAlphabetically(collection:ArrayCollection):void{
			var sort:Sort = new Sort();
			// There is only one sort field, so use a null first parameter.
			sort.fields = [new SortField(null, true)];
			collection.sort = sort;
			collection.refresh();
		} 
		
		/**
		 * Replaces all ocurrencies of "find" for "replacement" in "source" string  
		 */
		public static function stringReplaceAll( source:String, find:Object, replacement:String ) : String
		{
			if(source!=null){
				return source.split( find ).join( replacement );
			}else{
				return source;
			}
		}
		
		/**
		 *  Merges 2 arrays of string (removes duplicates)
		 */
		public static function mergeStringArrays(a1:Array,a2:Array):Array{
			var auxArray:Array = new Array();
			auxArray = a1.concat();
			for each(var val:String in a2){
				var exists:Boolean = false;
				for each (var val2:String in auxArray){
					if(val == val2){
						exists = true;
						break;
					}
				}
				if(!exists){
					auxArray.push(val);
				}
			}
			return auxArray;
		}
		
		/**
		 * 
		 */ 
		public static function dumpObj(obj:Object):String {
			var res:String = "";
			for (var p:String in obj) {
				res += p + ":" + obj[p] + ", ";
			}
			return res;
		}

		/**
		 * Generates a where clause from the filter string and the list of fields to use.
		 * The where clause has the form: WHERE <field1> like '%<filterWord1>%' OR <field2> like '%<filterWord1>%' ...
		 * OR <field1> like '%<filterWord2>%' OR <field2> like '%<filterWord2>%' ...
		 */
		public static function generateFilterClause(commaSeparated:String, fields:Array):String {
			var re:RegExp = /\s+/g;
			if (fields == null || fields.length == 0 || 
				commaSeparated == null || commaSeparated.replace(re, "").length == 0) {
				return "";
			}
			var words:Array = commaSeparated.split(/,\s*/);
			var whereClause:String = " WHERE ";
			for (var i:int=0; i<words.length; i++) {
				for (var j:int=0; j<fields.length; j++) {
					whereClause += fields[j] + " like '%"+ words[i] +"%' OR ";
				}
			}
			whereClause = whereClause.substr(0, whereClause.length-4);
			trace('generateFilterClause("' + commaSeparated + '", <' + fields + '>)=' + whereClause);
			return whereClause;
		}
		
		public static function concat(stringArr:Array, sep:String = ", " , quote:String = ""):String {
			if (stringArr == null || stringArr.length == 0) {
				return "";
			}
			var clause:String = "";
			for (var i:int; i < stringArr.length; i++) {
				clause += quote + stringArr[i] + quote + sep;
			}
			return clause.substr(0, clause.length - 2);
		}
		
		/**
		 *  Add all items from an Array into an ArrayCollection
		 */ 
		public static function addArrayIntoArrayCollection(from:Array,to:ArrayCollection):void{
			for each(var o:Object in from){
				to.addItem(o);
			}
		}
		
		/**
		 *  Add all items from a Vector into an Array
		 */ 
		public static function vectorToArray(obj:Object):Array {
			if (!obj) {
				return [];
			} else if (obj is Array) {
				return obj as Array;
			} else if (obj is Vector.<*>) {
				var array:Array = new Array(obj.length);
				for (var i:int = 0; i < obj.length; i++) {
					array[i] = obj[i];
				}
				return array;
			} else {
				return [obj];
			}
		} 
		
		/**
		 * Returns an ArrayCollection of all values within the specified dictionary.
		 */ 
		public static function getValuesFromDictionary(dic:Dictionary):ArrayCollection{
			var a:ArrayCollection = new ArrayCollection();
			
			for each (var value:Object in dic){
				a.addItem(value);
			}
			
			return a;
		}
		
		/**
		 * Returns an String with all lines of a file, if file doesn't exist it will return null 
		 */
		public static function readEntireTextFile(file:File):String{
			var allLines:String = null;
			if(file.exists){
				//read the file entirely
				var fileStream:FileStream = new FileStream();
				fileStream.open(file, FileMode.READ);
				var bytesNumber:uint = fileStream.bytesAvailable;
				allLines = '';
				while (bytesNumber>0){
					allLines += fileStream.readUTFBytes(bytesNumber);
					bytesNumber = fileStream.bytesAvailable;
				}
				fileStream.close();
			}
			return allLines;
		}
		
		/**
		 * Navigates to some urls
		 */ 
		//Timer
		private static var urlTimer:Timer = new Timer(100, 0); //180000 = 5 minutes;
		private static var urlsToNavigate:ArrayCollection = new ArrayCollection();
		public static function navigateToUrls(urls:ArrayCollection):void{
			urlsToNavigate.addAll(urls);
			urlTimer.addEventListener(TimerEvent.TIMER, onTick);
			urlTimer.start();
		}
		
		private static function onTick(event:TimerEvent):void{
			if(urlsToNavigate.length>0){
				var url:URLRequest = urlsToNavigate.getItemAt(0) as URLRequest;
				navigateToURL(url);
				urlsToNavigate.removeItemAt(0);
			}else{
				urlTimer.stop();
			}
		}
		
		/**
		 * Centers a window in the middle of the screen
		 */ 
		public static function centerWindowOnScreen(window:WindowedApplication):void{
			window.nativeWindow.x = (Screen.mainScreen.bounds.width - window.width)/2;
			window.nativeWindow.y = (Screen.mainScreen.bounds.height - window.height)/2;
		}

		
		/**
		 *  Returns the application version number
		 */
		public static function getApplicationVersionNumber():String{
			var descriptor:XML = NativeApplication.nativeApplication.applicationDescriptor;
			var ns:Namespace = descriptor.namespaceDeclarations()[0];
			var version:String = descriptor.ns::versionLabel;
			return version;
		}
		
		/** 
		 * 	Define a sort compare function ignoring case
		 */
		public static function sortCompareFunctionIgnoreCase(obj1:Object, obj2:Object, gc:GridColumn):int {
			// Create an instance of the SortingCollator.
			var collator:SortingCollator = new SortingCollator();
			// Make the sort case insensitive. The default is case sensitive.
			collator.ignoreCase = true;
			if(obj1!=null && obj2!=null){
				var data1:String = (obj1[gc.dataField]==null?'':obj1[gc.dataField]);
				var data2:String = (obj2[gc.dataField]==null?'':obj2[gc.dataField]);
				return collator.compare(data1, data2);
			}
			
			return 0;
		}
		
		/**
		 * return the string cut to a size
		 */
		public static function cutStringToSize(str:String,size:int):String{
			var returnStr:String = str;
			if(str!=null && str.length > size){
				returnStr = returnStr.substr(0,size-3) +'...';
			}
			return returnStr;
		}
		
		/**
		 * returns the quantity of matches of "char" in "text"
		 */
		public static function getQuantityOfChar(text:String,char:String):int{
			if(text==null){
				return 0;
			}
			var ar:Array = text.split(char);
			return ar.length-1;
		}
		
		/**
		 * returns the length of a string inside a label
		 * average length per char = 7
		 * empty length = 5.5
		 * formula = average length - length of empties 
		 */ 
		public static function stringLengthToLabelLength(text:String):int{
			var length:Number = 0;
			var char:String='';
			for(var i:int=0;i<text.length;i++){
				char = text.charAt(i);
				if(char.search(new RegExp("[0-9]"))==0){
					length+= 7;
				}else if(char.indexOf(" ")==0){
					length+= 3;
				}else if(char.indexOf("_")==0){
					length+= 8; 
				}else if(char.search(new RegExp("[A-Z]"))==0){
					length+= 8;
				}else if(char.search(new RegExp("[ftr]"))==0){
					length+= 4;
				}else if(char.search(new RegExp("[jil]"))==0){
					length+= 3;
				}else if(char.search(new RegExp("[mw]"))==0){
					length+= 10;
				}else if(char.search(new RegExp("[a-z]"))==0){
					length+= 7;
				}else{
					length+= 4;
				}
			}
			//return text.length * 7 - (Utils.getQuantityOfChar(text,' ') *5.5);
			return length as int; 
		}
	
		/**
		 * converts a String to Boolean
		 */
		public static function stringToBoolean(str:String):Boolean{
			if(str.toLowerCase() == "true" || str == "1"){
				return true;
			}else{
				return false;
			}
		}
		
		/**
		 * Converts ',' in '.'.
		 * Use in textInput as changingEvent in order to allow only valid decimal numbers
		 */  
		public static function convertsToDecimalCharacterChangingEvent(event:TextOperationEvent):void
		{
			if(	event.operation is InsertTextOperation ){
				var insertEvent:InsertTextOperation = event.operation as InsertTextOperation;
				//converts ',' in '.'
				if(insertEvent.text==','){
					insertEvent.text = '.';
				}
			}
			
		}
	}
}