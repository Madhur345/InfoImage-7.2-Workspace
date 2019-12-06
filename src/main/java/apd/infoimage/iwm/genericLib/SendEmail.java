package apd.infoimage.iwm.genericLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendEmail {
	@Test
	public static void SendEmailRpt() throws Exception {
		ExcelLib exl = new ExcelLib();

		try {
		
			String iifDataPath = "src\\test\\resources\\IIFTestData.xlsx";

			String toaddress = exl.getExcelData(iifDataPath, "Environment", 1, 0);
			String fromaddress = exl.getExcelData(iifDataPath, "Environment", 1, 1);
			String hostdetails = exl.getExcelData(iifDataPath, "Environment", 1, 2);

			String[] to = {};

			if (toaddress.length() != 0) {
				to = toaddress.trim().split(",");
			}
			
			String attachmentPath = "ATU Reports\\Report\\Results";			
			String attachmentfile = "(Report) ";
			
			Assert.assertTrue(sendEmailReport(hostdetails, fromaddress, to, attachmentPath, attachmentfile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean sendEmailReport(String host, String from, String[] to, String attachmentPath,
			String attachmentfilename) throws IOException {
		Boolean sent = false;
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		try {
			// String BuildDetails[] = getEnvBuildDetails();
			// Assert.assertTrue(!BuildDetails[0].isEmpty());
			// Assert.assertTrue(!BuildDetails[1].isEmpty());
			MimeMessage message = new MimeMessage(session);

			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setFrom(new InternetAddress(from));
			message.setSubject("Automation Report for 11.0 Sprint-3 Test Execution " + "");
			// BuildDetails[0]);

			String FolderName = getRecentFolder(attachmentPath);
			Thread.sleep(10000);

			String FinalFile = attachmentPath + "\\" + FolderName + "\\" + attachmentfilename + FolderName + ".xlsx";

			// Read the report excel file for no. of test cases executed, passed
			// , failed and skipped.
			ExcelLib exls = new ExcelLib();
			String totalTcs = exls.getExcelData(FinalFile, "TestSummary", 0, 1);
			String passTcs = exls.getExcelData(FinalFile, "TestSummary", 1, 1);
			String failTcs = exls.getExcelData(FinalFile, "TestSummary", 2, 1);
			String skkippedTcs = exls.getExcelData(FinalFile, "TestSummary", 3, 1);

			System.out.println("FinalFile is " + FinalFile);
			File IPfile = new File(FinalFile);
			// ---------------------------
			/*
			 * String
			 * htmlReport=attachmentPath+"\\"+FolderName+"\\"+"CurrentRun"+".
			 * html"; File htmlFile=new File(htmlReport); String
			 * piChart=attachmentPath+"\\"+FolderName+"\\"+"pieChart"+".js";
			 * File piChartFile=new File(piChart); File[]
			 * files={IPfile,htmlFile,piChartFile};
			 */

			// -----------------------
			if (IPfile.exists()) {
				// if(files.length>0){
				System.out.println("......test.....");
				String ZipFile = attachmentPath + "/" + FolderName + "/" + FolderName + ".zip";
				zipFile(IPfile, ZipFile);
				// zipMultipleFiles(files,ZipFile);
				System.out.println("Zip done....");
				Multipart multipart = new MimeMultipart();
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(ZipFile);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName("ExecutionReport.Zip");
				multipart.addBodyPart(messageBodyPart);
				message.setContent(multipart);

				// message body part (the html)
				BodyPart messageBody = new MimeBodyPart();
				// String htmlText = "<p>Hi All, </p><p>Please find attached
				// Automation Report for Below Build and
				// Environment.</p><br><p><b>Build Details :
				// </p></b></p><br><br><br><br><p>**This is an Auto Generated
				// Mail Report after Automation
				// Execution</p><br><p>Regards,</p><p>Automation Team</p>";
				String htmlText = "<p>Hi All, </p><p>Please find attached Automation Report for 11.0 Sprint-3 test execution.</p><br><p><b><p >Test Execution Summary :</p><br><p><b><p >Total Test Cases :"
						+ totalTcs + "</p> <p >Passed Test Cases :" + passTcs + "</p><p >Failed Test Cases :" + failTcs
						+ "</p><p >Skipped Test Cases :" + skkippedTcs
						+ "</p></p></b></p><br><br><br><br><p>**This is an Auto Generated Mail Report after Automation Execution</p><br><p>Regards,</p><p>Automation Team</p>";
				messageBody.setContent(htmlText, "text/html");

				// add it
				multipart.addBodyPart(messageBody);

				// message.saveChanges();
				Transport.send(message, message.getAllRecipients());
				sent = true;
			} else {
				System.out.println("File Not Found");
			}
		}
		// catch(MessagingException e)
		catch (Exception e) {
			e.printStackTrace();
		}
		return sent;
	}

	public static String getRecentFolder(String Folderpath) {
		File directory = new File(Folderpath);
		File[] directories = directory.listFiles();
		File lastModifiedFile = directories[0];
		for (int i = 0; i < directories.length; i++) {
			if (directories[i].isDirectory()) {
				if (lastModifiedFile.lastModified() < directories[i].lastModified()) {
					lastModifiedFile = directories[i];
				}
			}
		}
		System.out.println(lastModifiedFile.getName());
		return lastModifiedFile.getName();
	}

	public static void zipFile(File inputFile, String zipFilePath) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
			ZipEntry zipEntry = new ZipEntry(inputFile.getName());
			zipOutputStream.putNextEntry(zipEntry);

			@SuppressWarnings("resource")
			FileInputStream fileInputStream = new FileInputStream(inputFile);
			byte[] buf = new byte[1024];
			int bytesRead;

			while ((bytesRead = fileInputStream.read(buf)) > 0) {
				zipOutputStream.write(buf, 0, bytesRead);
			}

			zipOutputStream.closeEntry();
			zipOutputStream.close();
			fileOutputStream.close();
			System.out
					.println("Regular file :" + inputFile.getCanonicalPath() + " is zipped to archive :" + zipFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static File[] zipMultipleFiles(File[] files, String zipFilePath) {

		File zipfile = new File(zipFilePath);
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];
		try {
			// create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			// compress the files
			for (int i = 0; i < files.length; i++) {
				FileInputStream in = new FileInputStream(files[i].getPath());
				// .getCanonicalName());
				// add ZIP entry to output stream
				out.putNextEntry(new ZipEntry(files[i].getName()));
				// transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// complete the entry
				out.closeEntry();
				in.close();
			}
			// complete the ZIP file
			out.close();
			return zipfile.listFiles();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return null;

	}

}

