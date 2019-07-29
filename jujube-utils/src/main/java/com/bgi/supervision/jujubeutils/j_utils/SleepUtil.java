package com.bgi.supervision.jujubeutils.j_utils;

/**
 * Created by majian
 * on 2019/4/25
 * copyright:BGI
 * Describe:
 */
public class SleepUtil {

    public static final long DEFAULT_SLEEP_TIME = 500;

    private boolean isRuning = false;

    public boolean isRuning() {
        return isRuning;
    }

    public void runWithTime(final long defaultSleepTime) {
        isRuning = true;
        new Thread() {

            @Override
            public void run() {
                try {
                    sleep(defaultSleepTime, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isRuning = false;
                super.run();
            }
        }.start();
    }
}
