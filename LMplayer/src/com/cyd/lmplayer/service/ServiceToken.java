
package com.cyd.lmplayer.service;

import android.content.ContextWrapper;
/**
 * @author 陈雅迪 
 * @function 创建context对象，用到service
 */
public class ServiceToken {
    public ContextWrapper mWrappedContext;
    //对context类的一种包装，构造函数包含了一个真正的Context引用，即contextlml
    public ServiceToken(ContextWrapper context) {
        mWrappedContext = context;
    }
}
