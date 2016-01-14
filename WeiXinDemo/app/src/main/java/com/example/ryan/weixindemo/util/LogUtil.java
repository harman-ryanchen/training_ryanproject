package com.example.ryan.weixindemo.util;

import android.os.Environment;
import android.util.Log;


import com.example.ryan.weixindemo.BuildConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LogUtil {
    public static final boolean IS_SAVE_LOG = false;
    private static boolean IS_FIRST = true;
    private static final String TAG = "HKConnect";

    public static String fileDir = getSDCardPath() + "/harmanlog/";
    public static String filePath = fileDir + "log.txt";
    private static FileOutputStream fileOutputStream;

    private static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static void deleteLog() {
        File f = new File(filePath);
        f.delete();
    }

    private static String getSDCardPath() {
        String path = null;
        if (hasSDCard()) {
            path = Environment.getExternalStorageDirectory().getPath();
        }
        return path;
    }

    public static void l(Object msg) {
        if (BuildConfig.DEBUG) {
            String vString = "";
            if (msg != null) {
                vString = formatStackTraceElement() + "==>" + msg.toString();
            } else {
                vString = formatStackTraceElement() + "==>" + "Null";
            }
            Log.e(TAG, vString);
            outputToFile(vString);
        }
    }

    public static void l() {
        if (BuildConfig.DEBUG) {
            String vString = formatStackTraceElement() + "==";
            Log.e(TAG, vString);
            outputToFile(vString);
        }
    }

    public static void l(String string, String msg) {
        if (BuildConfig.DEBUG) {
            String vString = formatStackTraceElement() + "==>" + msg.toString();
            Log.e(string, vString);
            outputToFile(vString);
        }
    }

    public static void s(String msg) {
        if (BuildConfig.DEBUG) {
            String vString = formatStackTraceElement() + "==>" + msg.toString();
            System.out.println(vString);
            outputToFile(vString);
        }
    }

    public static void lHex(String name, byte[] buf) {
        if (BuildConfig.DEBUG) {
            StackTraceElement st[] = Thread.currentThread().getStackTrace();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; buf != null && i < buf.length; i++) {
                String hex = Integer.toHexString(buf[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
                sb.append(",");
            }
            Log.e("communicate", formatStackTraceElement(st[3]) + name + "--->>" + sb);
        } else {
            return;
        }
    }

    public static void l(byte[] buf) {
        if (BuildConfig.DEBUG) {
            StackTraceElement st[] = Thread.currentThread().getStackTrace();
            StringBuffer sb = new StringBuffer();
            if (buf == null) {
                sb.append("null");
            } else {
                for (int i = 0; i < buf.length; i++) {
                    sb.append(buf[i]);
                    sb.append("-");
                }
            }
            Log.e(TAG, formatStackTraceElement(st[3]) + "--->>" + sb);
        } else {
            return;
        }
    }

    public static void outputToFile(String str) {
        if (BuildConfig.DEBUG && IS_SAVE_LOG && fileDir != null) {
            if (str != null) {
                str = getCurrentTime() + "------" + str;
                try {
                    File f = new File(fileDir);
                    if (!f.exists()) {
                        f.mkdir();
                    }
                    File f2 = new File(filePath);
                    if (!f2.exists()) {
                        f2.createNewFile();
                    }
                    if (fileOutputStream == null) {
                        fileOutputStream = new FileOutputStream(f2, true);
                    }
                    if (IS_FIRST) {
                        IS_FIRST = false;
                        fileOutputStream.write(("============================================================================\n").getBytes());
                        fileOutputStream.write(("============================================================================\n").getBytes());
                        fileOutputStream.write(("============================================================================\n").getBytes());
                    }

                    fileOutputStream.write((str + "\n").getBytes());
//					fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getCurrentTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        String str = format.format(new Date(time));
        String timeString = time + "";
        String millisecond = timeString.substring(timeString.length() - 3);
        return str + ":" + millisecond;
    }

    public static void logStack() {
        if (BuildConfig.DEBUG) {
            StackTraceElement st[] = Thread.currentThread().getStackTrace();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < st.length; i++) {
                StackTraceElement vElement = st[i];
                sb.append(formatStackTraceElement(vElement));
                sb.append("\n");
            }

            Log.e(TAG, sb.toString());
            outputToFile(sb.toString());
        }
    }

    public static String formatThrowableToString(Throwable t) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                t.printStackTrace(new PrintStream(baos));
            } finally {
                baos.close();
            }
            return baos.toString();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";

    }

    public static void l(Throwable t) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                t.printStackTrace(new PrintStream(baos));
            } finally {
                baos.close();
            }
            outputToFile(baos.toString());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * Logs to the android logger at VERBOSE level.
     * No-operation if BuildConfig.DEBUG is false (See logging flavor dimension in build.gradle)
     *
     * @param formatString printf style string used by String#format. Use %s for each argument for simplicity.
     * @param formatArgs   arguments that are used as replacements for formatString. If the last argument of this list
     *                     is a Throwable then do not include a placeholder for it in the formatString to have the
     *                     stack trace output.
     */
    public static void v(Object formatString, Object... formatArgs) {
        if (BuildConfig.DEBUG) {
            androidUtilLog(Log.VERBOSE, String.valueOf(formatString), formatArgs);
        }
    }

    /**
     * Logs to the android logger at DEBUG level.
     * No-operation if BuildConfig.DEBUG is false (See logging flavor dimension in build.gradle)
     *
     * @param formatString printf style string used by String#format. Use %s for each argument for simplicity.
     * @param formatArgs   arguments that are used as replacements for formatString. If the last argument of this list
     *                     is a Throwable then do not include a placeholder for it in the formatString to have the
     *                     stack trace output.
     */
    public static void d(Object formatString, Object... formatArgs) {
        if (BuildConfig.DEBUG) {
            androidUtilLog(Log.DEBUG, String.valueOf(formatString), formatArgs);
        }
    }

    /**
     * Logs to the android logger at INFO level.
     * Logs regardless of BuildConfig.DEBUG value.
     *
     * @param formatString printf style string used by String#format. Use %s for each argument for simplicity.
     * @param formatArgs   arguments that are used as replacements for formatString. If the last argument of this list
     *                     is a Throwable then do not include a placeholder for it in the formatString to have the
     *                     stack trace output.
     */
    public static void i(Object formatString, Object... formatArgs) {
        androidUtilLog(Log.INFO, String.valueOf(formatString), formatArgs);
    }

    /**
     * Logs to the android logger at WARN level.
     * Logs regardless of BuildConfig.DEBUG value.
     *
     * @param formatString printf style string used by String#format. Use %s for each argument for simplicity.
     * @param formatArgs   arguments that are used as replacements for formatString. If the last argument of this list
     *                     is a Throwable then do not include a placeholder for it in the formatString to have the
     *                     stack trace output.
     */
    public static void w(Object formatString, Object... formatArgs) {
        androidUtilLog(Log.WARN, String.valueOf(formatString), formatArgs);
    }

    /**
     * Logs to the android logger at ERROR level.
     * Logs regardless of BuildConfig.DEBUG value.
     *
     * @param formatString printf style string used by String#format. Use %s for each argument for simplicity.
     * @param formatArgs   arguments that are used as replacements for formatString. If the last argument of this list
     *                     is a Throwable then do not include a placeholder for it in the formatString to have the
     *                     stack trace output.
     */
    public static void e(Object formatString, Object... formatArgs) {
        androidUtilLog(Log.ERROR, String.valueOf(formatString), formatArgs);
    }

    private static void androidUtilLog(int priority, String formatString, Object... formatArgs) {
        StringBuilder msg = new StringBuilder();
        if (BuildConfig.DEBUG) {
            StackTraceElement[] stack = Thread.currentThread().getStackTrace();
            if (stack != null && stack.length >= 5) {
                msg.append(formatStackTraceElement(stack[4])).append("==>");
            }
        }
        Throwable ex = null;
        if (formatArgs != null
                && formatArgs.length > 0
                && formatArgs[formatArgs.length - 1] instanceof Throwable) {
            ex = (Throwable) formatArgs[formatArgs.length - 1];
            formatArgs = Arrays.copyOfRange(formatArgs, 0, formatArgs.length - 1);
        }
//        if (StringUtils.countMatches(formatString, "%s") != formatArgs.length) {
//            // Show entire message if %s is forgotten
//            msg.append(String.valueOf(formatString)).append(' ').append(Arrays.toString(formatArgs));
//        } else {
            try {
                msg.append(String.format(String.valueOf(formatString), formatArgs));
            } catch (RuntimeException formatException) {
                msg.append(String.valueOf(formatString)).append(Arrays.toString(formatArgs));
            }
//        }

        if (ex != null) {
            msg.append("\n").append(Log.getStackTraceString(ex));
        }
        Log.println(priority, TAG, msg.toString());
    }

    private static String formatStackTraceElement(StackTraceElement ste) {
        StringBuffer sb = new StringBuffer();
        if (BuildConfig.DEBUG) {
            String fileName = ste.getFileName();
            if (fileName != null) {
                sb.append(fileName.replace(".java", ""));
            }
        }
        else {
            // Obfuscated via proguard renamesourcefileattribute, use class name that can be mapped.
            sb.append(ste.getClassName());
        }
        sb.append(".");
        sb.append(ste.getMethodName());
        sb.append("(");
        sb.append(ste.getLineNumber());
        sb.append(")");
        sb.append(" thread " + Thread.currentThread().getId() + " ");
        return sb.toString();
    }

    private static String formatStackTraceElement() {
        StackTraceElement st[] = Thread.currentThread().getStackTrace();
        return formatStackTraceElement(st[4]);
    }

    public static void ld() {
        if (BuildConfig.DEBUG) {
            String vString = formatStackTraceElement() + "==";
            Log.e(TAG, vString);
            outputToFile(vString);
        }
    }

    public static void ld(Object msg) {
        if (BuildConfig.DEBUG) {
            String vString = "";
            if (msg != null) {
                vString = formatStackTraceElement() + "==>" + msg.toString();
            } else {
                vString = formatStackTraceElement() + "==>" + "Null";
            }
            Log.e(TAG, vString);
            outputToFile(vString);
        }
    }
}
