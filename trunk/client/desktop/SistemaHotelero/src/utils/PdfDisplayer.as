package utils
{
	import flash.utils.ByteArray;
	import flash.filesystem.File;
	import flash.filesystem.FileStream;
	import flash.filesystem.FileMode;
	
	
	public class PdfDisplayer
	{
		
		static public function displayPdf(pdfFile:ByteArray):void
		{
			
			var destFile:File = File.applicationStorageDirectory; 
			
			var fileName:String = new Date().time.toString() + ".pdf";
			
			destFile = destFile.resolvePath(fileName);  
			var outStream:FileStream = new FileStream();
			
			outStream.open(destFile, FileMode.WRITE);
			
			outStream.writeBytes(pdfFile, 0, pdfFile.length);
			
			outStream.close();
			
			destFile.openWithDefaultApplication();
		}
	}
}