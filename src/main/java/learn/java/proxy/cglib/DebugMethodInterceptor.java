package learn.java.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 自定义MethodInterceptor
 */
public class DebugMethodInterceptor implements MethodInterceptor {

    /**
     * @param obj         被代理的对象（需要增强的对象）
     * @param method      被拦截的方法（需要增强的方法）
     * @param args        方法入参
     * @param proxy       用于调用原始方法
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method: "+method.getName());
        Object o = proxy.invokeSuper(obj, args);
        //调用方法之后，同样可以添加自己的操作
        System.out.println("after method: "+method.getName());
        return o;
    }
}
