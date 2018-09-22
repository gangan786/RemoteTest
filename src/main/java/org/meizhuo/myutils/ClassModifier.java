package org.meizhuo.myutils;

/**
 * @ProjectName: RemoteTest
 * @Package: org.meizhuo.myutils
 * @ClassName: ${TYPE_NAME}
 * @Description: 修改Class文件中常量池部分
 * @Author: Gangan
 * @CreateDate: 2018/9/22 11:12
 * @UpdateUser:
 * @UpdateDate: 2018/9/22 11:12
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class ClassModifier {

    private static final int CONSTANT_POOL_COUNT_INDEX = 8;//class文件中常量池的起始偏移

    private static final int CONSTANT_UTF8_info = 1;//CONSTANT_UTF8_info常量的tag标志

    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5, 5};//常量池中11种常量所占的长度

    private static final int u1 = 1;
    private static final int u2 = 2;

    private byte[] classByte;

    public ClassModifier(byte[] classByte) {
        this.classByte = classByte;
    }

    public byte[] modifyUTF8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;//常量开始的起始偏移量
        for (int i = 0; i < cpc; i++) {
            int tag = ByteUtils.bytes2Int(classByte, offset, u1);//获取每个常量的标志位
            if (tag == CONSTANT_UTF8_info) {
                int len = ByteUtils.bytes2Int(classByte, offset + u1, u2);//获取UTF8_info常量的长度
                offset += (u1 + u2);//UTF8_info常量内容开始的起始偏移量
                String str = ByteUtils.bytes2String(classByte, offset, len);//将内容byte转换为string
                if (oldStr.equalsIgnoreCase(str)){
                    byte[] strBytes = ByteUtils.string2Bytes(newStr);
                    byte[] strLen = ByteUtils.int2bytes(newStr.length(), u2);
                    classByte=ByteUtils.bytesReplace(classByte,offset-u2,u2,strLen);//替换长度标志位
                    classByte=ByteUtils.bytesReplace(classByte,offset,len,strBytes);//替换内容标志位
                    return classByte;
                }else {
                    offset+=len;
                }
            } else {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

    /**
     * @return 返回常量池中常量的个数
     */
    private int getConstantPoolCount() {
        return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }

}
