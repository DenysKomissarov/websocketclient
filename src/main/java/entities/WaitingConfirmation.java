package entities;

import com.uidance.websocket.messages.BaseServerNotificationSMsg;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WaitingConfirmation {

    private Map<MessagePriority, BaseServerNotificationSMsg> messagesMap= new ConcurrentHashMap<>();


    public WaitingConfirmation() {

    }

    public WaitingConfirmation(BaseServerNotificationSMsg message,  Map<String, String> commands) {

        updateMessage(message, commands);
    }

    public void updateMessage(BaseServerNotificationSMsg message,  Map<String, String> commands){

        cleanNotNeededMsgsOnUserFlowStep(message.getRoute(), commands);
        MessagePriority messagePriority = MessagePriority.valueOf(message.getRoute());

//        int priorityInt = Integer.valueOf(messagePriority.getName());
//        for (MessagePriority priority : messagesMap.keySet()){
//            if (Integer.parseInt(priority.getName()) <= priorityInt){
//                messagesMap.remove(priority);
//                if (messagesMap.containsKey(priority)) {
//                    commands.remove(messagesMap.get(priority).getMessageId());
//                }
//            }
//        }
        messagesMap.put(messagePriority, message);
    }

    public void removeMessage(String messageId){


        Iterator<Map.Entry<MessagePriority, BaseServerNotificationSMsg> >
                iterator = messagesMap.entrySet().iterator();

        // Iterate over the HashMap
        while (iterator.hasNext()) {

            // Get the entry at this iteration
            Map.Entry<MessagePriority, BaseServerNotificationSMsg>
                    entry
                    = iterator.next();

            // Check if this value is the required value
            if (messageId.equals(entry.getValue().getMessageId())) {
                // Remove this entry from HashMap
                iterator.remove();
                break;
            }
        }

    }

    private void removeAllMessages(Map<String, String> commands){
        for (BaseServerNotificationSMsg message: messagesMap.values()) {
            commands.remove(message.getMessageId());
        }
    }
    private void removeMessageByCommand( Map<String, String> commands, MessagePriority ... messagePriorities){

        for (MessagePriority messagePriority : messagePriorities) {
            try{
                commands.remove(messagesMap.get(messagePriority).getMessageId());
            }catch(NullPointerException ex){
//                System.out.println("messageId: " + messagesMap.get(messagePriority).getMessageId() + " Don`t exist");
            }
        }

    }

    private void cleanNotNeededMsgsOnUserFlowStep(String route, Map<String, String> commands){

        switch (route){
            case "error":
                break;
            case "delivery_confirmation":
                break;
            //event
            case "user_join_event":
                removeAllMessages(commands);
                messagesMap.clear();
                break;
            case "user_leave_event":
                removeAllMessages(commands);
                messagesMap.clear();
                break;
            case "event_start":
                break;
            case "event_end":
                removeAllMessages(commands);
                messagesMap.clear();
                break;
            case "event_state":
                break;
            //playlist
            case "user_join_playlist":
                removeMessageByCommand(commands, MessagePriority.playlist_command, MessagePriority.playlist_state, MessagePriority.playlist_end);
                messagesMap.remove(MessagePriority.playlist_command);
                messagesMap.remove(MessagePriority.playlist_state);
                messagesMap.remove(MessagePriority.playlist_end);
                break;
            case "playlist_state":
                break;
            case "playlist_command":
                removeMessageByCommand(commands, MessagePriority.playlist_state);
                messagesMap.remove(MessagePriority.playlist_state);
                break;
            case "playlist_end":
                removeMessageByCommand(commands, MessagePriority.user_join_playlist, MessagePriority.user_leave_playlist, MessagePriority.playlist_command, MessagePriority.playlist_state);
                messagesMap.remove(MessagePriority.user_join_playlist);
                messagesMap.remove(MessagePriority.user_leave_playlist);
                messagesMap.remove(MessagePriority.playlist_command);
                messagesMap.remove(MessagePriority.playlist_state);

        }

    }


    public Map<MessagePriority, BaseServerNotificationSMsg> getMessagesMap() {
        return messagesMap;
    }

    public void setMessagesMap(Map<MessagePriority, BaseServerNotificationSMsg> messagesMap) {
        this.messagesMap = messagesMap;
    }
}
