
package com.thk.thkvisitor.utils;

import android.util.Log;

/**
 * 日志
 *
 * @author liu
 */
public final class LogHelper {

    private static boolean LOGDBUG = false;

    /** 日志级别 显示级别参考 android.util.Log的级别 配置0全部显示，配置大于7全不显示 */
    public static final int LEVLE = 2;

    public static void v(String tag, String msg) {
        if (LOGDBUG) {
            if (LEVLE <= Log.VERBOSE) {
                Log.v(tag, msg);
            }

        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (LOGDBUG) {
            if (LEVLE <= Log.VERBOSE) {
                Log.v(tag, msg, tr);
            }
        }
    }

    public static void d(String tag, String msg) {
        if (LOGDBUG) {
            if (LEVLE <= Log.VERBOSE) {
                Log.v(tag, msg);
            }

        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (LOGDBUG) {
            if (LEVLE <= Log.DEBUG) {
                Log.d(tag, msg, tr);
            }
        }
    }

    public static void i(String tag, String msg) {
        if (LOGDBUG) {
            if (LEVLE <= Log.INFO) {
                LogHelper.d(tag, msg);
            }
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (LOGDBUG) {
            if (LEVLE <= Log.INFO) {
                LogHelper.d(tag, msg, tr);
            }
        }
    }

    public static void w(String tag, String msg) {
        if (LOGDBUG) {
            if (LEVLE <= Log.WARN) {
                Log.w(tag, msg);
            }
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (LOGDBUG) {
            if (LEVLE <= Log.WARN) {
                Log.w(tag, msg, tr);
            }
        }
    }

    public static void w(String tag, Throwable tr) {
        if (LOGDBUG) {

            if (LEVLE <= Log.WARN) {
                Log.w(tag, tr.getMessage(), tr);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (LOGDBUG) {
            if (LEVLE <= Log.ERROR) {
                Log.e(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (LOGDBUG) {
            if (LEVLE <= Log.ERROR) {
                Log.e(tag, msg, tr);
            }
        }
    }

    public static void e(String tag, Throwable tr) {
        if (LOGDBUG) {
            if (LEVLE <= Log.ERROR) {
                Log.e(tag, tr.getMessage(), tr);
            }
        }
    }

    /**
     * getter method
     *
     * @return the lOGDBUG
     */

    public static boolean isLOGDBUG() {
        return LOGDBUG;
    }

    /**
     * setter method
     *
     * @param lOGDBUG the lOGDBUG to set
     */

    public static void setLOGDBUG(boolean lOGDBUG) {
        LOGDBUG = lOGDBUG;
    }
}
