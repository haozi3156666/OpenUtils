package com.antilope.openutils.fileop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.antilope.openutils.base.DateUtils;

/**
 * java�ļ�����������
 * 
 * @author Chris
 * @version 2008-6-26
 */
public class FileUtils {

	public static Logger log = Logger.getLogger(FileUtils.class);

	private static void createDirectory(String directory, String subDirectory) {
		String dir[];
		File fl = new File(directory);
		try {
			if (subDirectory == "" && fl.exists() != true) {
				fl.mkdir();
			} else if (subDirectory != "") {
				dir = subDirectory.replace('\\', '/').split("/");
				for (int i = 0; i < dir.length; i++) {
					File subFile = new File(directory + File.separator + dir[i]);
					if (subFile.exists() == false)
						subFile.mkdir();
					directory += File.separator + dir[i];
				}

			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * �����ļ����е������ļ�������һ���ļ���
	 * 
	 * @param srcDirector
	 *            Դ�ļ���
	 * @param desDirector
	 *            Ŀ���ļ���
	 */
	public static void copyFileWithDirector(String srcDirector,
			String desDirector) throws IOException {
		(new File(desDirector)).mkdirs();
		File[] file = (new File(srcDirector)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				FileInputStream input = new FileInputStream(file[i]);
				FileOutputStream output = new FileOutputStream(desDirector
						+ "/" + file[i].getName());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (file[i].isDirectory()) {
				copyFileWithDirector(srcDirector + "/" + file[i].getName(),
						desDirector + "/" + file[i].getName());
			}

		}

	}

	/**
	 * ɾ���ļ���
	 * 
	 * @param folderPath
	 *            folderPath �ļ�����������·��
	 */
	public static void delFolder(String folderPath) throws Exception {

		// ɾ����������������
		delAllFile(folderPath);
		String filePath = folderPath;
		filePath = filePath.toString();
		File myFilePath = new File(filePath);
		// ɾ�����ļ���
		myFilePath.delete();
	}

	static List<File> fileList = new ArrayList<File>();

	public static List<File> getFileByDir(String dirPath) {
		File file = new File(dirPath);
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (dirPath.endsWith(File.separator)) {
				temp = new File(dirPath + tempList[i]);
			} else {
				temp = new File(dirPath + File.separator + tempList[i]);
			}

			if (temp.isDirectory()) {
				getFileByDir(dirPath + "/" + tempList[i]);
			} else {
				fileList.add(temp);
			}
		}
		return fileList;
	}

	/**
	 * ��ȡ�ļ����µ������ļ�·��
	 * 
	 * @param dirPath
	 * @return �ļ�·���б�
	 */
	public static List<File> getAllFilePath(String dirPath) {
		List<File> fileList = new ArrayList<File>();
		File file = new File(dirPath);
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (dirPath.endsWith(File.separator)) {
				temp = new File(dirPath + tempList[i]);
			} else {
				temp = new File(dirPath + File.separator + tempList[i]);
			}
			if (temp.isDirectory()) {
				fileList.addAll(getFileByDir(dirPath + "/" + tempList[i]));
			} else {
				fileList.add(temp);
			}
		}
		return fileList;
	}

	public static void main_(String[] args) {
		String data = readTxtFile("c:/stopwords.txt");
		String[] words = data.split(" ");
		for (String s : words) {
			System.out.println("add(\"" + s.trim() + "\");");
			System.out.println("add(\"" + s.trim().toUpperCase() + "\");");
		}
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		System.out.println("��ʼ��ȡ����Դ�ļ��б� " + DateUtils.sysTime);
		// �����ļ��б��ȡ
		List<File> fileList = FileUtils
				.getAllFilePath("E:/����/bigdata/short-text-documents");
		System.out.println("��ȡ����Դ�ļ��б���� " + DateUtils.sysTime+" "+fileList.size());
		// ���ļ���
		int tootleFiles = fileList.size();
		//
		Map<String, Map<String, Integer>> fileWordMap = new HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Double>> fileWordTFMap = new HashMap<String, Map<String, Double>>();
		Map wordTF = null;
		System.out.println("��ʼ�����ļ�TF  " + DateUtils.sysTime);
		for (int i = 0; i < fileList.size() ; i ++) {
			File file = fileList.get(i);
			Map<String, Integer> wordCount = getDocKeyWordMap(file);
			fileWordMap.put(file.getPath(), wordCount);
			wordTF = getWordTF(wordCount);
			fileWordTFMap.put(file.getPath(), wordTF);
			System.out.println("��ʼ�����ļ� [" + i+ "] " + file.getPath());
		}
		System.out.println("�����ļ�TF����  " + DateUtils.sysTime);
		System.out.println("��ʼ�������ĳһ������ļ���  " + DateUtils.sysTime);
		Map<String, Integer> containWordFilesMap = getContainWordFiles(fileWordMap);
		
		// ����ֵ����
		System.out.println("��ʼ����ֵ����  " + DateUtils.sysTime);
		int k = 1 ;
		for (Iterator tfit = fileWordTFMap.entrySet().iterator(); tfit
				.hasNext();) {
			Map<String, Double> wordImportanceMap = new HashMap<String, Double>();
			Map.Entry tfen = (Entry) tfit.next();
			String fileName = (String) tfen.getKey();
			Map wordTF_ = (Map) tfen.getValue();
			for (Iterator it = wordTF_.entrySet().iterator(); it.hasNext();) {
				Map.Entry en = (Entry) it.next();
				String word = (String) en.getKey();
				double logBase = (double) (Math.round(tootleFiles
						/ containWordFilesMap.get(word)) / 100.0);
				double wordImportance = (Double) wordTF_.get(word)
						* (Math.log((double) logBase) / Math.log((double) 2));
				if(!StopWords.STOPWORDS.contains(word) && !StopWords.STOPWORDS.contains(word.toLowerCase()))
				wordImportanceMap.put(word, wordImportance);
				
			}
			log.info("["+(k++)+"] "+fileName + " : " + mapKey2String(sortByValue(wordImportanceMap,20)));
			// System.out.println(fileName+" = "+wordImportanceMap);
		}
	}
	
	public static String mapKey2String(Map map){
		StringBuffer result = new StringBuffer();
		int i = 1;
		for(Iterator it = map.entrySet().iterator(); it.hasNext();){
			Map.Entry en = (Entry) it.next();
			String word = (String) en.getKey();
			result.append((i++)+" : "+word);
			result.append(",");
		}
		return result.toString();
	}

	public static Map sortByValue(Map map,int limit) {

		// ����ֵ��Ӯ��entryset�ŵ�������
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {

			// ��������ֵ�ôӴ�С��������
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		Map result = new LinkedHashMap();
		int i = 0 ; 
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
			i ++;
			if(i > limit ){
				break;
			}
		}
		return result;
	}

	// stop words ����

	/**
	 * ��ȡ����ĳһ������ļ���
	 * 
	 * @return
	 */
	public static Map<String, Integer> getContainWordFiles(
			Map<String, Map<String, Integer>> fileWords) {
		Map<String, Integer> containWordFilesMap = new HashMap<String, Integer>();
		for (Iterator it = fileWords.entrySet().iterator(); it.hasNext();) {
			Map.Entry en = (Entry) it.next();
			String fileName = (String) en.getKey();
			Map<String, Integer> wordCount = (Map<String, Integer>) en
					.getValue();
			for (Iterator its = wordCount.entrySet().iterator(); its.hasNext();) {
				Map.Entry wordEn = (Entry) its.next();
				String word = (String) wordEn.getKey();
				if (containWordFilesMap.containsKey(word)) {
					containWordFilesMap.put(word,
							containWordFilesMap.get(word) + 1);
				} else {
					containWordFilesMap.put(word, 1);
				}
			}

		}
		return containWordFilesMap;
	}

	/**
	 * ��ȡһ���ļ���ÿ��������ֵĴ����б�
	 * 
	 * @param file
	 * @return
	 */
	public static Map<String, Integer> getDocKeyWordMap(File file) {
		String fileData = stringFilter(readTxtFile(file));
		// �ִ�
		String[] wordList = fileData.split(" ");
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		for (String word : wordList) {
			if (StopWords.STOPWORDS.contains(word.trim().toLowerCase())) {
				continue;
			}
			if (wordCount.containsKey(word.trim())) {
				wordCount.put(word.trim(), wordCount.get(word.trim()) + 1);
			} else {
				wordCount.put(word.trim(), 1);
			}
		}
		wordCount.put("total", wordList.length);
		return wordCount;
	}

	public static Map<String, Double> getWordTF(Map<String, Integer> wordCount) {
		Map<String, Double> wordTF = new HashMap<String, Double>();
		//
		long maxWordCount = wordCount.get("total");
		for (Iterator it = wordCount.entrySet().iterator(); it.hasNext();) {
			Map.Entry en = (Entry) it.next();
			String word = (String) en.getKey();
			Integer count = (Integer) en.getValue();
			double tf = (double) (Math.round(count / maxWordCount) / 100.0);
			wordTF.put(word, tf);
		}
		return wordTF;
	}

	/**
	 * ���ܣ�Java��ȡtxt�ļ������� ���裺1���Ȼ���ļ���� 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
	 * 3����ȡ������������Ҫ��ȡ�����ֽ��� 4��һ��һ�е������readline()�� ��ע����Ҫ���ǵ����쳣���
	 * 
	 * @param filePath
	 */
	public static String readTxtFile(File file) {
		StringBuffer data = new StringBuffer();
		try {
			String encoding = "GBK";
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					data.append(lineTxt);
				}
				read.close();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data.toString();
	}
	
	  public   static   String stringFilter(String   str)   throws   PatternSyntaxException   {     
          // ֻ������ĸ������       
          // String   regEx  =  "[^a-zA-Z0-9]";                     
             // ��������������ַ�  
		    String regEx="[\"_`~!@#$%^&*()+-=|{}':;',\\[\\].<>/?~��!&*@#��%����&*��������+|{}������������������������\"0-9]";  
		    Pattern   p   =   Pattern.compile(regEx);     
		    Matcher   m   =   p.matcher(str);     
		    return   m.replaceAll("").trim();     
    }  
	  
	/**
	 * ���ܣ�Java��ȡtxt�ļ������� ���裺1���Ȼ���ļ���� 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
	 * 3����ȡ������������Ҫ��ȡ�����ֽ��� 4��һ��һ�е������readline()�� ��ע����Ҫ���ǵ����쳣���
	 * 
	 * @param filePath
	 */
	public static String readTxtFile(String filePath) {
		StringBuffer data = new StringBuffer();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					data.append(lineTxt + " \r");
				}
				read.close();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data.toString();
	}

	/**
	 * ɾ��ָ���ļ����������ļ�
	 * 
	 * @param path
	 *            �ļ�����������·��
	 */
	public static boolean delAllFile(String path) throws Exception {

		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}

		if (!file.isDirectory()) {
			return flag;
		}

		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}

			if (temp.isFile()) {
				temp.delete();
			}

			if (temp.isDirectory()) {
				// ��ɾ���ļ���������ļ�
				delAllFile(path + "/" + tempList[i]);
				// ��ɾ�����ļ���
				delFolder(path + "/" + tempList[i]);
				flag = true;
			}

		}
		return flag;
	}

}
