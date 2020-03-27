package test.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CreateNewFolder {

	public static void main(String[] args) throws IOException {
		// need to change or path mention from yml file (read source & dest path from
		// yml file)
		String outPath = "/home/sujay/Documents/FilesFromJavaCode/output/";
		String inPath = "/home/sujay/Documents/FilesFromJavaCode/input/";

		// folder name need to generate
		// (At run time, based on selected company or selected Account number)
		String floder = "1116884005_FEB-2020";

		// Output path to create new folder and copy the file(s)
		String destPath = outPath + floder;

		// Creating a File object
		File file = new File(destPath);

		// Checking is particular folder exist or not
		if (!file.exists()) {
			// Creating the directory, if not exist
			file.mkdir();
		}

		// from path mention from yml file and file name have to be declare
		File from = null;
		File dest = null;
		int fileNum = 1;
		for (int i = 1; i <= fileNum; i++) {
			from = new File(inPath + "example-" + i + ".csv");
			dest = new File(destPath + "/example-" + i + ".csv");
			// File exists copy from source to destination path else send error message
			if (from.exists()) {
				copyFileSourceToDestination(from, dest);
			} else {
				System.out.println("From File is not exist & throw an Exception");
			}
		}
		ZipFolder2 zipFolder = new ZipFolder2();
		zipFolder.generateZipFile(destPath,destPath);
	}
	
	/** 
	 *  This method copy a file from one directory to another. src is path of the 
	 *  file to copy dest is the path where to copy the file. 
	 *   @param src 
	 *   @param dest 
	 *   @throws IOException */ 
	public static void copyFileSourceToDestination(File src, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(dest);
			// buffer size 1K
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = is.read(buf)) > 0) {
				os.write(buf, 0, bytesRead);
			}
		} finally {
			is.close();
			os.close();
		}
	}

}
