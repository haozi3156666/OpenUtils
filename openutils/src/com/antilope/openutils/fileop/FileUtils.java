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
     * java�ļ�����������
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
         * �����ļ����е������ļ�������һ���ļ���
         * @param srcDirector Դ�ļ���
         * @param desDirector Ŀ���ļ���
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
         * ɾ���ļ���
         * @param folderPath folderPath �ļ�����������·��
         */
        public static void delFolder(String folderPath) throws Exception {

            //ɾ����������������
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            //ɾ�����ļ���
            myFilePath.delete();
        }
 

        /**
         * ɾ��ָ���ļ����������ļ�
         * @param path �ļ�����������·��
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
                    //��ɾ���ļ���������ļ�
                    delAllFile(path + "/" + tempList[i]);
                    //��ɾ�����ļ���
                    delFolder(path + "/" + tempList[i]);
                    flag = true;
                }

            }
            return flag;
        }

    }


