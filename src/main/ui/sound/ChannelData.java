package ui.sound;

import javax.sound.midi.MidiChannel;

// Represent data for some midichannel
public class ChannelData {

    private MidiChannel channel;

    private int velocity;
    private int pressure;
    private int bend;
    private int reverb;
    private int num;

    // getters
    public MidiChannel getChannel() {
        return channel;
    }

    public ChannelData(MidiChannel channel, int num) {
        this.channel = channel;
        this.num = num;
        velocity = 64;
        pressure = 64;
        bend = 64;
        reverb = 64;
    }
}

/*
 *Title:SimpleDrawingPlayer-Complete
 *Author:Norm Hutchinson
 *Date:Oct 19, 2019
 *Availability:https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
 */
