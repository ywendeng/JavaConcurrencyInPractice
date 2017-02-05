package com.ywdeng.threadsafety;

import java.math.BigInteger;
import java.util.concurrent.atomic.*;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * CountingFactorizer
 *
 * Servlet that counts requests using AtomicLong
 *
 * @author Brian Goetz and Tim Peierls
 */
/**
 * @author ywdeng
 *为了确保线程安全对于"先检查后执行(例如延迟初始化问题)"和"读取→修改→写入"这样的复合
 *操作，必须需要确保更新所有的状态变量在一个原子操作完成的
 *1. 对于在无状态的类中添加一个状态变量时-----可以使用线程安全对象来管理类的状态
 */
@ThreadSafe
public class CountingFactorizer extends GenericServlet implements Servlet {
   // AtomicLong 是线程安全的类
	private final AtomicLong count = new AtomicLong(0);

    public long getCount() { return count.get(); }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {}
    BigInteger extractFromRequest(ServletRequest req) {return null; }
    BigInteger[] factor(BigInteger i) { return null; }
}
