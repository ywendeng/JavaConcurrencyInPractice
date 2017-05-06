package com.ywdeng.threadsafety;

import java.math.BigInteger;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * CachedFactorizer
 * <p/>
 * Servlet that caches its last request and result
 *
 * @author Brian Goetz and Tim Peierls
 */
/**
 * @author ywdeng
 * 状态变量是锁保护的-----如果使用同步来协调对某个变量的访问，那么在访问变量的所有位置上
 * 都需要使用同步，而且 如果使用锁来协调某个变量访问，那么在访问变量的所有位置上需要使用
 * 同一个锁
 */
@ThreadSafe
public class CachedFactorizer extends GenericServlet implements Servlet {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;
    @GuardedBy("this") private long cacheHits;
    // 使用锁来协调变量的访问，在访问变量的所有位置上使用同一个锁，否则加锁协议会被破坏
    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;
        //同步代码块的大小需要在线程安全性，简单性，和性能之间衡量
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
