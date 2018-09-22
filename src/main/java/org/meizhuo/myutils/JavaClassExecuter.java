package org.meizhuo.myutils;

import java.lang.reflect.Method;

/**
 * @ProjectName: RemoteTest
 * @Package: org.meizhuo.myutils
 * @ClassName: ${TYPE_NAME}
 * @Description:
 * @Author: Gangan
 * @CreateDate: 2018/9/22 14:34
 * @UpdateUser:
 * @UpdateDate: 2018/9/22 14:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class JavaClassExecuter {
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier classModifier = new ClassModifier(classByte);
        byte[] modiByte = classModifier.modifyUTF8Constant("java/lang/System", "org/meizhuo/myutils/HackSystem");
        HotSwapClassLoader hotSwapClassLoader = new HotSwapClassLoader();
        Class clazz = hotSwapClassLoader.loadByte(modiByte);
        try {
            Method method = clazz.getMethod("main", new Class[]{String.class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}
