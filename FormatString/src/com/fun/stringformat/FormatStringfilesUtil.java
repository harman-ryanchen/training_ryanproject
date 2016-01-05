package com.fun.stringformat;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

public class FormatStringfilesUtil {

	// public static BufferedReader bufread;
	// 闁圭娲ら悾楣冨棘閸ワ附顐介悹渚灠缁剁偤宕仦鑺ュ�缂佸鎷�
	private static String path = "";
	private static String originalPath = "";
	private static String targetPath = "";
	private static String PATH = "/Users/studio02/Desktop/projectfile/StringTable/";
	private static final String SPECIAL = "_&SPECIAL&_";
	

	private static String[] in = new String[] { "en.lproj", "nl.lproj", "fr.lproj", "de.lproj", "sv.lproj", "he.lproj",
			"es.lproj", "zh-Hans.lproj", "da.lproj", "fi.lproj", "ja.lproj", "pt.lproj", "id.lproj", "it.lproj",
			"ko.lproj", "nb-NO.lproj", "pl.lproj", "ru.lproj", "zh-Hant-TW.lproj" };
	private static String[] out = new String[] { "values-en/strings.xml", "values-nl/strings.xml",
			"values-fr/strings.xml", "values-de/strings.xml", "values-sv/strings.xml",
			"values-iw/strings.xml", "values-es/strings.xml", "values-zh-rCN/strings.xml",
			"values-da/strings.xml", "values-fi/strings.xml", "values-ja/strings.xml",
			"values-pt/strings.xml", "values-in/strings.xml", "values-it/strings.xml",
			"values-ko/strings.xml", "values-nb/strings.xml", "values-pl/strings.xml",
			"values-ru/strings.xml", "values-zh-rTW/strings.xml" };

	private static File filename = new File(path);
	private static String readStr = "";

	public static void format(String path) throws IOException {
		PATH = path + "/";
		File f1 = new File(PATH + "android");
		if (!f1.exists()) {
			f1.mkdir();
		}

		for (int i = 0; i < out.length; i++) {
			String outDir = out[i].replace("strings.xml", "");
			File f = new File(outDir);
			if (!f.exists()) {
				f.mkdir();
			}
			originalPath = in[i];
			targetPath = out[i];
			String str = convert_file_to_string_java_bufferedreader(PATH + originalPath + "//Localizable.strings");
			String targetStr = replaceByStr(str);
			File targetFile = FormatStringfilesUtil.creatTxtFile(PATH + "android//" + targetPath);
			FormatStringfilesUtil.writeTxtFile(targetStr, targetFile);
		}
	}

	public static void main(String[] s) throws IOException {
		File f1 = new File(PATH + "android");
		if (!f1.exists()) {
			f1.mkdir();
		}
		for (int i = 0; i < out.length; i++) {
			String outDir = out[i].replace("strings.xml", "");
			File f = new File(outDir);
			if (!f.exists()) {
				f.mkdir();
			}
			originalPath = in[i];
			targetPath = out[i];
			String str = convert_file_to_string_java_bufferedreader(PATH + originalPath + "//Localizable.strings");
			String targetStr = replaceByStr(str);
			File targetFile = FormatStringfilesUtil.creatTxtFile(PATH + "android//" + targetPath);
			FormatStringfilesUtil.writeTxtFile(targetStr, targetFile);
		}
	}

	public static String getTextFromRtf(String filePath) {
		String result = null;
		File file = new File(filePath);
		try {
			DefaultStyledDocument styledDoc = new DefaultStyledDocument();
			InputStream is = new FileInputStream(file);
			if (filePath.contains("Chinese")) {
				InputStreamReader isr = new InputStreamReader(is, "ISO8859_1");
				BufferedReader read = new BufferedReader(isr);
				new RTFEditorKit().read(read, styledDoc, 0);
				result = new String(styledDoc.getText(0, styledDoc.getLength()).getBytes("ISO8859_1"), "gbk");
			} else {
				InputStreamReader isr = new InputStreamReader(is, "utf-8");
				BufferedReader read = new BufferedReader(isr);
				new RTFEditorKit().read(read, styledDoc, 0);
				result = new String(styledDoc.getText(0, styledDoc.getLength()).getBytes("utf-8"), "utf-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String convert_file_to_string_java_bufferedreader(String fileLocation) throws IOException {

		File file = new File(fileLocation);

		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer fileContents = new StringBuffer();
		String line = br.readLine();
		while (line != null) {
			fileContents.append(line + "\n");
			line = br.readLine();
		}

		br.close();
		return fileContents.toString();
	}

	public static File creatTxtFile(String pathName) throws IOException {
		File file = new File(pathName);
		if (pathName.contains("values-")) {
			String dirPath = pathName.substring(0, pathName.lastIndexOf("/"));
			File vFile = new File(dirPath);
			if (!vFile.exists()) {
				vFile.mkdir();
			}
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		return file;
	}

	public static String readTxtFile(File file) {
		String read;
		FileReader fileread = null;
		BufferedReader bufread;
		readStr = "";
		try {
			fileread = new FileReader(file);
			bufread = new BufferedReader(fileread);
			try {
				while ((read = bufread.readLine()) != null) {
					readStr = readStr + read + "\r\n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					bufread.close();
					fileread.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readStr;
	}

	public static void writeTxtFile(String newStr, File targetFile) throws IOException {
		String filein = newStr + "\r\n" + readStr + "\r\n";
		System.out.println("writeTxtFile=" + filein);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(targetFile);
			Writer out = new OutputStreamWriter(fos, "UTF-8");
			out.write(filein);
			out.flush();
		} catch (IOException e1) {
			// TODO 闁煎浜滄慨鈺呮偨閻旂鐏�catch 闁秆嶆嫹
			e1.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e2) {
					// TODO 闁煎浜滄慨鈺呮偨閻旂鐏�catch 闁秆嶆嫹
					e2.printStackTrace();
				}
			}
		}
	}

	// public static void replaceTxtByStr(String oldStr, String replaceStr) {
	// String temp = "";
	// try {
	// File file = new File(path);
	// FileInputStream fis = new FileInputStream(file);
	// InputStreamReader isr = new InputStreamReader(fis);
	// BufferedReader br = new BufferedReader(isr);
	// StringBuffer buf = new StringBuffer();
	//
	// // 濞ｅ洦绻傞悺銊ф嫚閵夘煈鏀介柛鎾崇Ч濞间即鎯冮崟顐㈡暥閻庣櫢鎷�
	// for (int j = 1; (temp = br.readLine()) != null && !temp.equals(oldStr);
	// j++) {
	// buf = buf.append(temp);
	// buf = buf.append(System.getProperty("line.separator"));
	// }
	//
	// // 閻忓繐妫楅崬瀵革拷纭咁潐瑜板啴宕楅敓锟�
	// buf = buf.append(replaceStr);
	//
	// // 濞ｅ洦绻傞悺銊ф嫚閵夘煈鏀介柛姘叄濞间即鎯冮崟顐㈡暥閻庣櫢鎷�
	// while ((temp = br.readLine()) != null) {
	// buf = buf.append(System.getProperty("line.separator"));
	// buf = buf.append(temp);
	// }
	//
	// br.close();
	// FileOutputStream fos = new FileOutputStream(file);
	// PrintWriter pw = new PrintWriter(fos);
	// pw.write(buf.toString().toCharArray());
	// pw.flush();
	// pw.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public static String replaceByStr(String oldStr) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>");
		buffer.append("\n");
		String[] lineString = oldStr.split("\n");
		String tempStr = "";
		for (int x = 0; x < lineString.length; x++) {
			tempStr = lineString[x];

			if (tempStr.contains("\\\"")) {
				tempStr = tempStr.replace("\\\"", SPECIAL);
			}
			if (tempStr.contains("\"")) {
				String[] t = tempStr.split("\"");
				if (t.length == 5) {
					String newStr = t[3];
					if (newStr.contains("\'")) {
						newStr = "\"" + t[3] + "\"";
					}
					
					if (newStr.contains(" & ")) {
						newStr = newStr.replace(" & ", " &#038; ");
					}
					
					if (newStr.contains("%d") && newStr.contains("%s")) {
						newStr = newStr.replace("%d", "%1$d");
						newStr = newStr.replace("%s", "%2$s");
					}
					if (newStr.contains("%d")) {
						newStr = newStr.replace("%d", "%1$d");
					}
					
					if (newStr.contains("%@")) {
						newStr = newStr.replace("%@", "%1$s");
						System.out.println("%@ =" + newStr);
					}
					if (newStr.contains("iPhone/iPod/iPad")) {
						newStr = newStr.replace("iPhone/iPod/iPad", "Android device");
					}

					if (newStr.contains("iPhone")) {
						newStr = newStr.replace("iPhone/iPod/iPad", "Android device");
					}

					if (newStr.contains("iOS")) {
						newStr = newStr.replace("iOS", "Android");
					}

					if (newStr.contains("(")) {
						newStr = newStr.replace("(", "&#040;");
					}

					if (newStr.contains(")")) {
						newStr = newStr.replace(")", "&#041;");
					}

					newStr = newStr.replace(SPECIAL, "\\\"");
					buffer.append("<string name=\"" + t[1] + "\">" + newStr + "</string>");
					buffer.append("\n");
				}
			} else if (tempStr.startsWith("/*")) {
				buffer.append(tempStr.replace("/*", "<!--"));
				buffer.append("\n");
			} else if (tempStr.endsWith("*/")) {
				buffer.append(tempStr.replace("*/", "-->"));
				buffer.append("\n");
			} else if (tempStr.contains("//")) {
				buffer.append(tempStr.replace("//", "<!-- ") + " -->");
				buffer.append("\n");
			} else {
				buffer.append(tempStr);
				buffer.append("\n");
			}
		}
		buffer.append("</resources>");
		return buffer.toString();
	}

}