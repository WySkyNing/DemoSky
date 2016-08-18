package com.ning.demosky.view.mvp.model;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class Entity {


    /**
     * engineOilBrandName : 美孚/Mobil
     */

    private List<EngineOilBrandListBean> engineOilBrandList;
    /**
     * engineOilPrice : 794
     * engineOilId : 1
     * engineOilName : 美孚/Mobil 金装美孚1号全合成机油0W-40 SN级（1L装）
     * engineOilSalesOrder : 93
     * engineOilPic1 : http://218.60.28.101:80/car/upload/22
     */

    private List<EngineOilListBean> engineOilList;

    public List<EngineOilBrandListBean> getEngineOilBrandList() {
        return engineOilBrandList;
    }

    public void setEngineOilBrandList(List<EngineOilBrandListBean> engineOilBrandList) {
        this.engineOilBrandList = engineOilBrandList;
    }

    public List<EngineOilListBean> getEngineOilList() {
        return engineOilList;
    }

    public void setEngineOilList(List<EngineOilListBean> engineOilList) {
        this.engineOilList = engineOilList;
    }

    public static class EngineOilBrandListBean {
        private String engineOilBrandName;

        public String getEngineOilBrandName() {
            return engineOilBrandName;
        }

        public void setEngineOilBrandName(String engineOilBrandName) {
            this.engineOilBrandName = engineOilBrandName;
        }
    }

    public static class EngineOilListBean {
        private int engineOilPrice;
        private int engineOilId;
        private String engineOilName;
        private int engineOilSalesOrder;
        private String engineOilPic1;

        public int getEngineOilPrice() {
            return engineOilPrice;
        }

        public void setEngineOilPrice(int engineOilPrice) {
            this.engineOilPrice = engineOilPrice;
        }

        public int getEngineOilId() {
            return engineOilId;
        }

        public void setEngineOilId(int engineOilId) {
            this.engineOilId = engineOilId;
        }

        public String getEngineOilName() {
            return engineOilName;
        }

        public void setEngineOilName(String engineOilName) {
            this.engineOilName = engineOilName;
        }

        public int getEngineOilSalesOrder() {
            return engineOilSalesOrder;
        }

        public void setEngineOilSalesOrder(int engineOilSalesOrder) {
            this.engineOilSalesOrder = engineOilSalesOrder;
        }

        public String getEngineOilPic1() {
            return engineOilPic1;
        }

        public void setEngineOilPic1(String engineOilPic1) {
            this.engineOilPic1 = engineOilPic1;
        }
    }
}
