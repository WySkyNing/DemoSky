package com.ning.demosky.view.mvp.model;

import com.ning.demosky.view.mvp.presenter.NetworkConnectInter;

/**
 * Created by yorki on 2016/6/14.
 */
public interface ModelInter {

    /**
     * 加载网络数据
     * @param  url
     * http://218.60.28.101/car/API/
     *
     * private void initData() {

    if (refreshPageNumber == 1){
    listData.clear();
    }

    JSONObject jsonObject = new JSONObject();
    try {
    jsonObject.put("userId", SharedPreferencesUtils.readUserId(aq.getContext()));
    jsonObject.put("engineOilSort", strEngineOilSort);
    jsonObject.put("pageNow", refreshPageNumber + "");

    if (!("".equals(strEngineOilBrand))){
    jsonObject.put("engineOilBrandName",strEngineOilBrand);
    }

    } catch (JSONException e) {
    e.printStackTrace();
    }

    GetNetDate.getInstance().startRequest(this, this, "EngineOilListActivity",
    Global.URL + "API_O2_GET_ENGINE_OIL_LIST", jsonObject);

    }
     * */
    void startLoadConnect(String url, NetworkConnectInter networkConnectInter);
}
