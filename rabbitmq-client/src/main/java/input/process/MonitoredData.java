package input.process;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class MonitoredData implements Serializable {

    private Date startTime;
    private Date endTime;
    private String activity;
    private Integer id;

    public MonitoredData(@JsonProperty("patient_id") Integer id,@JsonProperty("startDate") Date st,
                         @JsonProperty("endDate") Date end,
                         @JsonProperty("activity") String activ) {
        this.id = id;
        this.startTime=st;
        this.endTime=end;
        this.activity=activ;
    }

    public Integer getId(){
        return this.id;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public String getActivity() {
        return this.activity;
    }

    public long getDurationM() {
        return TimeUnit.MILLISECONDS.toMinutes(endTime.getTime()-startTime.getTime());
    }

    public long getDurationH() {
        return TimeUnit.MILLISECONDS.toHours(endTime.getTime()-startTime.getTime());
    }

}