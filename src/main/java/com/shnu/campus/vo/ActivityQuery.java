package com.shnu.campus.vo;

/**
 * Created by guodi on 2020-04-20 10:34
 */
public class ActivityQuery {

        private String title;
        private Long typeId;
        private boolean recommend;

        public ActivityQuery() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getTypeId() {
            return typeId;
        }

        public void setTypeId(Long typeId) {
            this.typeId = typeId;
        }

        public boolean isRecommend() {
            return recommend;
        }

        public void setRecommend(boolean recommend) {
            this.recommend = recommend;
        }

}
