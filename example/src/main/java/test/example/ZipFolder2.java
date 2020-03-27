package test.example;


	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.zip.ZipEntry;
	import java.util.zip.ZipOutputStream;

	public class ZipFolder2 {

		private List <String> fileList;
	    private  static String OUTPUT_ZIP_FILE ;
	    private static String SOURCE_FOLDER ;

	    public ZipFolder2() {
	        fileList = new ArrayList < String > ();
	    }

	/*
	 * public static void main(String[] args) { String zipFile =
	 * "/home/sujay/Documents/FilesFromJavaCode/file6/file45.zip"; String
	 * souceFolder = "/home/sujay/Documents/FilesFromJavaCode/file25"; //
	 * SourceFolder path
	 * 
	 * generateZipFile(souceFolder,zipFile);
	 * 
	 * }
	 */
	    //public static void main(String[] args) {
	    	public static void generateZipFile(String sourceFolder,String destFolder) {
	    	OUTPUT_ZIP_FILE=sourceFolder+".zip";
	    	SOURCE_FOLDER=sourceFolder;
	    	ZipFolder2 appZip = new ZipFolder2();
	        appZip.generateFileList(new File(SOURCE_FOLDER));
	        appZip.zipIt(OUTPUT_ZIP_FILE);
	    }

	    public void zipIt(String zipFile) {
	        byte[] buffer = new byte[1024];
	        String source = new File(SOURCE_FOLDER).getName();
	        FileOutputStream fos = null;
	        ZipOutputStream zos = null;
	        try {
	            fos = new FileOutputStream(zipFile);
	            zos = new ZipOutputStream(fos);

	            System.out.println("Output to Zip : " + zipFile);
	            FileInputStream in = null;

	            for (String file: this.fileList) {
	                System.out.println("File Added : " + file);
	                ZipEntry ze = new ZipEntry(source + File.separator + file);
	                zos.putNextEntry(ze);
	                try {
	                    in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
	                    int len;
	                    while ((len = in .read(buffer)) > 0) {
	                        zos.write(buffer, 0, len);
	                    }
	                } finally {
	                    in.close();
	                }
	            }

	            zos.closeEntry();
	            System.out.println("Folder successfully compressed");

	        } catch (IOException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                zos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void generateFileList(File node) {
	        // add file only
	        if (node.isFile()) {
	            fileList.add(generateZipEntry(node.toString()));
	        }

	        if (node.isDirectory()) {
	            String[] subNote = node.list();
	            for (String filename: subNote) {
	                generateFileList(new File(node, filename));
	            }
	        }
	    }

	    private String generateZipEntry(String file) {
	        return file.substring(SOURCE_FOLDER.length() + 1, file.length());
	    }

	}

