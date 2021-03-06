/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.wittyneko.utils;

import android.util.Log;

/**
 * Log工具类
 * @author wittyneko
 * @since 2017/4/18
 */
public class LogUtil {

    public static String tagPrefix = "tutu";
    private static boolean isDebug = true;
    //private static  String space = "\r\n-------------------------------------------\r\n";
    private static  String space = "\r\n";

    private LogUtil() {
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        LogUtil.isDebug = isDebug;
    }

    private static String generateTag() {
        //String tag = getTrace(3);
        //tag = TextUtils.isEmpty(tagPrefix) ? tag : tagPrefix + ":" + tag;
        //return tag;
        return tagPrefix;
    }

    private static String getTrace() {
        return getTrace(3);
    }

    private static String getTrace(int index){
        StackTraceElement caller = new Throwable().getStackTrace()[index];
        String tag = "%s.%s(%s:%d)";
        String callerClazzName = caller.getClassName();
        //callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getFileName(), caller.getLineNumber());
        return tag;
    }

    public static void d(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.d(tag, getTrace() + space +content);
    }

    public static void d(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.d(tag, getTrace() + space +content, tr);
    }

    public static void e(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.e(tag, getTrace() + space +content);
    }

    public static void e(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.e(tag, getTrace() + space +content, tr);
    }

    public static void i(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.i(tag, getTrace() + space +content);
    }

    public static void i(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.i(tag, getTrace() + space +content, tr);
    }

    public static void v(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.v(tag, getTrace() + space +content);
    }

    public static void v(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.v(tag, getTrace() + space +content, tr);
    }

    public static void w(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.w(tag, getTrace() + space +content);
    }

    public static void w(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.w(tag, getTrace() + space +content, tr);
    }

    public static void w(Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        //Log.w(tag, getTrace() + space +tr);
        Log.w(tag, getTrace() + space, tr);
    }


    public static void wtf(String content) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.wtf(tag, getTrace() + space +content);
    }

    public static void wtf(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.wtf(tag, getTrace() + space +content, tr);
    }

    public static void wtf(Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        //Log.wtf(tag, getTrace() + space +tr);
        Log.wtf(tag, getTrace() + space, tr);
    }

}
