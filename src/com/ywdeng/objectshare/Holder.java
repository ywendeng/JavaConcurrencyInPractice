package com.ywdeng.objectshare;

/**
 * Holder
 * <p/>
 * Class at risk of failure if not properly published
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Holder {
	// 没有使用同步来确保Holder对象对其它线性的可见性，因此将Holder 称为"未被正确发布"
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n)
            throw new AssertionError("This statement is false.");
    }
    
}
