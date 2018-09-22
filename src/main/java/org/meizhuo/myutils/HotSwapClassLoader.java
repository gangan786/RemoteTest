package org.meizhuo.myutils;

/**
 * @ProjectName: RemoteTest
 * @Package: org.meizhuo.RemoteTest
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/9/21 20:52
 * @UpdateUser:
 * @UpdateDate: 2018/9/21 20:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader(){
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte){
        //使用defineClass()这个方法把提交执行的java类的byte[]数组转变为Class对象
        return defineClass(null,classByte,0,classByte.length);
    }

}
