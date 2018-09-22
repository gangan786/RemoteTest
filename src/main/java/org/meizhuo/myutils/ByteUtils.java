package org.meizhuo.myutils;

/**
 * @ProjectName: RemoteTest
 * @Package: org.meizhuo.RemoteTest
 * @ClassName: ${TYPE_NAME}
 * @Description: Bytes数组处理工具
 * @Author: Gangan
 * @CreateDate: 2018/9/21 22:16
 * @UpdateUser:
 * @UpdateDate: 2018/9/21 22:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class ByteUtils {

    /**
     * 将指定位置的byte改为int值返回
     *
     * @param b
     * @param start 偏移量
     * @param len   长度
     * @return
     */
    public static int bytes2Int(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) b[i]) & 0xff;
            n <<= (--len) * 8;
            sum = n + sum;
        }
        return sum;
    }

    /**
     * 将指定int值转化为byte数组返回
     *
     * @param value
     * @param len   数组长度
     * @return
     */
    public static byte[] int2bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }

    /**
     * 将对应位置长度的元素转换为String返回
     *
     * @param b
     * @param start
     * @param len
     * @return
     */
    public static String bytes2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    /**
     * 将string转化为byte数组返回
     *
     * @param string
     * @return
     */
    public static byte[] string2Bytes(String string) {
        return string.getBytes();
    }

    /**
     * 将原始数组的内容替换
     * @param originalBytes 原始数组数据
     * @param offset 起始偏移量
     * @param len 偏移长度
     * @param replaceBytes 替换内容
     * @return
     */
    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[replaceBytes.length + replaceBytes.length - len];
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
        System.arraycopy(originalBytes, offset + len, newBytes, offset + replaceBytes.length,
                originalBytes.length - offset - len);
        return newBytes;
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[1024];
        bytes[0] = 11;
        bytes[1] = 'a';
        ByteUtils.bytes2Int(bytes, 0, 1024);
    }

}
