package edu.utdallas.gamegenerator.Shared;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioPlayer 
{
	private static Clip currentClip;
	
	public static void playAudio(String path)
	{
		stopAudio();
		try {
		    File yourFile = new File(path);
		    System.out.println(path);
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    final Clip clip;

		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    currentClip = clip;

		    clip.addLineListener(new LineListener()
			{
				public void update(LineEvent event)
				{
					if (event.getType() == LineEvent.Type.STOP) {
						clip.close();
					}
				}
        	});

		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void stopAudio()
	{
		if(!(currentClip == null) && currentClip.isRunning())
		{
			currentClip.stop();
			currentClip.close();
		}
	}
}
