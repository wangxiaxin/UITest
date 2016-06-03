//package com.changingedu.dev.rxdemo;
//
//import com.changingedu.dev.util.Logger;
//
//import java.util.HashMap;
//import java.util.concurrent.TimeUnit;
//
//import rx.Observable;
//import rx.Observer;
//import rx.Subscription;
//import rx.functions.Func1;
//import rx.observables.ConnectableObservable;
//
///**
// * Created by Wangxiaxin on 2016/4/1.
// */
//public class TestCountDown {
//
//    private class CountDownTask {
//        CountDownListener listener;
//        int count;
//        int oriCount;
//        boolean isRepeat;// 是否是循环任务
//    }
//
//    private HashMap<String, ConnectableObservable> mCountDownTasks;
//
//    private TestCountDown() {
//        mCountDownTasks = new HashMap<>();
//    }
//
//    private ConnectableObservable createNewObservable(int counts) {
//        Subscription s =  Observable.interval(1, TimeUnit.SECONDS).take(counts).publish().connect();
//    }
//
//    private ConnectableObservable getObservable(String tag, int counts) {
//        ConnectableObservable ob = mCountDownTasks.get(tag);
//        if (ob == null) {
//            ob = createNewObservable(counts);
//            ob.autoConnect()
//            mCountDownTasks.put(tag, ob);
//        }
//        return ob;
//    }
//
//    private void addCountDownTask(String tag,int )
//
//    public void Btn4_RunTask5Times_IntervalOf3s() {
//        Logger.v(String.format("D4 [%s] --- BTN click", _getCurrentTimestamp()));
//
//        Observable//
//                .interval(3, TimeUnit.SECONDS).take(5)//
//                .map(new Func1<Long, Integer>() {
//                    @Override
//                    public Integer call(Long aLong) {
//                        return null;
//                    }
//                })
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onCompleted() {
//                        Logger.v(String
//                                .format("D4 [%s] XXX COMPLETE", _getCurrentTimestamp()));
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Logger.v.e(e, "something went wrong in TimingDemoFragment example");
//                    }
//
//                    @Override
//                    public void onNext(Long number) {
//                        Logger.v(String.format("D4 [%s]     NEXT", _getCurrentTimestamp())
//                                + "  num=" + number);
//                    }
//                });
//    }
//}
