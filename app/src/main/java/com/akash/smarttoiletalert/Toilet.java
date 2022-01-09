package com.akash.smarttoiletalert;

public class Toilet {
    public static int count;
    private String toiletnm;
    private String time;
    private String irvalue;
    private String methanevalue;
    private String status;

    public Toilet(String toiletnm,String time,String irvalue,String methanevalue,String status)
    {
        this.toiletnm=toiletnm;
        this.time=time;
        this.irvalue=irvalue;
        this.methanevalue=methanevalue;
        this.status=status;
    }
//    public Toilet(String toiletnm)
//    {
//        this.toiletnm=toiletnm;
//        this.time="3";
//        this.irvalue="50";
//        this.methanevalue="600";
//    }
    public Toilet()
    {

    }


    public String getToiletnm() {
        return toiletnm;
    }

    public void setToiletnm(String toiletnm) {
        this.toiletnm = toiletnm;
    }

    public String getMethanevalue() {
        return methanevalue;
    }

    public void setMethanevalue(String methanevalue) {
        this.methanevalue = methanevalue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIrvalue() {
        return irvalue;
    }

    public void setIrvalue(String irvalue) {
        this.irvalue = irvalue;
    }
    public Boolean checkstatus()
    {
        int val = Integer.parseInt(methanevalue);
        return (val>400? false:true);
    }
//    public boolean isIsexpand() {
//        return isexpand;
//    }
//
//    public void setIsexpand(boolean isexpand) {
//        this.isexpand = isexpand;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
