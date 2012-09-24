package com.cdz.sh.util;

import org.junit.Test;

public class TestZipUtil {

	@Test
	public void testZip() {
		
		String srcFolder = "./dellosky";
		String destFilePath = "c:/mybackups/backup_en_zip.zip";
			
			
		ZipUtil.zipFolder(srcFolder, destFilePath);
		
	}
	
	@Test
	public void testUnzip() {
		
		String zipFilePath = "c:/mybackups/backup_en_zip.zip";
		String destFolder = "./";
			
			
		ZipUtil.unzipFolder(zipFilePath, destFolder);
		
	}

}
