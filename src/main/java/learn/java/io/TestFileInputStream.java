package learn.java.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileInputStream {
    public static void main(String[] args) throws IOException {
        //test-1
        System.out.println("===============test-1==============");
        //正常读取，要多次用到当前值，需要赋值给一个 对象，每调用一次read()，流就会读取一个字节
        FileOutputStream outputStream = new FileOutputStream(new File("D:/test/io/FileInputStream/1"));
        byte[] bytes={1,2,3,4,5,6,7,8,9,10};
        outputStream.write(bytes);
        outputStream.flush();

        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream inputStream = new FileInputStream(new File("D:/test/io/FileInputStream/1"));
        int len = inputStream.available();
        for (int i = 0; i < len; i++) {
            StringBuilder str = stringBuilder.append(inputStream.read() + ",");
        }
        System.out.println(stringBuilder.toString());
        //..test-1

        //test-2
        System.out.println("===========test-2==================");
        StringBuilder builder = new StringBuilder();
        int read=0;
        FileInputStream inStream = new FileInputStream(new File("D:/test/io/FileInputStream/1"));
        while (true){
            read=inStream.read();
            builder.append(read+",");
            if (inStream.read()!=-1)    // 想用read()=-1 来判断文件是否读取完，应该每个循环只调用一次read()方法，想多次用当前读取到的值
                                        //应该赋值给对象 而不是 在循环中再调用一次 read()方法，调用一次读取一个字节，所以得到的字节少了
                continue;
            break;
        }
        System.out.println(builder.toString());
    }
}
