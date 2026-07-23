package homework_12.dip;

public class EmailSender implements MessageSender{
    @Override
    public void sendMessage(String message) {
        System.out.println("Отправка email: " + message);
    }
}
