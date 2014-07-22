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
 * java文件操作工具类
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
	 * 拷贝文件夹中的所有文件到另外一个文件夹
	 * 
	 * @param srcDirector
	 *            源文件夹
	 * @param desDirector
	 *            目标文件夹
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
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            folderPath 文件夹完整绝对路径
	 */
	public static void delFolder(String folderPath) throws Exception {

		// 删除完里面所有内容
		delAllFile(folderPath);
		String filePath = folderPath;
		filePath = filePath.toString();
		File myFilePath = new File(filePath);
		// 删除空文件夹
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
	 * 获取文件夹下的所有文件路径
	 * 
	 * @param dirPath
	 * @return 文件路径列表
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
		System.out.println("开始读取数据源文件列表 " + DateUtils.sysTime);
		// 所有文件列表获取
		List<File> fileList = FileUtils
				.getAllFilePath("E:/北航/bigdata/short-text-documents");
		System.out.println("读取数据源文件列表结束 " + DateUtils.sysTime+" "+fileList.size());
		// 总文件数
		int tootleFiles = fileList.size();
		//
		Map<String, Map<String, Integer>> fileWordMap = new HashMap<String, Map<String, Integer>>();
		Map<String, Map<String, Double>> fileWordTFMap = new HashMap<String, Map<String, Double>>();
		Map wordTF = null;
		System.out.println("开始计算文件TF  " + DateUtils.sysTime);
		for (int i = 0; i < fileList.size() ; i ++) {
			File file = fileList.get(i);
			Map<String, Integer> wordCount = getDocKeyWordMap(file);
			fileWordMap.put(file.getPath(), wordCount);
			wordTF = getWordTF(wordCount);
			fileWordTFMap.put(file.getPath(), wordTF);
			System.out.println("开始处理文件 [" + i+ "] " + file.getPath());
		}
		System.out.println("计算文件TF结束  " + DateUtils.sysTime);
		System.out.println("开始计算包含某一词语的文件数  " + DateUtils.sysTime);
		Map<String, Integer> containWordFilesMap = getContainWordFiles(fileWordMap);
		
		// 特征值计算
		System.out.println("开始特征值计算  " + DateUtils.sysTime);
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

		// 将键值赌赢的entryset放到链表中
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {

			// 将链表按照值得从大到小进行排序
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

	// stop words 过滤

	/**
	 * 获取包含某一词语的文件数
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
	 * 获取一个文件中每个词语出现的次数列表
	 * 
	 * @param file
	 * @return
	 */
	public static Map<String, Integer> getDocKeyWordMap(File file) {
		String fileData = stringFilter(readTxtFile(file));
		// 分词
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
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public static String readTxtFile(File file) {
		StringBuffer data = new StringBuffer();
		try {
			String encoding = "GBK";
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					data.append(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data.toString();
	}
	
	  public   static   String stringFilter(String   str)   throws   PatternSyntaxException   {     
          // 只允许字母和数字       
          // String   regEx  =  "[^a-zA-Z0-9]";                     
             // 清除掉所有特殊字符  
		    String regEx="[\"_`~!@#$%^&*()+-=|{}':;',\\[\\].<>/?~！!&*@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"0-9]";  
		    Pattern   p   =   Pattern.compile(regEx);     
		    Matcher   m   =   p.matcher(str);     
		    return   m.replaceAll("").trim();     
    }  
	  
	/**
	 * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public static String readTxtFile(String filePath) {
		StringBuffer data = new StringBuffer();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					data.append(lineTxt + " \r");
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data.toString();
	}

	/**
	 * 删除指定文件夹下所有文件
	 * 
	 * @param path
	 *            文件夹完整绝对路径
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
				// 先删除文件夹里面的文件
				delAllFile(path + "/" + tempList[i]);
				// 再删除空文件夹
				delFolder(path + "/" + tempList[i]);
				flag = true;
			}

		}
		return flag;
	}

}
