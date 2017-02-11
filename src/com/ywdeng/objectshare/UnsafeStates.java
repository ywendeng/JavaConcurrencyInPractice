package com.ywdeng.objectshare;

/**
 * UnsafeStates
 * <p/>
 * Allowing internal mutable state to escape
 *
 * @author Brian Goetz and Tim Peierls
 */
class UnsafeStates {
    private String[] states = new String[]{
        "AK", "AL" /*...*/
    };
    /**
     * 将对象的引用作为非私有方法的返回值俩将本来不应该发布的可变对象发布了
     * 导致了逸出，使得私有的可变变量逸出了它的作用域
     * @return
     */
    public String[] getStates() {
        return states;
    }
}
