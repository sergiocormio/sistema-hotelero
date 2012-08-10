package utils
{
	import flash.filesystem.File;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	import flash.utils.Dictionary;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.Alert;
	import mx.formatters.CurrencyFormatter;
	import mx.validators.NumberValidator;
	
	import spark.components.TextInput;

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
		public static function stringReplaceAll( source:String, find:String, replacement:String ) : String
		{
			return source.split( find ).join( replacement );
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
		

	}
}