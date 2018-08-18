package com.ps.loanbox.net;

//import rx.Subscriber;

/**
 * 事件接受者  用于RXjava 工程用RxJava2   故舍弃
 */
public abstract class SimpleSubscribe{

}
//public abstract class SimpleSubscribe<T> extends Subscriber<T> {
//
//    private ImpBaseView baseView;
//
//    public SimpleSubscribe(ImpBaseView baseView){
//        this.baseView=baseView;
//    }
//
//    public SimpleSubscribe(){}
//    @Override
//    public void onStart() {
//        onRequestStart();
//    }
//
//    @Override
//    public void onCompleted() {
//
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        Log.i("code","错误信息："+e.getMessage());
//        if(baseView!=null){
//            baseView.getError(e);
//            baseView.hiddenProgressDialog();
//        }
//    }
//
//    @Override
//    public void onNext(T t) {
//        success(t);
//        if(baseView!=null){
//            baseView.hiddenProgressDialog();
//        }
//
//    }
//
//    public abstract void onRequestStart();
//    public abstract void success(T t);
//}
