package com.infelt.schedule.aspect;

import android.util.Log;


import com.infelt.schedule.Utils.ThreadUtils;
import com.infelt.schedule.annotation.InSchedule;
import com.infelt.schedule.annotation.InScheduleInit;
import com.infelt.schedule.annotation.InScheduleType;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class InThreadAspect {

    private static final String TAG = "InThreadAspect";

    private final String pointcutRunAble
            = "execution(@com.infelt.schedule.annotation.InSchedule * *(..)) && @annotation(inSchedule)";

    @Pointcut(value = pointcutRunAble, argNames = "inSchedule")
    public void startRunAble(InSchedule inSchedule) {
        Log.d(TAG, "startRunAble ");
    }

    @Around("startRunAble(inSchedule)")
    public void doRunAble(final ProceedingJoinPoint joinPoint, InSchedule inSchedule) {
        if (inSchedule.value() == InScheduleType.TYPE_MAIN) {
            ThreadUtils.getThreadUtils().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    try {
                        joinPoint.proceed();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            });
        } else if (inSchedule.value() == InScheduleType.TYPE_IO) {
            ThreadUtils.getThreadUtils().getThreadPoolExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        joinPoint.proceed();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            });
        }
    }

    private final String pointcutInit
            = "execution(@com.infelt.schedule.annotation.InScheduleInit * *(..)) && @annotation(inScheduleInit)";

    @Pointcut(value = pointcutInit, argNames = "inScheduleInit")
    public void init(InScheduleInit inScheduleInit) {
        Log.d(TAG, "InSchedule ");
    }

    @Around("init(inScheduleInit)")
    public void doInit(final ProceedingJoinPoint joinPoint, InScheduleInit inScheduleInit) {
        ThreadUtils.getThreadUtils().init();
    }


}
