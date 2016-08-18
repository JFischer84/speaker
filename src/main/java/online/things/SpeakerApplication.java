package online.things;

import com.tinkerforge.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpeakerApplication {

    private static final String HOST = "localhost";
    private static final int PORT = 4223;
    private static final String UIDS = "sxr";
    private static final String UIDD = "nZs";
    public static BrickletPiezoSpeaker ps;
    public static BrickletDistanceUS dist;
    private static volatile int pos;
    private static boolean blocked = false;
    private static boolean exit = false;
    private static int C = 1046;
    private static int D = 1174;
    private static int E = 1318;
    private static int F = 1396;
    private static int G = 1567;
    private static int GS = 1661;
    private static int A = 1760;
    private static int AS = 1864;
    private static int B = 1975;
    private static int CH = 2093;
    private static int CSH = 2217;
    private static int DH = 2349;
    private static int DSH = 2489;
    private static int EH = 2637;
    private static int FH = 2793;
    private static int FSH = 2959;
    private static int GH = 3135;
    private static int GSH = 3322;
    private static int AH = 3520;
    private static int MAX_DIFFERENCE = 100;

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SpeakerApplication.class, args);

        IPConnection ipcon = new IPConnection();
        ps = new BrickletPiezoSpeaker(UIDS, ipcon);
        dist = new BrickletDistanceUS(UIDD, ipcon);

        Song imperialMarch = new Song();

        //first Part
        imperialMarch.addNote(500, A);
        imperialMarch.addNote(500, A);
        imperialMarch.addNote(500, A);
        imperialMarch.addNote(350, F);
        imperialMarch.addNote(150, CH);
        imperialMarch.addNote(500, A);
        imperialMarch.addNote(350, F);
        imperialMarch.addNote(150, CH);
        imperialMarch.addNote(650, A);

        //second Part
        imperialMarch.addNote(500, EH);
        imperialMarch.addNote(500, EH);
        imperialMarch.addNote(500, EH);
        imperialMarch.addNote(350, FH);
        imperialMarch.addNote(150, CH);
        imperialMarch.addNote(500, GS);
        imperialMarch.addNote(350, F);
        imperialMarch.addNote(150, CH);
        imperialMarch.addNote(650, A);

        ipcon.connect(HOST, PORT);

        ps.addBeepFinishedListener(() -> {
            System.out.println("I am your Father");
            blocked = false;
        });

        while (!exit) {

            int firstSample = dist.getDistanceValue();
            Thread.sleep(50);
            int secondSample = dist.getDistanceValue();
            int difference = firstSample - secondSample;

            if (difference >= MAX_DIFFERENCE) {
                playSong(imperialMarch);
            }
            System.out.println(difference);
        }

        System.out.println("Press Key to exit.");
        System.in.read();

        ipcon.disconnect();

    }

    public static void playSong(Song song) throws Exception {
        pos = 0;

        while (pos < song.getNotes().size()) {

            if (!blocked) {
                Note note = song.getNotes().get(pos);
                pos++;

                ps.beep(note.getDuration(), note.getFrequency());
                blocked = true;
            }
            Thread.sleep(50);
        }
    }
}
