package com.hzg.skywalking;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * @Package: com.hzg.skywalking
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2021-12-03 16:05
 */
public class SkyWalkingTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // 只拦截SkyWalkingTest测试程序
        if (!"com/hzg/skywalking/SkyWalkingTest".equals(className)) {
            return null;
        }
        try {
            // 获取Javassist Class池
            ClassPool pool = ClassPool.getDefault();
            // 获取Class池中的SkyWalkingTest CtClass对象(与SkyWalkingTest的Class对象一一对应的关系)
            CtClass ctClass = pool.getCtClass(className.replace("/", "."));
            // 找到main方法并在逻辑前后添加程序语句
            // 声明本地方法变量
            CtMethod mainMethod = ctClass.getDeclaredMethod("main");
            mainMethod.addLocalVariable("beginTime", CtClass.longType);
            mainMethod.insertBefore("beginTime = System.currentTimeMillis();");
            mainMethod.insertAfter("System.out.println(\"总共耗时：\" + (System.currentTimeMillis() - beginTime));");
            // 返回修改过后的字节码数据
            return ctClass.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
        // 返回null代表没有修改此字节码
        return null;
    }
}
