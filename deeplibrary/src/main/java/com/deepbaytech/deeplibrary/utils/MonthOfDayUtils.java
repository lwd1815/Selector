package com.deepbaytech.deeplibrary.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by x on 2016/9/22.
 */

public class MonthOfDayUtils {
    // TODO: 2016/9/22 根据月份获取该月一共有多少天
    public static int byMonthToDay(int month){
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return getYear();
        }
        return 0;
    }

    // TODO: 2016/9/22 判断今年是否是闰年，并获取其中二月的天数
    private static int getYear(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        if (year%100==0){
            if (year%400==0){
                return 29;
            }else{
                return 28;
            }
        }
        if (year%4==0){
            return 29;
        }else
            return 28;
    }

    /**
     *
     * @param startMonth 开始的月份
     * @param startDay 开始的日期
     * @param endMonth 结束的月份
     * @param endDay    结束的日期
     * @return 返回这段时间内一共有多少天
     */

    // TODO: 2016/9/22 获取时间段中一共有多少天 
    public static int getAllMonthDay(int startMonth, int startDay, int endMonth, int endDay) {
        int allDay = 0;
        if (endMonth < startMonth) {
            for (int i = startMonth; i <= 12; i++) {
                allDay = allDay+byMonthToDay(i);
            }
            allDay = allDay-startDay;
            int i = 1;
            while(i<endMonth){
                allDay = allDay+byMonthToDay(i);
                i++;
            }
            allDay = allDay+endDay;
        }
        if (startMonth==endMonth){
            allDay = endDay-startDay;
        }
        if (startMonth<endMonth){
            for (int i = startMonth;i<endMonth;i++){
                allDay = allDay+byMonthToDay(i);
            }
            allDay = allDay-startDay+endDay;
        }
        return allDay;
    }

    /**
     * 余数如何处理
     * @param startMonth 开始的月份
     * @param startDay  开始的日期
     * @param endMonth  结束的月份
     * @param endDay    结束的日期
     * @param intervalNum 一共需要分为多少段
     * @return
     */
    public static List<String> getInterval(int startMonth, int startDay, int endMonth, int endDay, int intervalNum){
        List<String> list = new ArrayList<>();
        // TODO: 2016/9/26 先获取到该段时间内一共有多少天
        int allDays = getAllMonthDay(startMonth, startDay, endMonth, endDay);
        if (intervalNum>allDays){
            return null;
        }
        //取时间间隔为多少天，判断余数，四舍五入
        int interval = (int) Math.round(allDays * 1.0 / intervalNum);
        // TODO: 2016/9/26 当这段时间属于两年的
        if (endMonth < startMonth) {
            // TODO: 2016/9/26 先计算第一年中的时间段
            for (int i = startMonth; i <= 12; i++) {
                list.add(i+"-"+startDay);
                int monthDay = byMonthToDay(i);
                int surplusDay = monthDay-startDay;
                //该月剩余天数是否大于时间间隔
                if (surplusDay>=interval){
                    int nextMonthStartDay = surplusDay%interval;
                    int surplusDayTimes = surplusDay/interval;
                    for (int y = 1;y<=surplusDayTimes;y++){
                        list.add(i+"-"+(y*interval+startDay));
                    }
                    if (nextMonthStartDay==0){
                        startDay = interval;
                    }else
                        startDay = interval-nextMonthStartDay;
                }else{
                    startDay = interval-surplusDay;
                }
            }
            int i = 1;
            // TODO: 2016/9/26 计算第二年的时间段
            while(i<endMonth){
                list.add(i+"-"+startDay);
                int monthDay = byMonthToDay(i);
                int surplusDay = monthDay-startDay;
                //该月剩余天数是否大于时间间隔
                if (surplusDay>=interval){
                    int nextMonthStartDay = surplusDay%interval;
                    int surplusDayTimes = surplusDay/interval;
                    for (int y = 1;y<=surplusDayTimes;y++){
                        list.add(i+"-"+(y*interval+startDay));
                    }
                    if (nextMonthStartDay==0){
                        startDay = interval;
                    }else
                        startDay = interval-nextMonthStartDay;
                }else{
                    startDay = interval-surplusDay;
                }
                i++;
            }
            // TODO: 2016/9/26 判断最后一个月的时间段
            // TODO: 2016/9/26 当上个月到最后一个月中剩余的时间大于最后的日期时
            if (endDay<=startDay){
                list.add(endMonth + "-" + endDay);
            }else{
                int endInterval = endDay-startDay;
                list.add(endMonth + "-" + startDay);
                if (endInterval<=interval){
                    list.add(endMonth + "-" + endDay);
                }else{
                    int endSurplus = endInterval/interval;
                    int endSurplusDay = endInterval%interval;
                    for (int y = 1;y <= endSurplus;y++){
                        list.add(endMonth+"-"+(startDay+y*interval));
                    }
                    if (endSurplusDay>0){
                        list.add(endMonth+"-"+endDay);
                    }
                }
            }
        }
        // TODO: 2016/9/26 当开始的月份和结束的月份属于同一个月时
        if (startMonth==endMonth){
            list.add(startMonth+"-"+startDay);
            int num = allDays/interval;
            for (int i = 1;i<=num;i++){
                list.add(startMonth+"-"+(startDay+i*interval));
            }
            if (allDays%interval>0){
                list.add(endMonth+"-"+endDay);
            }
        }
        // TODO: 2016/9/26 当时间段属于同一年时
        if (startMonth<endMonth){
            // TODO: 2016/9/26 计算开始月份和日期到结束月份中的时间段
            for (int i = startMonth;i<endMonth;i++){
                    list.add(i+"-"+startDay);
                    int monthDay = byMonthToDay(i);
                    int surplusDay = monthDay-startDay;
                    //该月剩余天数是否大于时间间隔
                    if (surplusDay>=interval){
                        int nextMonthStartDay = surplusDay%interval;
                        int surplusDayTimes = surplusDay/interval;
                        for (int y = 1;y<=surplusDayTimes;y++){
                            list.add(i+"-"+(y*interval+startDay));
                        }
                        if (nextMonthStartDay==0){
                            startDay = interval;
                        }else{
                            startDay = interval-nextMonthStartDay;
                        }
                }else{
                        startDay = interval-surplusDay;
                    }
            }
            // TODO: 2016/9/26 计算最后一个月份的时间段
            if (endDay<=startDay){
                list.add(endMonth + "-" + endDay);
            }else{
                int endInterval = endDay-startDay;
                list.add(endMonth + "-" + startDay);
                if (endInterval<=interval){
                    list.add(endMonth + "-" + endDay);
                }else{
                    int endSurplus = endInterval/interval;
                    int endSurplusDay = endInterval%interval;
                    for (int y = 1;y <= endSurplus;y++){
                        list.add(endMonth+"-"+(startDay+y*interval));
                    }
                    if (endSurplusDay>0){
                        list.add(endMonth+"-"+endDay);
                    }
                }
            }
        }
        return list;
    }
}
