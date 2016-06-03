//package com.changingedu.dev.rxdemo;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.concurrent.ConcurrentHashMap;
//
//import android.os.Handler;
//import android.os.HandlerThread;
//import android.os.Looper;
//
//import com.changingedu.dev.util.Logger;
//
///**
// * Created by Wangxiaxin on 2015/8/26.
// *
// * 倒计时任务处理中心 倒计时以秒为单位
// */
//public final class CountDownCenter {
//
//    /**
//     * 倒计时任务的监听接口
//     * */
//    public interface CountDownListener {
//        void onCountDown(String tag, int leftCount);
//    }
//
//    private class CountDownTask {
//        CountDownListener listener;
//        int count;
//        int oriCount;
//        boolean isRepeat;// 是否是循环任务
//    }
//
//    private static CountDownCenter sInstance;
//    private ConcurrentHashMap<String, CountDownTask> mCountDownTaskMap;
//    private boolean mIsRunning = false;
//    private HandlerThread mTaskThread;
//
//    private Handler mCountDownHandler;
//    private Handler mNotifyHandler;
//    private final CountDownRun mCountDownRun = new CountDownRun();
//
//    private static final String TAG = "CountDownCenter";
//
//    public static CountDownCenter INSTANCE() {
//        if (sInstance == null) {
//            synchronized (CountDownCenter.class) {
//                if (sInstance == null) {
//                    sInstance = new CountDownCenter();
//                }
//            }
//        }
//        return sInstance;
//    }
//
//    private CountDownCenter() {
//        mTaskThread = new HandlerThread(TAG);
//        mTaskThread.start();
//        mCountDownHandler = new Handler(mTaskThread.getLooper());
//        mNotifyHandler = new Handler(Looper.getMainLooper());
//        mCountDownTaskMap = new ConcurrentHashMap<String, CountDownTask>();
//
//    }
//
//    public void addSystemTask(Runnable r, long delay) {
//        if (r == null)
//            return;
//
//        mCountDownHandler.postDelayed(r, delay);
//    }
//
//    class CountDownRun implements Runnable {
//        public boolean isAuto = true;
//
//        @Override
//        public void run() {
//            updateTask();
//            if (isAuto)
//                mCountDownHandler.postDelayed(this, 1000);
//        }
//    }
//
//    /** 判断当前tag是否处于倒计时中 */
//    public boolean isDuringCountDown(String tag) {
//        CountDownTask task = mCountDownTaskMap.get(tag);
//        return (task != null && task.count > 0);
//    }
//
//    /**
//     *
//     * 获取指定tag的倒计时任务的剩余秒数
//     * */
//    public synchronized int getLeftCount(String tag) {
//        if (mCountDownTaskMap.containsKey(tag)) {
//            return mCountDownTaskMap.get(tag).count;
//        }
//
//        return 0;
//    }
//
//    /**
//     * 增加一个倒计时任务
//     *
//     */
//    public void addTask(String tag, int initCount, CountDownListener listener) {
//        addTask(tag, initCount, listener, false);
//    }
//
//    /**
//     * 增加一个倒计时任务
//     *
//     * @param forceReset
//     *            是否需要强制重置
//     * @param tag
//     *            任务的名称（保证全局唯一）
//     * @param initCount
//     *            初始倒计时数
//     * @param listener
//     *            倒计时监听
//     *
//     * */
//    public void addTask(String tag, int initCount, CountDownListener listener,
//                        boolean forceReset) {
//        addTask(tag, initCount, listener, forceReset, false);
//    }
//
//    public void addRepeatTask(String tag, int interval, CountDownListener listener) {
//        addTask(tag, interval, listener, false, true);
//    }
//
//    public void addTask(String tag, int initCount, CountDownListener listener,
//                        boolean forceReset, boolean isRepeat) {
//
//        Logger.v(TAG, "addTask:  tag=" + tag + "  initSeconds=" + initCount
//                + "  listener=" + listener + "  reset=" + forceReset);
//
//        int count = initCount;
//
//        if (mCountDownTaskMap.containsKey(tag) && mCountDownTaskMap.get(tag) != null) {
//            Logger.v(TAG, "addTask:  update task : tag=" + tag);
//            CountDownTask task = mCountDownTaskMap.get(tag);
//            if (forceReset) {
//                task.oriCount = task.count = initCount;
//            }
//            else {
//                count = task.count;
//            }
//
//            task.listener = listener;
//        }
//        else {
//            Logger.v(TAG, "addTask:  create task : tag=" + tag);
//            CountDownTask task = new CountDownTask();
//            task.oriCount = task.count = initCount;
//            task.listener = listener;
//            task.isRepeat = isRepeat;
//            mCountDownTaskMap.put(tag, task);
//        }
//
//        if (listener != null) {
//            listener.onCountDown(tag, count);
//        }
//
//        tryStart();
//    }
//
//    /**
//     * 取消一个定时任务
//     *
//     * @param tag
//     *            任务的名称（保证全局唯一）
//     *
//     * */
//    public void cancelTask(String tag) {
//        Logger.v(TAG, "cancelTask:  tag=" + tag);
//        if (mCountDownTaskMap.containsKey(tag)) {
//            Logger.v(TAG, "cancelTask:  tag=" + tag + " ok");
//            mCountDownTaskMap.remove(tag);
//        }
//
//        tryStop();
//    }
//
//    /**
//     * 取消指定倒计时任务的监听
//     *
//     * @param tag
//     *            任务的tag
//     * */
//    public void cancelTaskListen(String tag) {
//        if (mCountDownTaskMap.containsKey(tag)) {
//            CountDownTask task = mCountDownTaskMap.get(tag);
//            task.listener = null;
//        }
//    }
//
//    /**
//     * 更新一个已存在的倒计时任务的监听
//     *
//     * @param tag
//     *            任务的tag
//     * @param listener
//     *            想要设置的监听
//     * */
//    public void setTaskListener(String tag, CountDownListener listener) {
//        if (mCountDownTaskMap.containsKey(tag)) {
//            CountDownTask task = mCountDownTaskMap.get(tag);
//            task.listener = listener;
//        }
//    }
//
//    /**
//     * 更新任务状态，上层请不要调用
//     *
//     * */
//    public void updateTask() {
//
//        synchronized (this) {
//            // Logger.v(TAG, "-------------------update------------------");
//            boolean needTryStop = false;
//
//            Iterator<HashMap.Entry<String, CountDownTask>> iter = mCountDownTaskMap
//                    .entrySet().iterator();
//            while (iter.hasNext()) {
//                HashMap.Entry<String, CountDownTask> entry = iter.next();
//                final String tag = entry.getKey();
//                final CountDownTask task = entry.getValue();
//                --task.count;
//                // Logger.v(TAG, " update : matchid = " + tag + "  count=" +
//                // task.count);
//                if (task.count < 0) {
//                    if (task.isRepeat) {
//                        task.count = task.oriCount;
//                    }
//                    else {
//                        iter.remove();
//                        needTryStop = true;
//                    }
//                }
//                else {
//                    if (task.listener != null) {
//
//                        mNotifyHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    task.listener.onCountDown(tag, task.count);
//                                } catch (Exception e) {
//                                    Logger.w(TAG, e);
//                                }
//                            }
//                        });
//                    }
//                }
//            }
//
//            if (needTryStop) {
//                tryStop();
//            }
//        }
//    }
//
//    /**
//     * 通知宿主，停止倒计时
//     * */
//    private void tryStop() {
//
//        Logger.v(TAG, "tryStop : empty=" + mCountDownTaskMap.isEmpty() + "   isrun="
//                + mIsRunning);
//
//        if (mCountDownTaskMap.isEmpty() && mIsRunning) {
//            mIsRunning = false;
//            mCountDownRun.isAuto = false;
//        }
//    }
//
//    /**
//     * 通知宿主，启动倒计时
//     * */
//    private void tryStart() {
//
//        Logger.v(TAG, "tryStart : empty=" + mCountDownTaskMap.isEmpty() + "   isrun="
//                + mIsRunning);
//
//        if (!mCountDownTaskMap.isEmpty() && !mIsRunning) {
//            mIsRunning = true;
//            mCountDownRun.isAuto = true;
//            mCountDownHandler.removeCallbacks(mCountDownRun);
//            mCountDownHandler.postDelayed(mCountDownRun, 1000);
//        }
//    }
//
//}
