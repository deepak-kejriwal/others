import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MsftQ2 {

	private static String input = "admin  -wx 29 Sep 1983        833 source.h\r\n"
			+ "admin  r-x 23 Jun 2003     854016 blockbuster.mpeg\r\n"
			+ "admin  --x 02 Jul 1997        821 delete-this.py\r\n"
			+ "admin  -w- 15 Feb 1971      23552 library.dll\r\n" + "admin  --x 15 May 1979  645922816 logs.zip\r\n"
			+ "jane   --x 04 Dec 2010      93184 old-photos.rar\r\n"
			+ "jane   -w- 08 Feb 1982  681574400 important.java\r\n"
			+ "admin  rwx 26 Dec 1952   14680064 to-do-list.txt";

	/*
	 * Output: x, admin, <14*2^20bytes, earliest last date 14*1024*1024=14,680,064
	 * 
	 * owner is 6 perm is 3 date is 11 size is 10
	 * 
	 * 6+1+3+1=11 date starts from 11 6+1+3+1+11+1=23 size starts from 23
	 * 
	 * 
	 */

	public static void main(String[] args) {
		String[] files = input.split("\n");

		System.out.println(getEligibleFiles(files));
	}

	private static String getEligibleFiles(String[] files) {

		Date resDate = null;
		String res = null;
		for (String file : files) {

			if (!file.startsWith("admin "))
				continue;

			if (file.charAt(9) != 'x')
				continue;

			String sizeStr = file.substring(23, 33).trim();
			if (sizeStr.length()>7 && Integer.parseInt(sizeStr) >= 14_680_064)
				continue;
			String dateStr = file.substring(11, 22);
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			Date date = null;
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (resDate == null) {
				resDate = date;
				res = dateStr;
				continue;
			}

			if (date.before(resDate)) {
				resDate = date;
				res = dateStr;
			}

		}
		return res;
	}

}
