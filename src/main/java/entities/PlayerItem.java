package entities;

import com.uidance.websocket.config.MainWebSocketHandler;
import com.uidance.websocket.handlers.Impl.new_flow.EndEventMainHandler;
import com.uidance.websocket.handlers.Impl.player.StreamPauseMainHandler;
import org.apache.log4j.Logger;

import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class PlayerItem {

    private final Logger LOG = Logger.getLogger(this.getClass());
    private final long ZERO = Time.valueOf("00:00:00").getTime();
    private Timer timer = null;
    private TimerTask timerTask = null;

    private final String eventId;
    private final String albumName;
    private volatile String currentTrackId;
    private volatile long currentTime;
    private volatile long lastChange;
    private volatile PlayerStatus playerStatus;
    private final List<TrackInfo> tracks;
    private final boolean validated;
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();



    public PlayerItem(String eventId, String albumName, List<TrackInfo> tracks, boolean validated) {
        this.eventId = eventId;
        this.albumName = albumName;
        this.currentTrackId = tracks.get(0).getTrackId();
        this.currentTime = ZERO;
        this.lastChange = ZERO;
        this.playerStatus = PlayerStatus.pause;
        this.tracks = tracks;
        this.validated = validated;
    }

    private PlayerItem(List<TrackInfo> tracks) {
        this.eventId = null;
        this.albumName = null;
        this.currentTrackId = tracks.get(0).getTrackId();
        this.currentTime = ZERO;
        this.lastChange = System.currentTimeMillis();
        this.playerStatus = PlayerStatus.play;
        this.tracks = tracks;
        this.validated = false;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public static PlayerItem getTestPlayerItem() {
        TrackInfo trackInfo = new TrackInfo();
        trackInfo.setTrackId("TEST_TRACK");
        trackInfo.setDuration(Time.valueOf("00:00:25").getTime());
        return new PlayerItem(Collections.singletonList(trackInfo));
    }

    public String getCurrentTrackId() {
        return currentTrackId;
    }

    public List<TrackInfo> getTracks() {
        return tracks;
    }

    public String getAlbumName() {
        return albumName;
    }

    private void createTimer(long now, MainWebSocketHandler mainWebSocketHandler) {
        clearTimer();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                StreamPauseMainHandler streamPauseHandler = mainWebSocketHandler.getStreamPauseHandler();
                EndEventMainHandler eventEndMainHandler = mainWebSocketHandler.getEventEndMainHandler();
                System.out.println("TIMER TASK END");
                if (streamPauseHandler.streamEnd(eventId, albumName)) {
                    if (validated){
                        eventEndMainHandler.broadcastEventEnd(eventId, albumName, mainWebSocketHandler);
                    }
                    else{
                        streamPauseHandler.broadcastPlaylistEnd(eventId, albumName, mainWebSocketHandler);
                    }
//                    eventEndMainHandler.afterPlaylistEndWithoutCloseConnection(eventId);

//                    service.schedule(new Runnable() {
//                        @Override
//                        public void run() {
//                            eventEndMainHandler.afterPlaylistEnd(eventId, mainWebSocketHandler);
//                        }
//                    }, 4, TimeUnit.SECONDS);

//                    Runnable task = () -> eventEndMainHandler.afterPlaylistEnd(eventId, mainWebSocketHandler);
//                    service.schedule(task, 4, TimeUnit.SECONDS);


                }
            }
        };
        long extraTime = extraTime(now);
        timer.schedule(timerTask, extraTime);
    }

    private void clearTimer() {
        LOG.debug("Clear timer eventId:" + eventId + " itemId:" + albumName);
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    public void refresh() {
        clearTimer();
        this.currentTrackId = tracks.get(0).getTrackId();
        this.currentTime = ZERO;
        this.lastChange = ZERO;
        this.playerStatus = PlayerStatus.pause;
    }

    public boolean validation() {
        if (eventId == null || albumName == null || tracks == null) {
            return false;
        }
        if (tracks.size() == 0) {
            return false;
        }
        boolean dur = true;
        for (TrackInfo trackInfo : tracks) {
            dur &= (trackInfo.getDuration() != ZERO);
        }
        return dur;
    }

    public boolean correctEventId(final String eventId) {
        return eventId.equals(this.eventId);
    }

    public String getEventId() {
        return eventId;
    }

    public boolean correctParams(final String eventId, final String albumName) {
        return this.eventId.equals(eventId) && this.albumName.equals(albumName);
    }

    public TrackStatus getCurrentTrack(long now, boolean withId) {
        countTimeNow(now);
        TrackStatus trackStatus = new TrackStatus();
        trackStatus.setTrackId(withId ? currentTrackId : null);
        trackStatus.setTrackState(currentTime);
        trackStatus.setPlayerStatus(playerStatus);
        return trackStatus;
    }

    public boolean updateLastHange(long now) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return false;
        } else {
            LOG.debug("update last change:: now:" + now + " cur:" + currentTime);
            countTimeNow(now);
            if (lastChange == ZERO) {
                return false;
            } else {
                lastChange = ZERO;
                clearTimer();
                return true;
            }
        }
    }

    public boolean pause(long now) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return false;
        } else {
            LOG.debug("pause:: now:" + now + " cur:" + currentTime);
            countTimeNow(now);
            if (lastChange == ZERO) {
                return false;
            } else {
                lastChange = ZERO;
                clearTimer();
                LOG.debug("Pause: eventId:" + eventId + " albumName: " + albumName);
                playerStatus = PlayerStatus.pause;
                return true;
            }
        }
    }

    public boolean streamEnd(long now) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return false;
        } else {
            LOG.debug("pause:: now:" + now + " cur:" + currentTime);
            countTimeNow(now);
            if (lastChange == ZERO) {
                return false;
            } else {
                lastChange = ZERO;
                playerStatus = PlayerStatus.playlist_end;
                TrackInfo trackInfo = tracks.get(tracks.size() - 1);
                currentTrackId = trackInfo.getTrackId();
                currentTime = trackInfo.getDuration();
                clearTimer();
                LOG.debug("Pause: eventId:" + eventId + " albumName: " + albumName);
                return true;
            }
        }
    }

    public boolean start(long now, MainWebSocketHandler mainWebSocketHandler) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return false;
        } else {
            countTimeNow(now);
            createTimer(now, mainWebSocketHandler);
            LOG.debug("play: eventId:" + eventId + " albumName: " + albumName);
            if (lastChange == ZERO) {
                lastChange = now;
                playerStatus = PlayerStatus.play;
                return true;
            } else {
                return false;
            }
        }
    }

    public PlayerStatus canStart() {
        return playerStatus;
    }

    public Long currentTrackSpecificTime(String newTrackId, long specificTime, long now) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return null;
        } else {
            countTimeNow(now);
            if (lastChange != ZERO) {
                return null;
            }
            TrackInfo existTrack = currentTrack(newTrackId);
            if (existTrack == null) {
                return null;
            }
            long duration = getTrackDuration(existTrack.getTrackId());
            if (specificTime <= duration && specificTime >= ZERO) {
                currentTrackId = existTrack.getTrackId();
                currentTime = specificTime;
                return getRoadState();
            } else {
                return null;
            }
        }
    }

    public boolean currentTrackFromStart(long now) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return false;
        } else {
            countTimeNow(now);
            if (lastChange == ZERO) {
                currentTime = ZERO;
                return true;

            } else {
                return false;
            }
        }
    }

    public boolean setRoadState(long state, long now) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return false;
        } else {
            if (lastChange != ZERO || tracks.size() == 0) {
                return false;
            } else {
                currentTrackId = tracks.get(0).getTrackId();
                currentTime = ZERO;
                trackRunnedNow(state, now);
                return true;
            }
        }
    }

    public boolean prevTrack(long now) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return false;
        } else {
            countTimeNow(now);
            if (lastChange == ZERO) {
                currentTrackId = prevTrack(currentTrackId).getTrackId();
                currentTime = ZERO;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean nextTrack(long now) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return false;
        } else {
            countTimeNow(now);
            if (lastChange == ZERO) {
                currentTrackId = nextTrack(currentTrackId).getTrackId();
                currentTime = ZERO;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean newCurrentTrack(long now, String trackId) {
        if (playerStatus.equals(PlayerStatus.playlist_end)) {
            return false;
        } else {
            countTimeNow(now);
            try{
                currentTrackId = currentTrack(trackId).getTrackId();
                currentTime = ZERO;
            }catch (NullPointerException ex){
                System.out.println(ex.getMessage());
                return false;
            }


                return true;

        }
    }

    private long extraTime(long now) {
        countTimeNow(now);
        long sum = 0;
        if (tracks != null && tracks.size() > 0) {
            for (int i = tracks.size() - 1; i >= 0; i--) {
                TrackInfo item = tracks.get(i);
                if (item.getTrackId().equals(currentTrackId)) {
                    sum += (item.getDuration() - (currentTime - ZERO));
                    return sum;
                } else {
                    sum += (item.getDuration() - ZERO);
                }
            }
        }
        return sum;
    }

    private TrackInfo prevTrack(String trackId) {
        int startVal = tracks.size() - 1;
        for (int i = startVal; i >= 0; i--) {
            if (tracks.get(i).getTrackId().equals(trackId)) {
                if (i == 0) {
                    return tracks.get(tracks.size() - 1);
                }
                return tracks.get(i - 1);
            }
        }
        throw new NullPointerException();
    }

    private TrackInfo nextTrack(String trackId) {
        int tracksSize = tracks.size();
        for (int i = 0; i < tracksSize; i++) {
            if (tracks.get(i).getTrackId().equals(trackId)) {
                if (i == tracks.size() - 1) {
                    return tracks.get(0);
                }
                return tracks.get(i + 1);
            }
        }
        throw new NullPointerException();
    }

    public int trackCompare(String trackId){

        TrackInfo newInfo = null;
        TrackInfo currentInfo = null;

        for (TrackInfo track : tracks) {
            if (track.getTrackId().equals(trackId)) {
                newInfo = track;
            }
        }

        for (TrackInfo track : tracks) {
            if (track.getTrackId().equals(currentTrackId)) {
                currentInfo = track;
            }
        }

        if (newInfo != null && currentInfo != null){
            if (tracks.indexOf(newInfo) > tracks.indexOf(currentInfo)){
                return 1;
            }
            else
                if (tracks.indexOf(newInfo) < tracks.indexOf(currentInfo)){
                return -1;
            }
                else
                    return 0;
        }
        return 0;
    }

    public TrackInfo currentTrack(String trackId) {
        for (TrackInfo track : tracks) {
            if (track.getTrackId().equals(trackId)) {
                return track;
            }
        }
        throw new NullPointerException();
    }

    private Long getRoadState() {
        if (lastChange != ZERO) {
            return null;
        }
        long sum = 0;
        for (TrackInfo track : tracks) {
            if (track.getTrackId().equals(currentTrackId)) {
                sum += (currentTime - ZERO);
                return sum;
            } else {
                sum += (track.getDuration() - ZERO);
            }
        }
        return null;
    }

    private long getTrackTimeSum() {
        long sum = 0;
        for (TrackInfo item : tracks) {
            sum += (item.getDuration() - ZERO);
        }
        return sum;
    }

    private long getTrackDuration(String trackId) {
        for (TrackInfo item : tracks) {
            if (item.getTrackId().equals(trackId)) {
                return item.getDuration();
            }
        }
        return ZERO;
    }

    private void countTimeNow(long now) {
        if (lastChange != ZERO) {
            long diff = now - lastChange;
            trackRunnedNow(diff, now);
        }
    }

    private void trackRunnedNow(long diff, long now) {
        long trackSum = getTrackTimeSum();
        if (trackSum != 0) {
            trackSumNotNull(diff, trackSum, now);
        }
    }

    private void trackSumNotNull(long diff, long trackSum, long now) {
        LOG.debug("track not null1:: diff:" + diff + " trackSum:" + trackSum);
        int many = (int) round3(diff / trackSum);
        if (many != 0) {
            diff -= (trackSum * many);
        }
        TrackInfo currentTrack = currentTrack(currentTrackId);
        LOG.debug("track not null2:: diff:" + diff + " dur: " + currentTrack.getDuration() + " currentTime: " + currentTime);
        if (diff < (currentTrack.getDuration() - currentTime)) {
            currentTime += diff;
            lastChange = now;
            playerStatus = PlayerStatus.play;
            LOG.debug("track not null3:: now:" + now + " cur:" + currentTime);
        } else {
            diffMoreThanExtraTime(diff, currentTrack, now);
        }
        LOG.debug("track not null4:: now:" + now + " cur:" + currentTime);
    }

    private void diffMoreThanExtraTime(long diff, TrackInfo currentTrack, long now) {
        LOG.debug(":: now:" + now + " cur:" + currentTime);
        diff -= (currentTrack.getDuration() - currentTime);
        currentTrack = nextTrack(currentTrack.getTrackId());
        currentTime = ZERO;
        currentTrackId = currentTrack.getTrackId();
        LOG.debug("diffMoreThanExtraTime1:: now:" + now + " cur:" + currentTime);
        while (diff > 0) {
            if (diff < currentTrack.getDuration() - ZERO) {
                currentTime += diff;
                lastChange = now;
                playerStatus = PlayerStatus.play;
                currentTrackId = currentTrack.getTrackId();
                LOG.debug("diffMoreThanExtraTime2:: now:" + now + " cur:" + currentTime);
                return;
            } else {
                diff -= (currentTrack.getDuration() - ZERO);
                currentTrack = nextTrack(currentTrack.getTrackId());
                LOG.debug("diffMoreThanExtraTime3:: now:" + now + " cur:" + currentTime);
            }
        }
    }

    private double round3(double val) {
        double accuracy = 1000.0;
        return Math.round(val * accuracy) / accuracy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlayerItem)) {
            return false;
        }
        PlayerItem that = (PlayerItem) obj;

        return that.correctParams(this.eventId, this.albumName);
    }

    @Override
    public int hashCode() {
        String eventId = this.eventId != null ? this.eventId : "";
        String albumName = this.albumName != null ? this.albumName : "";
        return eventId.hashCode() ^ albumName.hashCode();
    }
}
