package com.fan.frame.mvp.present;

import com.fan.frame.mvp.model.HttpModel;
import com.fan.frame.mvp.model.Imodle;
import com.fan.frame.mvp.view.Iview;

public class Presenter implements Ipresent {
    private Iview view;
    private Imodle model;

    public Presenter(Iview view) {
        this.view = view;
        model = new HttpModel(text -> Presenter.this.view.updateTv(text));
    }

    /**         text -> Presenter.this.view.updateTv(text)
     *          new Callback() {
     *             @Override
     *             public void onResult(String text) {
     *                 Presenter.this.view.updateTv(text);
     *             }
     *         }
     *
     */

    @Override
    public void request() {
        model.request();
    }

    @Override
    public void detachView() {
        view  = null;
        model.stop();
    }
}
