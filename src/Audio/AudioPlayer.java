package Audio;

import javax.sound.sampled.*;

public class AudioPlayer {
	
	private Clip clip;
	private int frame;
	public boolean pause;

	public AudioPlayer(String s) {
		
		try{
		
			AudioInputStream ais = 
				AudioSystem.getAudioInputStream(
						getClass().getResourceAsStream(s)
				);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat (
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels()*2,
				baseFormat.getSampleRate(),
				false
			);
			//decoded audio input stream
			AudioInputStream dais = 
				AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			
			pause = false;
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void pause() {
		pause = true;
		frame = clip.getFramePosition();
		stop();
	}
	
	public void resume() {
		if (pause == false) return;
		pause = false;
		clip.setFramePosition(frame);
		clip.start();
	}
	
	public void play() {
		if (clip == null) return;
			stop();
			clip.setFramePosition(0);
			clip.start();
	}
	
	public void stop() {
		if (clip.isRunning()) clip.stop();
	}
	
//	private void close(){
//		stop();
//		clip.close();
//	}
	
}
