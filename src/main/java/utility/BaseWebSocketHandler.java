package utility;

import messages.webSocket.BaseServerSocketMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public abstract class BaseWebSocketHandler extends TextWebSocketHandler {

    final JSON json;
    public final Map<String, WebSocketSession> userSocketSessionMap = new ConcurrentHashMap<>();
    final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    Map<WebSocketSession, List<BaseServerSocketMsg>> messagesToSend = new ConcurrentHashMap<>();


//    private ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
   public ExecutorService executorService;
//    private ExecutorService executor2 = Executors.newFixedThreadPool(1000);


    private Set<WebSocketSession> pingPong = new HashSet<>();

    public BaseWebSocketHandler(JSON json) {
        this.json = json;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
//        LOG.debug("in");
        readWriteLock.writeLock().lock();
        try {

            String uid = session.getId();
            if (userSocketSessionMap.get(uid) == null) {
//                LOG.info("open connection sessionId: " + uid);
//                System.out.println(" getLocalAddress " + session.getLocalAddress());
//                System.out.println(" getRemoteAddress " + session.getRemoteAddress());
                userSocketSessionMap.put(uid, session);
            }
        } finally {
            readWriteLock.writeLock().unlock();
//            LOG.debug("out");
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        closeSession(session, false);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        String uId = null;
        readWriteLock.writeLock().lock();
        try {
            uId = session.getId();
            userSocketSessionMap.remove(uId);

//            System.out.println("connection close2");
        } finally {
            readWriteLock.writeLock().unlock();
//            LOG.debug("out");
//            LOG.info("closed connection sessionId: " + (uId == null ? "" : uId));
        }
    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) {
//        LOG.debug("in");
        readWriteLock.writeLock().lock();
        try {
            pingPong.remove(session);
        } finally {
            readWriteLock.writeLock().unlock();
//            LOG.debug("out");
//            LOG.debug("SOCKET PONG: " + session.getId());
        }
    }

    public void sendMessageToUserEventEnd(String uid, BaseServerSocketMsg responseMessage) {
        WebSocketSession session = null;
        try {
            session = userSocketSessionMap.get(uid);

        } finally {
            responseMessage.setServerTimeStamp(System.currentTimeMillis());
            sendMessageEventEnd(session, new TextMessage(json.serialize(responseMessage)), uid);
        }
    }


    public void sendMessageToUser(String uid, BaseServerSocketMsg responseMessage) {
        WebSocketSession session = null;
//        LOG.debug("in");
//        readWriteLock.writeLock().lock();
        try {
            session = userSocketSessionMap.get(uid);

        } finally {
//            responseMessage.setServerTimeStamp(System.currentTimeMillis());
//            sendMessage(session, new TextMessage(json.serialize(responseMessage)));

//            sendMessageTest(session, new TextMessage(json.serialize(responseMessage)), uid);
            sendMessageTest(session, responseMessage, uid);


//            readWriteLock.writeLock().unlock();
//            LOG.debug("out");
        }
    }

    void sendMessageEventEnd(WebSocketSession session, WebSocketMessage message, String uid) {

        try {
            if (session != null && session.isOpen()) {
                session.sendMessage(message);
            }
        } catch (Exception e) {
        }
    }
    void sendMessageTest(WebSocketSession session, BaseServerSocketMsg message, String uid) {
//        System.out.println("message to user: " + message.getPayload());
//        System.out.println("users count: " + userSocketSessionMap.size());

        checkSessionAndSendMessage(session, message);
//        checkSessionAndSendMessageSceduler(session, message);
    }

    void sendMessage(WebSocketSession session, BaseServerSocketMsg message) {
//        System.out.println("message to user: " + message.getPayload());
//        System.out.println("users count: " + userSocketSessionMap.size());
         checkSessionAndSendMessage(session, message);
//        checkSessionAndSendMessageSceduler(session, message);
    }

    void sendPing(WebSocketSession session, WebSocketMessage message) {
//        try {
//            synchronized (session) {
//                if (session != null && session.isOpen()) {
//                    session.sendMessage(message);
//                }
//            }
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//        }
    }

    private void checkSessionAndSendMessage(WebSocketSession session, BaseServerSocketMsg message) {
        try {

            if (session != null && session.isOpen()) {
                synchronized (this) {
                    message.setServerTimeStamp(System.currentTimeMillis());
                    session.sendMessage(new TextMessage(json.serialize(message)));
//                        if(!message.getPayload().toString().contains("State"))
//                            System.out.println(message.getPayload());
                    }
                //}
            }
        } catch (Exception e) {
        }
    }

    private void checkSessionAndSendMessageSceduler(WebSocketSession session, BaseServerSocketMsg message) {
        try {
                if (session.isOpen()) {
                    if (!messagesToSend.containsKey(session)) {
                        List<BaseServerSocketMsg> messages = new ArrayList<>();
                        messages.add(message);
                        messagesToSend.put(session, messages);
                    } else messagesToSend.get(session).add(message);
                }
//                    message.setServerTimeStamp(System.currentTimeMillis());

        } catch (Exception e) {
        }
    }



    void closeSession(WebSocketSession session, boolean force) {
        try {
            if (session != null && session.isOpen()) {
                synchronized (this) {
                    session.close(new CloseStatus(1001, force ? "force" : "not_force"));
                }
            }
        } catch (Exception e) {
        }
    }

//    @Scheduled(fixedDelay = 30000)
//    private void PingPongInterview() {
////        LOG.debug("in");
//        readWriteLock.writeLock().lock();
//        try {
//            closeConn();
//            addToCollection();
//            setPing();
//        } finally {
//            readWriteLock.writeLock().unlock();
////            LOG.debug("out");
//        }
//    }

    @Scheduled(fixedDelay = 5)
    private void sendMultiple() {


        messagesToSend.forEach((k,v) -> executorService.submit(() -> v.forEach(message -> {
            try {
                message.setServerTimeStamp(System.currentTimeMillis());
                k.sendMessage(new TextMessage(json.serialize(message)));
                v.remove(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        })));
    }

}
