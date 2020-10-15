import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public final static String TEST_STR = "{\n" +
            "   \"data\" : \"测试\",\n" +
            "   \"radioId\" : 8688102,\n" +
            "   \"type\" : \"infor\",\n" +
            "   \"workId\" : 61233,\n" +
            "   \"workTime\" : \"2020:05:23 16-05-10\"\n" +
            "}";

    private final static String IP = "10.67.52.83";//服务器IP地址
    private final static int PORT = 50100;//服务器端口地址

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("----------工单下发测试软件-------------");
        byte[] gbkData = TEST_STR.getBytes("gbk");
        byte[] utf8Data = bytesToUTF8String(gbkData);
        try {
            Socket s = new Socket(IP, PORT);
            OutputStream oos = s.getOutputStream();
            oos.write(utf8Data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * gbk数据转utf-8
     *
     * @param data 原始gbk数据
     * @return 转换后的utf-8数据
     */
    public static byte[] bytesToUTF8String(byte[] data) {
        String str = "";
        try {
            str = new String(data, "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str.getBytes();
        }
    }
}
