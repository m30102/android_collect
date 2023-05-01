package com.fan.collect.module.jsbridge.api.report;

import android.content.Context;
import java.util.Properties;

public interface IWebContainerReporter {


    /**
     * 自定义时长事件，此类事件通常用于统计次数
     *
     * @param ctx             设备上下文，不能为null
     * @param intervalSeconds 事件耗时，单位由业务约定
     * @param eventId         自定义事件的id，不能为null或""
     * @param properties      String类型的key-Value对
     */
    public int trackCustomKVTimeIntervalEvent(Context ctx, int intervalSeconds, String eventId, Properties properties);

    /**
     * 开始时长统计，进入页⾯时调用，通常为Activity.onResume()
     *
     * @param pageName
     */
    public void trackBeginPage(String pageName);
    /**
     * 设置用户自定义的user id
     *
     * @param customUserId 用户自定义的user id
     */
    public void setCustomUserId(String customUserId);
}
