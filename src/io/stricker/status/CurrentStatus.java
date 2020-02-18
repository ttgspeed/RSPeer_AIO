package io.stricker.status;

public class CurrentStatus {
    private static Status WT_STATUS = Status.IDLE;

    public static Status get(){
        return WT_STATUS;
    }

    public static void set(Status newStatus){
        WT_STATUS = newStatus;
    }
}
