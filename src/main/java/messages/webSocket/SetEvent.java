package messages.webSocket;

public class SetEvent {



//    //set event
//    Optional("https://uandidance-events.com/UIS/eventcrud/getevent/7311b48e-c5eb-4626-afd7-50aab995649c/7e2458b3-deb8-400a-b5aa-cd49595fba88")
//    Optional(["Content-Type": "application/json"])
//    Optional({
//        bookStatus = 0;
//        categoryId = 294;
//        categoryName = "1 Song Playlist";
//        commentsFromLeader = "";
//        cost = 0;
//        date = "2019-10-15";
//        datetime = "2019-10-15 14:42:00";
//        duration = "00:02:43";
//        endTime = "";
//        eventId = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        eventRating = 0;
//        leaderId = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        leaderName = "Name Last Name";
//        locationReception = DontKnow;
//        mediaCost = 0;
//        mediaId = "cdc06cde-855e-483e-b3bb-9bece631d190";
//        mediaName = "1 Song playlist";
//        name = sergtest;
//        playlists = 1;
//        public = 1;
//        status = published;
//        time = "14:42:00";
//        timezoneId = 58;
//        typeName = Playlist;
//        utcTime = "2019-10-15 11:42:00";
//    })
//
//    //Join Event
//    Optional("https://uandidance-events.com/UIS/media/playlists/cdc06cde-855e-483e-b3bb-9bece631d190")
//    Optional(["Content-Type": "application/json"])
//    Optional({
//        list =     (
//                {
//                        itemId = "7d2665b5-beb6-498a-811c-54864881f118";
//        name = "1 Song Playlist";
//        trackList =             (
//                {
//                        duration = "00:02:43";
//        serialNumber = 3004;
//        size = 3108829;
//        songFile = "https://s3-us-west-1.amazonaws.com/uandibucket/kPAeFbQsvdErJPjO8Qm004_Feel_It_Still.m4a";
//        trackId = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        trackName = "04 Feel It Still";
//                }
//            );
//        }
//    );
//        status = success;
//    })
//
////open socket
//    CONNECTED ==========
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 1;
//        "message_id" = "E8463869-BC2E-4C13-8777-22D77D33DAFC";
//        route = "user_join_event";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139878547;
//    }
//
//    RECEIVED ========== 1571139878.878044
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        route = "delivery_confirmation";
//        "server_time_stamp" = 1571139878678;
//        "target_message_id" = "E8463869-BC2E-4C13-8777-22D77D33DAFC";
//        "target_route" = "user_join_event";
//    }
//
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "message_id" = "9E2F947A-2494-48FD-B469-4695E633F046";
//        route = "delivery_confirmation";
//        "target_message_id" = "94ca6e06-8abc-4612-bd94-44f8f7e59fe9";
//        "target_route" = "user_join_event";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139878887;
//    }
//
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 1;
//        "message_id" = "0DD2AD2B-5C9E-42ED-ACDC-B3A45C8E3D4B";
//        route = "event_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139878894;
//    }
//
//    RECEIVED ========== 1571139878.8828948
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 1;
//        "message_id" = "94ca6e06-8abc-4612-bd94-44f8f7e59fe9";
//        route = "user_join_event";
//        "server_time_stamp" = 1571139878683;
//        user =     {
//                "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_name" = "Name Last Name";
//    };
//    }
//
//    RECEIVED ========== 1571139879.5363069
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        route = "delivery_confirmation";
//        "server_time_stamp" = 1571139879303;
//        "target_message_id" = "0DD2AD2B-5C9E-42ED-ACDC-B3A45C8E3D4B";
//        "target_route" = "event_state";
//    }
//
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "message_id" = "361AF6F0-A643-4F28-BF79-82AC368C2A25";
//        route = "delivery_confirmation";
//        "target_message_id" = "b7ebbf43-6ac7-4407-9ade-7760d54a80e7";
//        "target_route" = "event_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139879540;
//    }
//
//    RECEIVED ========== 1571139879.538051
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "event_status" = published;
//        "is_need_confirmation" = 1;
//        leader =     {
//                "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_name" = "Name Last Name";
//    };
//        "message_id" = "b7ebbf43-6ac7-4407-9ade-7760d54a80e7";
//        name = sergtest;
//        playlists =     (
//                {
//                        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        listeners =             (
//                );
//        name = "1 Song Playlist";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        tracks =             (
//                {
//                        duration = 163000;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "file_url" = "https://s3-us-west-1.amazonaws.com/uandibucket/kPAeFbQsvdErJPjO8Qm004_Feel_It_Still.m4a";
//        name = "04 Feel It Still";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_order_position" = 0;
//                }
//            );
//        }
//    );
//        route = "event_state";
//        "server_time_stamp" = 1571139879306;
//        "users_in_room" =     (
//                {
//                        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_name" = "Name Last Name";
//        }
//    );
//    }
//
//
////Start Event
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 1;
//        "message_id" = "79A46466-4490-4C38-9FB7-965E27C80639";
//        route = "event_start";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139936565;
//    }
//
//    RECEIVED ========== 1571139936.9371798
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        route = "delivery_confirmation";
//        "server_time_stamp" = 1571139936721;
//        "target_message_id" = "79A46466-4490-4C38-9FB7-965E27C80639";
//        "target_route" = "event_start";
//    }
//
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "message_id" = "11CB71FE-5964-4608-8F86-3BF8C36D5194";
//        route = "delivery_confirmation";
//        "target_message_id" = "f71ff216-0f0f-4500-b6b6-3bcaa9a5889c";
//        "target_route" = "event_start";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139936943;
//    }
//
//    RECEIVED ========== 1571139936.9405427
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 1;
//        "message_id" = "f71ff216-0f0f-4500-b6b6-3bcaa9a5889c";
//        route = "event_start";
//        "server_time_stamp" = 1571139936726;
//    }
//
//    //get media and go to player
//    Optional("https://uandidance-events.com/UIS/media/get?userId=7311b48e-c5eb-4626-afd7-50aab995649c&mediaId=cdc06cde-855e-483e-b3bb-9bece631d190")
//    Optional([:])
//    SUCCESS: {
//        active = 1;
//        categoryId = 294;
//        cost = 0;
//        id = "cdc06cde-855e-483e-b3bb-9bece631d190";
//        name = "1 Song playlist";
//        promoCode = i5y3077jh2lw0kb93s9i;
//        published = 1;
//        rating = "3.964666666666667";
//        verified = 1;
//    }
////user on player
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 1;
//        "message_id" = "27BFEE3E-0838-437A-9B67-104B5E2BA0F6";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "user_join_playlist";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139938237;
//    }
//
//    RECEIVED ========== 1571139938.6776457
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        route = "delivery_confirmation";
//        "server_time_stamp" = 1571139938391;
//        "target_message_id" = "27BFEE3E-0838-437A-9B67-104B5E2BA0F6";
//        "target_route" = "user_join_playlist";
//    }
//
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "message_id" = "A97D8CFF-C617-4081-9DC9-CB26858B3289";
//        route = "delivery_confirmation";
//        "target_message_id" = "1a070ee9-b49d-4ff9-836d-b87a69319d6e";
//        "target_route" = "user_join_playlist";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139938680;
//    }
//
//    updatePlaylistListeners
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        listeners =     (
//                {
//                        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_name" = "Name Last Name";
//        }
//    );
//        name = "1 Song Playlist";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        tracks =     (
//                {
//                        duration = 163000;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "file_url" = "https://s3-us-west-1.amazonaws.com/uandibucket/kPAeFbQsvdErJPjO8Qm004_Feel_It_Still.m4a";
//        name = "04 Feel It Still";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_order_position" = 0;
//        }
//    );
//    }
//
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "A02C920A-FAAA-4B55-B871-425AA2D93FAE";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "playlist_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139938689;
//    }
//
//    RECEIVED ========== 1571139938.6792538
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 1;
//        "message_id" = "1a070ee9-b49d-4ff9-836d-b87a69319d6e";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "user_join_playlist";
//        "server_time_stamp" = 1571139938393;
//        user =     {
//                "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_name" = "Name Last Name";
//    };
//    }
//
///// дальше всякие меседжи
//    RECEIVED ========== 1571139939.2913158
//
//    {
//        "action_time" = 1571139939087;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "c2ce9b09-8998-4bf4-8a82-7edfc4bce31e";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = play;
//        route = "playlist_state";
//        "server_time_stamp" = 1571139939088;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 2361;
//    }
//
//    setSilencePlayerTo false
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "622AD3B6-DADF-4B4D-8C1D-295EDEFD2054";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "playlist_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139939334;
//    }
//
//    diff Before HardSync = 0.025699853897094727
//    RECEIVED ========== 1571139939.5976899
//
//    {
//        "action_time" = 1571139939463;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "a108e327-ca2c-4cfc-816f-08d72ba5be80";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = play;
//        route = "playlist_state";
//        "server_time_stamp" = 1571139939463;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 2737;
//    }
//
//    setSilencePlayerTo false
//    diff Before HardSync = 0.02595806121826172
//    Start lite sync 0.10300230979919434
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "F7CA86B5-FDFA-4FC9-9CC0-465E526680D4";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "playlist_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139956790;
//    }
//
//    RECEIVED ========== 1571139957.1091547
//
//    {
//        "action_time" = 1571139956945;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "2e79e85f-be69-442e-8691-99adff453492";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = play;
//        route = "playlist_state";
//        "server_time_stamp" = 1571139956946;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 20219;
//    }
//
//    setSilencePlayerTo false
//    soft rewind only isSameTrack: true -0.012930154800415039
//    Start lite sync 0.012817621231079102
//    SEND ==========
//
//    {
//        "action_time" = 1571139960465;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 1;
//        "message_id" = "1B6615F9-E8ED-4AC2-AE98-871F8C7D9B35";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        route = "playlist_command";
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 23862;
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139960465;
//    }
//
//    RECEIVED ========== 1571139960.796558
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        route = "delivery_confirmation";
//        "server_time_stamp" = 1571139960620;
//        "target_message_id" = "1B6615F9-E8ED-4AC2-AE98-871F8C7D9B35";
//        "target_route" = "playlist_command";
//    }
//
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "message_id" = "10DB8358-FFDE-4739-A944-DADFF3009345";
//        route = "delivery_confirmation";
//        "target_message_id" = "3e322553-e4ff-41b7-a961-54d66ac3fddb";
//        "target_route" = "playlist_command";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139960800;
//    }
//
//    RECEIVED ========== 1571139960.7987077
//
//    {
//        "action_time" = 1571139960623;
//        delay = 0;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 1;
//        "message_id" = "3e322553-e4ff-41b7-a961-54d66ac3fddb";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        route = "playlist_command";
//        "server_time_stamp" = 1571139960623;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 23896;
//    }
//
//    setSilencePlayerTo true
//    AudioSessionMng: ToPlayback
//    silencePlayer isPlaying true
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "373D43F0-9138-4EF9-B5D2-F58B6234B05E";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "playlist_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571139981065;
//    }
//
//    RECEIVED ========== 1571139981.3791747
//
//    {
//        "action_time" = 1571139981195;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "0451e73a-fa05-4a4e-a98c-369c23788a84";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        route = "playlist_state";
//        "server_time_stamp" = 1571139981196;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 23896;
//    }
//
//    setSilencePlayerTo true
//    AudioSessionMng: ToPlayback
//    silencePlayer isPlaying true
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "DAB995C2-0B86-4DD9-ADDB-F8F6ECA5DE16";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "playlist_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571140001470;
//    }
//
//    RECEIVED ========== 1571140001.7580028
//
//    {
//        "action_time" = 1571140001625;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "8e6ba37e-c6fe-4f73-bbaa-db073937b163";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        route = "playlist_state";
//        "server_time_stamp" = 1571140001625;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 23896;
//    }
//
//    setSilencePlayerTo true
//    AudioSessionMng: ToPlayback
//    silencePlayer isPlaying true
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "FE2CC1AC-5692-49E2-B87F-144C99AF7F0E";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "playlist_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571140021844;
//    }
//
//    RECEIVED ========== 1571140022.1334019
//
//    {
//        "action_time" = 1571140022000;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "86451b5f-bf32-4045-8c6a-299f99e266ff";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        route = "playlist_state";
//        "server_time_stamp" = 1571140022000;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 23896;
//    }
//
//    setSilencePlayerTo true
//    AudioSessionMng: ToPlayback
//    silencePlayer isPlaying true
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "1EC46331-0918-42AE-9261-F3A72565A04E";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "playlist_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571140042210;
//    }
//
//    RECEIVED ========== 1571140042.5122578
//
//    {
//        "action_time" = 1571140042361;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "0d9f0641-7bbb-4fbd-b475-86d5589ef99e";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        route = "playlist_state";
//        "server_time_stamp" = 1571140042361;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 23896;
//    }
//
//    setSilencePlayerTo true
//    AudioSessionMng: ToPlayback
//    silencePlayer isPlaying true
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "75D82BF5-1FD8-4CFF-A3ED-1EEEA62A4C2E";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "playlist_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571140062662;
//    }
//
//    RECEIVED ========== 1571140062.9925468
//
//    {
//        "action_time" = 1571140062816;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "90318932-c04b-4b5c-972d-f4df16cdfd04";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        route = "playlist_state";
//        "server_time_stamp" = 1571140062816;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 23896;
//    }
//
//    setSilencePlayerTo true
//    AudioSessionMng: ToPlayback
//    silencePlayer isPlaying true
//    SEND ==========
//
//    {
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "404F7815-8569-4C18-9126-0E48645B3286";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        route = "playlist_state";
//        "user_id" = "7311b48e-c5eb-4626-afd7-50aab995649c";
//        "user_time_stamp" = 1571140083043;
//    }
//
//    RECEIVED ========== 1571140083.3696437
//
//    {
//        "action_time" = 1571140083184;
//        "event_id" = "7e2458b3-deb8-400a-b5aa-cd49595fba88";
//        "is_need_confirmation" = 0;
//        "message_id" = "ac577995-c985-4aed-a26f-ef06df5cecde";
//        "playlist_id" = "7d2665b5-beb6-498a-811c-54864881f118";
//        "playlist_status" = pause;
//        route = "playlist_state";
//        "server_time_stamp" = 1571140083184;
//        "track_id" = "61b34db7-6a19-4ded-8a67-238181d86f62";
//        "track_position" = 23896;
//    }
//
//    setSilencePlayerTo true
//    AudioSessionMng: ToPlayback
//    silencePlayer isPlaying true
}
