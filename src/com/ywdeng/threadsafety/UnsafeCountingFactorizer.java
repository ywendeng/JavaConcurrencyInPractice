package com.ywdeng.threadsafety;

import java.math.BigInteger;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * UnsafeCountingFactorizer
 *
 * Servlet that counts requests without the necessary synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */
/**
 * @author ywdeng
 * 在无状态的对象中添加一个状态，用于统计网站的访问次数
 * 
 */
@NotThreadSafe
public class UnsafeCountingFactorizer extends GenericServlet implements Servlet {
   /**
    * 设置一个私有全局变量 count 
    * 在实现++count 操作时 ，并不是原子操作的--------读入count值→ count值加上1→ 将新的count值赋值给count
    */
	private long count = 0;

    public long getCount() {
        return count;
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        ++count;
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }
}
