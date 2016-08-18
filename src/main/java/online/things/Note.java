package online.things;

/**
 * Created by jfische1 on 18.08.16.
 */
public class Note {

    private long duration;
    private int frequency;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Note(long duration, int frequency) {
        this.duration = duration;
        this.frequency = frequency;
    }


}
