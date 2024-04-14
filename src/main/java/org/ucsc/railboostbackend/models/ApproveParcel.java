package org.ucsc.railboostbackend.models;

public class ApproveParcel {

    private String bookingId;
    private String senderName;
    private String receiverName;
    private String recoveringStation;
    private String item;
    private Float weight;
    private String status;


    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getRecoveringStation() {
        return recoveringStation;
    }

    public void setRecoveringStation(String recoveringStation) {
        this.recoveringStation = recoveringStation;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApproveParcel{" +
                "bookingId='" + bookingId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", recoveringStation='" + recoveringStation + '\'' +
                ", item='" + item + '\'' +
                ", weight=" + weight +
                ", status='" + status + '\'' +
                '}';
    }
}