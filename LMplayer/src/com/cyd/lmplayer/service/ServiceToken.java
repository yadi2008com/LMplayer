
package com.cyd.lmplayer.service;

import android.content.ContextWrapper;
/**
 * @author ���ŵ� 
 * @function ����context�����õ�service
 */
public class ServiceToken {
    public ContextWrapper mWrappedContext;
    //��context���һ�ְ�װ�����캯��������һ��������Context���ã���contextlml
    public ServiceToken(ContextWrapper context) {
        mWrappedContext = context;
    }
}
