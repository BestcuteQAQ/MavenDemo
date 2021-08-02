package rename;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Locale;


public class test {
    static String dir = "D:\\纸牌屋第二季";//文件所在路径，所有文件的根目录，记得修改为你电脑上的文件所在路径

    public static void main(String[] args) throws IOException {
//        NameToOneTwoThree(dir);
        replaceWordInName(dir,"","飘花电影piaohua.com纸牌屋第二季1024高清");
    }
 /**
   * 递归遍历文件夹获取文件
   */
    public static void replaceWordInName(String path,String newString,String oldString) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        replaceWordInName(file.getAbsolutePath(),newString,oldString);
                    } else {//是文件，判断是否需要重命名
                        fileName = file.getName();
                        parentPath = file.getParentFile();

                        //文件名包含需要被替换的字符串
                        if (fileName.contains(oldString)) {
                            newName = fileName.replaceAll(oldString, newString);//新名字
                            newDir = new File(parentPath + "/" + newName);//文件所在文件夹路径+新文件名
                            file.renameTo(newDir);//重命名
                            System.out.println("修改后：" + newDir);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void NameToLowerCase(String path) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        NameToLowerCase(file.getAbsolutePath());
                    } else {//是文件，判断是否需要重命名
                        fileName = file.getName();
                        parentPath = file.getParentFile();
                        newName = fileName.toLowerCase(Locale.ROOT);
                        newDir = new File(parentPath + "/" + newName);//文件所在文件夹路径+新文件名
                        file.renameTo(newDir);//重命名
                        System.out.println("修改后：" + newDir);

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
    public static void NameToOneTwoThree(String path) {
        int num =0;
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        NameToLowerCase(file.getAbsolutePath());
                    } else {//是文件，判断是否需要重命名
                        num++;
                        parentPath = file.getParentFile();
                        newName = "\\"+num+".mp4";
                        newDir = new File(parentPath +newName);//文件所在文件夹路径+新文件名
                        file.renameTo(newDir);//重命名
                        System.out.println("修改后：" + newDir);

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}