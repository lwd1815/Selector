package com.deepbaytech.deeplibrary;

import android.app.Activity;
import android.content.Context;
import java.util.Stack;

/**
 * @author EtherealPatrick
 *         Created at 2016/8/12.
 * @email yinghengsun@deepbaytech.com
 */
public class ActivityManager {
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;
    private static Stack<Activity> waredetailStack;
    static {
        instance = new ActivityManager();
    }

    public static ActivityManager getInstance() {
        return instance;
    }

    public void addDetailActivity(Activity activity){
        if(waredetailStack==null){
            waredetailStack=new Stack<>();
        }
        waredetailStack.add(activity);
    }

    public Stack<Activity> getWaredetailStack(){
        return waredetailStack;
    }

    /**
     * 添加指定Activity到堆栈
     */
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    /**
     * 获取当前Activity
     */
    public Activity currentActivity(){
        Activity activity=activityStack.lastElement();
        return activity;
    }
    /**
     * 结束当前Activity
     */
    public void finishActivity(){
        Activity activity=activityStack.lastElement();
        finishActivity(activity);
    }
    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity){
        if(activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }
    }
    /**
     * 结束指定Class的Activity
     */
    public void finishActivity(Class<?> cls){
        for (Activity activity : activityStack) {
            if(activity.getClass().equals(cls) ){
                finishActivity(activity);
                return;
            }
        }
    }

    /**
     * 结束全部的Activity
     */
    public void finishAllActivity(){
        for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
//            android.app.ActivityManager activityMgr= (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//            activityMgr.restartPackage(context.getPackageName());
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) { }
    }
}
