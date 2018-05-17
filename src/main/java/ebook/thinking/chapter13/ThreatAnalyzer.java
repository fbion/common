/**
 *
 */
package ebook.thinking.chapter13;

import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * @author zzh
 *
 */
public class ThreatAnalyzer {

	static String threatData = ""
			+ "58.27.82.161@02/10/2005\n"
			+ "204.45.234.40@02/11/2005\n"
			+ "58.27.82.161@02/11/2005\n"
			+ "58.27.82.161@02/12/2005\n"
			+ "58.27.82.161@02/12/2005\n"
			+ "[Next log section with different data format]";
	public static void main(String[] args) {
		Scanner scanner = new Scanner(threatData);
		String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@" +
		"(\\d{2}/\\d{2}/\\d{4})";
		while(scanner.hasNext(pattern)) {
			scanner.next(pattern);
			MatchResult matcher = scanner.match();
			String ip = matcher.group(1);
			String date = matcher.group(2);
			System.out.format("Threat on %s from %s\n",date,ip);
		}
	}
}
