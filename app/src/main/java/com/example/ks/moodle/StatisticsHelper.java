package com.example.ks.moodle;

import android.content.Context;
import android.content.Intent;

import com.example.ks.moodle.db.dao.DBManager;
import com.example.ks.moodle.service.UploadService;

import java.util.Map;

public class StatisticsHelper {
    public static boolean isDebug=true;
    public static long indextime;
    public static StatisticsHelper instance=null;

    public static StatisticsHelper getInstance(){
        if(instance==null){
            instance=new StatisticsHelper();
        }
        return instance;
    }

    /**
     * 初始化统计
     * @param context
     */
    public void initStatistics(Context context){
        indextime=System.currentTimeMillis();
        Intent intent=new Intent(context, UploadService.class);
        context.startService(intent);
    }

    /**
     * 关闭统计，目的在于关闭数据库
     * @param context
     */
    public void closeStatistics(Context context){
        DBManager.getInstance(context).closeDatabase();
    }

    /**
     * 添加统计事件
     * @param context
     * @param eventId
     * @param map
     */

    public void AddEvent(Context context,String eventId,String name,String xuehao,String eventName,Map<String,Object> map){
        new Thread(new AddEventThread(context,eventId,name,xuehao,eventName,map)).start();


    }
}
