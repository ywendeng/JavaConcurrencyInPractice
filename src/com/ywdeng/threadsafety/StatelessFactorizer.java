package com.ywdeng.threadsafety;

import java.math.BigInteger;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * StatelessFactorizer
 *
 * A stateless servlet
 * 
 * @author Brian Goetz and Tim Peierls
 */
/**
 * @author ywdeng
 * 线程安全的核心：对状态(存储在状态变量中的数据)访问进行管理，特别是对共享状态和可变状态的访问
 * 实现线程安全通常有以下几种方法：
 * 1.不在线程之间共享状态变量
 * 2.将状态变量设置为不可变的变量
 * 3.使用同步机制----synchronized,同时还包括，volatile,显示锁以及原子变量-----
 * 在实际运用中通常将多线程访问的共享变量使用面向对象的技术封装成线性安全类（有些时候也可以将状态保存
 * 在公开域中，或者是提供一个对内部对象的公开引用）
 * *************************************************************
 * 在该类中访问的临时变量都存储在线程栈的局部变量中，线程之间没有共享的变量 
 */
@ThreadSafe
public class StatelessFactorizer extends GenericServlet implements Servlet {

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }
    //使用default 表示可以在同类或者自同包中可以使用，而 protect 可以在不同包中的子类中可以引用
    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }
}
