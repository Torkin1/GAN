package mp.homework.gan.view;

import android.content.Context;
import android.util.AttributeSet;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MappableSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {

    // SeekBar with a variable range

    private final int baseMax = 100;
    private final int baseMin = 0;
    private int newMax = baseMax;
    private int newMin = baseMin;

    public MappableSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNewMax(int newMax){
        this.newMax = newMax;
    }
    public void setNewMin(int newMin){
        this.newMin = newMin;
    }

    public int getNewMax() {
        return this.newMax;
    }

    public int getNewMin() {
        return this.newMin;
    }

    private int map(int baseVal){
        // Maps a given value in 0-100 range to an output value in newMin-newMax range
        try{
            return (((this.newMax - this.newMin)*baseVal)/this.baseMax) + this.newMin;
        } catch (ArithmeticException e){
            // when calling super(Context context, AttributeSet attrs), baseMax is not yet initialized and has value == 0.
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, String.format(Locale.getDefault(),"baseMin=%d, baseMax=%d, newMin=%d, newMax=%d, baseVal=%d", this.baseMin, this.baseMax, this.newMin, this.newMax, baseVal));
            return 0;
        }
    }
    private int demap(int mappedVal) {
        // Finds first suitable value in 0-100 range which can be mapped to mappedVal
        if (mappedVal < this.newMin){
            mappedVal = this.newMin;
        }
        if (mappedVal > this.newMax){
            mappedVal = this.newMax;
        }
        int candidate;
        for (candidate = 0; this.map(candidate) != mappedVal; candidate ++){ }
        return candidate;
    }

    @Override
    public int getProgress(){
        return this.map(super.getProgress());
    }

    @Override
    public void setProgress(int val){
        super.setProgress(this.demap(val));
    }
}
