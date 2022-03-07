package com.example.myfirsrtpro;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //this method is called when the service is created
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this,R.raw.backgroundmusic);
        mediaPlayer.setLooping(true);//set the music file to loop
        mediaPlayer.setVolume(100,100);
        //loads the file, sets settings to the file
    }

    //when the service is started
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    //when the service is destroyed to stop playing the music
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}
//todo put this somewhere and turn it on