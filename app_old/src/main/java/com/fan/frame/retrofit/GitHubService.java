package com.fan.frame.retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user") String user);

    /*@POST("query")
    Call.aidl<ResponseBody> search(@Query("type") String type, @Query("postid") String postid);*/

    @POST("query")
    Call<PostQueryInfo> search(@Query("type") String type, @Query("postid") String postid);

    public class PostQueryInfo {
        private String message;
        private String nu;
        private String ischeck;
        private String com;
        private String status;
        private String condition;
        private String state;
        private List<DataBean> data;
        public static class DataBean {
            private String time;
            private String context;
            private String ftime;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            @Override
            public String toString() {
                return "DataBean{" +
                        "time='" + time + '\'' +
                        ", context='" + context + '\'' +
                        ", ftime='" + ftime + '\'' +
                        '}';
            }
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getNu() {
            return nu;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "PostQueryInfo{" +
                    "message='" + message + '\'' +
                    ", nu='" + nu + '\'' +
                    ", ischeck='" + ischeck + '\'' +
                    ", com='" + com + '\'' +
                    ", status='" + status + '\'' +
                    ", condition='" + condition + '\'' +
                    ", state='" + state + '\'' +
                    ", data=" + data +
                    '}';
        }
    }


}
