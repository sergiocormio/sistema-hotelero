package com.cdz.sh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

	
	/**
	 * @param srcFolder path to the folder to be zipped
	 * @param destZipFile path to the final zip file
	 */
	 public static boolean zipFolder( String srcFolder, String destZipFile) {
		if (new File(srcFolder).isDirectory()) {

			ZipOutputStream zip = null;
			FileOutputStream fileWriter = null;
			try {
				File outPutFile = new File(destZipFile);
				
				fileWriter = new FileOutputStream(outPutFile);
				zip = new ZipOutputStream(fileWriter);
			} 
			catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}

			addFolderToZip("", srcFolder, zip); //$NON-NLS-1$
			try {
				zip.flush();
				zip.close();
			} 
			catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
			return true;
		} 
		else {
			return false;
		}
	}



	/**
	 * @param zipFile the zip file that needs to be unzipped
	 * @param destFolder the folder into which unzip the zip file and create the folder structure
	 */
	public static void unzipFolder( String zipFile, String destFolder ) {
		try {
			ZipFile zf = new ZipFile(zipFile);
			Enumeration< ? extends ZipEntry> zipEnum = zf.entries();
			String dir = destFolder;

			while( zipEnum.hasMoreElements() ) {
				ZipEntry item = (ZipEntry) zipEnum.nextElement();

				if (item.isDirectory()) {
					File newdir = new File(dir + File.separator + item.getName());
					newdir.mkdir();
				} 
				else {
					String newfilePath = dir + File.separator + item.getName();
					File newFile = new File(newfilePath);
					if (!newFile.getParentFile().exists()) {
						newFile.getParentFile().mkdirs();
					}

					InputStream is = zf.getInputStream(item);
					FileOutputStream fos = new FileOutputStream(newfilePath);
					int ch;
					while( (ch = is.read()) != -1 ) {
						fos.write(ch);
					}
					is.close();
					fos.close();
				}
			}
			zf.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}



	private static void addToZip( String path, String srcFile, ZipOutputStream zip ) {
		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		}
		else {
			byte[] buf = new byte[1024];
			int len;
			try {
				FileInputStream in = new FileInputStream(srcFile);
				zip.putNextEntry(new ZipEntry(path + File.separator + folder.getName()));
				while( (len = in.read(buf)) > 0 ) {
					zip.write(buf, 0, len);
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}



	private static void addFolderToZip( String path, String srcFolder, ZipOutputStream zip ) {
		File folder = new File(srcFolder);
		String listOfFiles[] = folder.list();
		try {
			for( int i = 0; i < listOfFiles.length; i++ ) {
				addToZip(path + File.separator + folder.getName(), srcFolder + File.separator
						+ listOfFiles[i], zip);
			}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
