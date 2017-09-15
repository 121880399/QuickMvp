package org.zzy.quickmvp.mvp.model.bean;

import com.zzy.quick.mvp.model.BaseModel;

import java.util.List;

/**
 * 项目名称: QuickMvp
 * 创建人: 周正一
 * 创建时间：2017/9/15
 */

public class ForecastWeather extends BaseModel {

    /**
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.90498734","lon":"116.40528870","update":{"loc":"2017-09-15 10:46","utc":"2017-09-15 02:46"}}
     * daily_forecast : [{"astro":{"mr":"00:16","ms":"15:06","sr":"05:56","ss":"18:24"},"cond":{"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"},"date":"2017-09-15","hum":"39","pcpn":"0.0","pop":"0","pres":"1017","tmp":{"max":"28","min":"18"},"uv":"4","vis":"20","wind":{"deg":"175","dir":"南风","sc":"微风","spd":"5"}},{"astro":{"mr":"01:18","ms":"15:57","sr":"05:57","ss":"18:22"},"cond":{"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"},"date":"2017-09-16","hum":"38","pcpn":"0.0","pop":"0","pres":"1014","tmp":{"max":"27","min":"18"},"uv":"5","vis":"20","wind":{"deg":"206","dir":"西南风","sc":"微风","spd":"3"}},{"astro":{"mr":"02:23","ms":"16:42","sr":"05:57","ss":"18:20"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-09-17","hum":"22","pcpn":"0.0","pop":"0","pres":"1013","tmp":{"max":"29","min":"16"},"uv":"5","vis":"20","wind":{"deg":"289","dir":"西北风","sc":"微风","spd":"4"}}]
     * status : ok
     */

    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeather5Bean> HeWeather5) {
        this.HeWeather5 = HeWeather5;
    }

    public static class HeWeather5Bean {
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.90498734
         * lon : 116.40528870
         * update : {"loc":"2017-09-15 10:46","utc":"2017-09-15 02:46"}
         */

        private BasicBean basic;
        private String status;
        /**
         * astro : {"mr":"00:16","ms":"15:06","sr":"05:56","ss":"18:24"}
         * cond : {"code_d":"104","code_n":"104","txt_d":"阴","txt_n":"阴"}
         * date : 2017-09-15
         * hum : 39
         * pcpn : 0.0
         * pop : 0
         * pres : 1017
         * tmp : {"max":"28","min":"18"}
         * uv : 4
         * vis : 20
         * wind : {"deg":"175","dir":"南风","sc":"微风","spd":"5"}
         */

        private List<DailyForecastBean> daily_forecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            /**
             * loc : 2017-09-15 10:46
             * utc : 2017-09-15 02:46
             */

            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * mr : 00:16
             * ms : 15:06
             * sr : 05:56
             * ss : 18:24
             */

            private AstroBean astro;
            /**
             * code_d : 104
             * code_n : 104
             * txt_d : 阴
             * txt_n : 阴
             */

            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            /**
             * max : 28
             * min : 18
             */

            private TmpBean tmp;
            private String uv;
            private String vis;
            /**
             * deg : 175
             * dir : 南风
             * sc : 微风
             * spd : 5
             */

            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
