package com.imooc.recorder.view;

import android.media.MediaPlayer;
import android.media.AudioManager;

import java.io.IOException;

/**
 * Created by Administrator on 2015/9/11.
 */
public class MediaManager {
    private static MediaPlayer mMediaPlayer;

    private static boolean isPause;

    public static void playSound(String filePath,
                                 MediaPlayer.OnCompletionListener onCompletionListener)
    {
        if(mMediaPlayer == null)
        {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    mMediaPlayer.reset();
                    return false;
                }
            });
        } else
        {
            mMediaPlayer.reset();
        }

        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(onCompletionListener);
            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pause() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying())
        {
            mMediaPlayer.pause();
            isPause = true;
        }
    }

    public static void resume()
    {
        if(mMediaPlayer != null && isPause)
        {
            mMediaPlayer.start();
            isPause = false;
        }
    }

    public static void release()
    {
        if(mMediaPlayer != null)
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
