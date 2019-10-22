package com.luo.lang;

/**
 * Class {@code ArchObject} is the root of the class hierarchy.
 * Every class has {@code ArchObject} as a superclass. All objects,
 * including arrays, implement the methods of this class.
 * @see     java.lang.Class
 * @since JDK1.0
 * @author luoxuzheng
 * @create 2019-10-22 8:52
 **/
public class ArchObject {

    private static native void registerNatives();
    static {
        registerNatives();
    }

    /**
     * 等价于
     * @see Object#getClass
     * @return
     */
    public final Class<?> getArchClass(){
        return super.getClass();
    }

    /**
     * 等价于
     * @see Object#hashCode
     * @return
     */
    public native int hashCode();


    /**
     *  对任何 non-null
     *  反射性 自身与自身 返回true
     *  对称性 {@code x} and {@code y}, {@code x.equals(y)}
     *  should return {@code true} if and only if
     *  传递性  a与b为true，b与c为true，则a与c也为true
     *  一致性  没有修改比较中的信息，则保持比较结果不会改变
     *  For any non-null reference value {@code x},
     *  {@code x.equals(null)} should return {@code false}.
     * @param obj
     * @return
     */
    public boolean equals(Object obj) {
        return (this == obj);
    }

    /**
     * @return     a clone of this instance.
     * @throws  CloneNotSupportedException  if the object's class does not
     *               support the {@code Cloneable} interface. Subclasses
     *               that override the {@code clone} method can also
     *               throw this exception to indicate that an instance cannot
     *               be cloned.
     * @see java.lang.Cloneable
     */
    protected native Object clone() throws CloneNotSupportedException;

    /**
     * 转换成string
     * @return
     */
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * 唤醒阻塞的线程
     */
    public void archNotify(){
        super.notify();
    };

    /**
     * 唤醒阻塞的所有线程
     */
    public void archNotifyAll(){
        super.notifyAll();
    };

    /**
     * 阻塞线程
     * @param timeout
     * @throws InterruptedException
     */
    public void archWait(long timeout) throws InterruptedException{
        super.wait(timeout);
    }

    /**
     * @see Object#wait(long, int)
     *
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     * @param      timeout   the maximum time to wait in milliseconds.
     * @param      nanos      additional time, in nanoseconds range
     *                       0-999999.
     * @throws  IllegalArgumentException      if the value of timeout is
     *                      negative or the value of nanos is
     *                      not in the range 0-999999.
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     */
    public void archWait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                    "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }

        archWait(timeout);
    }

    /**
     * @see Object#wait()
     * @throws InterruptedException
     */
    public void archWait() throws InterruptedException {
        archWait(0);
    }

    /**
     * 只調用一次
     * Called by the garbage collector on an object when garbage collection
     * determines that there are no more references to the object.
     * A subclass overrides the {@code finalize} method to dispose（处置） of
     * system resources or to perform other cleanup.
     * @throws Throwable
     */
    protected void finalize() throws Throwable { }
}
