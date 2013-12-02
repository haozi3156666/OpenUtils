package com.antilope.openutils.fileop;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.util.Enumeration;
 import org.apache.tools.zip.ZipEntry;
 import org.apache.tools.zip.ZipFile;
 import org.apache.tools.zip.ZipOutputStream;
    /**
     * java文件操作工具类
     * @author Chris
     * @version 2008-6-26
     */
    public class FileUtils {
    
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
         * @param srcDirector 源文件夹
         * @param desDirector 目标文件夹
         */
        public static void copyFileWithDirector(String srcDirector, String desDirector) throws IOException {
            (new File(desDirector)).mkdirs();
            File[] file = (new File(srcDirector)).listFiles();
            for (int i = 0; i < file.length; i++) {
                if (file[i].isFile()) {
                    FileInputStream input = new FileInputStream(file[i]);
                    FileOutputStream output = new FileOutputStream(desDirector + "/" + file[i].getName());
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
                    copyFileWithDirector(srcDirector + "/" + file[i].getName(), desDirector + "/" + file[i].getName());
                }

            }

        }

        /**
         * 删除文件夹
         * @param folderPath folderPath 文件夹完整绝对路径
         */
        public static void delFolder(String folderPath) throws Exception {

            //删除完里面所有内容
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            //删除空文件夹
            myFilePath.delete();
        }
 

        /**
         * 删除指定文件夹下所有文件
         * @param path 文件夹完整绝对路径
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
                    //先删除文件夹里面的文件
                    delAllFile(path + "/" + tempList[i]);
                    //再删除空文件夹
                    delFolder(path + "/" + tempList[i]);
                    flag = true;
                }

            }
            return flag;
        }

    }


