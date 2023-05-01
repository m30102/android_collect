package com.fan.collect.java.midi;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

public class MidiFile {

    static Map<Integer,String> line_1 = new HashMap<>();
    static Map<Integer,String> line_2 = new HashMap<>();
    static Map<Integer,String> line_3 = new HashMap<>();
    static {
        line_3.put(0,"Z");
        line_3.put(1,"X");
        line_3.put(2,"C");
        line_3.put(3,"V");
        line_3.put(4,"B");
        line_3.put(5,"N");
        line_3.put(6,"M");
        line_2.put(0,"A");
        line_2.put(1,"S");
        line_2.put(2,"D");
        line_2.put(3,"F");
        line_2.put(4,"G");
        line_2.put(5,"H");
        line_2.put(6,"J");
        line_1.put(0,"Q");
        line_1.put(1,"W");
        line_1.put(2,"E");
        line_1.put(3,"R");
        line_1.put(4,"T");
        line_1.put(5,"Y");
        line_1.put(6,"U");
    }
    static float speed = -1;
    public static void main(String[] args) {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("创建异常");
            e.printStackTrace();
        }
        try {
            Sequence sequence = MidiSystem.getSequence(new File("D:\\真夏飞焰.mid"));
            Track[] tracks = sequence.getTracks();
            System.out.println("tracks:" + tracks.length);// 音轨数
            Sequencer sequencer = MidiSystem.getSequencer();
            float divType = sequence.getDivisionType(); // 获取序列的（计时方式？）

            int resolution = sequence.getResolution();// 获取序列的时间解析度
            // 宵宫 960?  00 01 00 02 03c0  midi类型1 音轨2 3c0为960
            // 960为每4分音符中所包含的tick数量  数值越大，MIDI系统的时间分辨率就越大。midimsg都是以tick为单位
            // 那每个四分音符时长多少？
            // 1个四分音符为1拍
            // https://www.jianshu.com/p/31d02765e1ec
            //https://www.bilibili.com/read/cv1753143/
            System.out.println("resolution:"+resolution);



            List<Note> noteList = new ArrayList<>();
            for (int t = 0; t < tracks.length; t++) {
                // 遍历轨道
                for (int i = 0; i < tracks[t].size(); i++) {

                    // https://blog.csdn.net/qq_27857857/article/details/78871306

                    MidiEvent midiEvent = tracks[t].get(i);// 获取轨道的信息
                    System.out.println("midiEventTick:" + midiEvent.getTick());//获取此信息在何时被触发
                    MidiMessage message = midiEvent.getMessage();// 获取信息对象
                    //一个tick具体是多久是以序列的resolution和divisionType决定的

                    int length = message.getLength(); // 获取MIDI数据长度

                    byte[] data = message.getMessage(); // 获取MIDI信息所包含的数据

                    int status = message.getStatus(); // 获取MIDI状态码 128 -255

                    StringBuffer sb = new StringBuffer();

                    for (byte d : data) {
                        // 高位置0,  因为负数不能被显示
                        sb.append(Integer.toHexString(d & 0xFF) + " ");
                    }
                    System.out.println("data:" + Arrays.toString(data));
                    System.out.println("dataHex:" + sb);
                    System.out.println("length:" + length);
                    System.out.println("status:" + status);

                    if (data.length == 3) {
                        // Midi信息状态位和数据位含义表 https://www.renrendoc.com/paper/210133536.html  (byte) 143 = -113
                        // 8x松开音符 9x按下音符  x为通道也就是不同乐器
                        // data[0] 为状态字节  data[1]为数据字节  多个数据字节？ TODO
                        if (data[0] >= -112 && data[0] <= -97) {
                            //(byte) 144 = -112   (byte) 159 = -97
                            if (data[1] != 0) {
                                noteList.add(new Note(midiEvent.getTick(), data[1], true));//  按下
                            } else {
                                noteList.add(new Note(midiEvent.getTick(), data[1], false));// 松开
                            }

                        } else if (data[0] <= -113) {
                            // 音符松开
                            noteList.add(new Note(midiEvent.getTick(), data[1], false));
                        }

                    }
                    // bpm 事件 [-1, 81, 3, 6, 80, 97] TODO
                    if (speed == -1 && data.length == 6 && data[0] == -1 && data[1] == 81 && data[2] == 3) {
                        speed = (500 / (float) sequence.getResolution()) * (120 / (60000000 / (float) (((data[3] & 0xFF) * 256 * 256) + ((data[4] & 0xFF) * 256) + (data[5] & 0xFF))));
                    }
                }
            }



            Collections.sort(noteList);

            System.out.println("集合:"+noteList);
            int x = 0;
            if(x == 0)return;

            List<Note> lyreNotes = new ArrayList<>();
            for(Note note:noteList){
                if(note.type()){
                    int note_36 = to36Key(note.getNote());
                    int[] lyreKeys = toLyreKey(note_36);
                    Arrays.stream(lyreKeys).forEach(i1 -> {
                        lyreNotes.add(new Note(note.getTick(), (byte) i1, note.type()));
                    });
                }
            }

            System.out.println("准备开始:");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long nextTick;
            List<Note> setNotes = new ArrayList<>(10);
            System.out.println("lyreNotes:"+lyreNotes);
            System.out.println(lyreNotes.get(1));
            for (int n = 0; n < lyreNotes.size(); n++) {
                Note note = lyreNotes.get(n);
                System.out.println("n:"+ n +" note"+ note);
                if ( (n + 1) < lyreNotes.size()) {
                    int nextIndex = n+1;
                    nextTick = lyreNotes.get(nextIndex).getTick();
                    System.out.println("nextIndex:"+ nextIndex +" nextNote:"+lyreNotes.get(nextIndex));
                } else {
                    nextTick = -1;
                }
                if(nextTick == note.getTick()){
                    setNotes.add(note);
                }else {
                    setNotes.add(note);

                    List<Integer> keys = new ArrayList<>();
                    setNotes.forEach(note1 -> {

                        int key;
                        if(note1.getNote() <=7){
                            // 第一排
                             key = (int)line_1.get(note1.getNote() - 1).charAt(0);
                        }else if(note1.getNote() >=8 && note1.getNote() <=14){
                             key = (int)line_2.get(note1.getNote() - 8).charAt(0);
                        }else{
                             key = (int)line_3.get(note1.getNote() - 15).charAt(0);
                        }
                        keys.add(key);
                    });

                    keys.forEach(key -> {
                        System.out.println("按下:"+key);
                        robot.keyPress(key);
                    });
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    keys.forEach(key -> {
                        System.out.println("抬起:"+key);
                        robot.keyRelease(key);
                    });

                    setNotes.clear();
                    if(nextTick != -1){
                        try {
                            long s = (long) ((float) (nextTick - note.getTick()) * speed);

                            System.out.println("speed:"+speed);
                            System.out.println("nextTick:"+nextTick);
                            System.out.println("note.getTick(:"+note.getTick());
                            System.out.println("sleep:"+s);
                            Thread.sleep((long) ((float) (nextTick - note.getTick()) * speed));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
                System.out.println("note:"+note.getNote());


            }


        } catch (InvalidMidiDataException | IOException | MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
    static Robot robot;

    {

    }

    //按照钢琴按键对应原神键位的范围的设定，将钢琴88个音符压缩成12*3=36个音符，但还包含半音
    public static int to36Key(int noteNum) {
        noteNum -= 47;
        if (noteNum <= -24) {
            return 1;
        } else if (noteNum >= -23 && noteNum <= -12) {
            return noteNum + 24;
        } else if (noteNum >= -11 && noteNum <= 0) {
            return noteNum + 12;
        } else if (noteNum >= 1 && noteNum <= 12) {
            return noteNum;
        } else if (noteNum >= 13 && noteNum <= 24) {
            return noteNum;
        } else if (noteNum >= 25 && noteNum <= 36) {
            return noteNum;
        } else if (noteNum >= 37 && noteNum <= 48) {
            return noteNum - 12;
        } else if (noteNum >= 49 && noteNum <= 60) {
            return noteNum - 24;
        }
        return -1;
    }

    //根据黑键的设定，36键进一步压缩成21键，21键即原神琴的键数
    public static int[] toLyreKey(int noteNum) {
        int magnification = 0;
        int noteNum_12 = 1;
        if (noteNum <= 12) {
            magnification = 0;
            noteNum_12 = noteNum;
        } else if (noteNum >= 13 && noteNum <= 24) {
            magnification = 1;
            noteNum_12 = noteNum - 12;
        } else if (noteNum >= 25 && noteNum <= 36) {
            magnification = 2;
            noteNum_12 = noteNum - 24;
        }

        if (noteNum_12 == 1) {
            return new int[]{1 + (7 * magnification)};
        } else if (noteNum_12 == 2) {
            return blackKey(1, magnification);
        } else if (noteNum_12 == 3) {
            return new int[]{2 + (7 * magnification)};
        } else if (noteNum_12 == 4) {
            return blackKey(2, magnification);
        } else if (noteNum_12 == 5) {
            return new int[]{3 + (7 * magnification)};
        } else if (noteNum_12 == 6) {
            return new int[]{4 + (7 * magnification)};
        } else if (noteNum_12 == 7) {
            return blackKey(4, magnification);
        } else if (noteNum_12 == 8) {
            return new int[]{5 + (7 * magnification)};
        } else if (noteNum_12 == 9) {
            return blackKey(5, magnification);
        } else if (noteNum_12 == 10) {
            return new int[]{6 + (7 * magnification)};
        } else if (noteNum_12 == 11) {
            return blackKey(6, magnification);
        } else if (noteNum_12 == 12) {
            return new int[]{7 + (7 * magnification)};
        }
        return null;
    }

    //按照黑键的设定，按照黑键左边的白键为索引
    private static int[] blackKey(int noteNum_7, int magnification) {
        return new int[]{noteNum_7 + (7 * magnification)};
    }

}
