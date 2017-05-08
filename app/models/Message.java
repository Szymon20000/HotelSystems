package models;

public class Message extends Model{

    public Integer id;
    public String content;
    public String title;
    public Integer addressee;
    public Integer sender;
    public int messageType;
    public Long date;

    public Message() {}

    public Message(String content, String title, Integer addressee, Integer sender, int messageType, Long date) {
        this.content = content;
        this.title = title;
        this.addressee = addressee;
        this.sender = sender;
        this.messageType = messageType;
        this.date = date;
    }

    public String getType() {
        String breakdown = "Breakdown";
        String remark = "Remark";
        String normalMessage = "Normal message";
        String response = "Response";
        if(messageType == 1) {
            return breakdown;
        } else if(messageType == 2) {
            return remark;
        } else if(messageType == 3) {
            return normalMessage;
        } else if(messageType == 4) {
            return response;
        } else {
            return null;
        }
    }

    public static int getManagerId() {
        final int MANAGER_ID = -1;
        return MANAGER_ID;
    }

}
