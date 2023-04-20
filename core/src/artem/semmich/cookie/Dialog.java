package artem.semmich.cookie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Dialog {
    String str;
    int lps = 10;
    float time=0;
    Animation face;
    Sound voice;
    public boolean isOpened=true;
    public boolean isFinished=false;
    public BitmapFont font;
    public Dialog(String str, Animation face, int lps, Sound voice, BitmapFont font)
    {
        this.str = str;
        this.face = face;
        this.lps = lps;
        this.voice = voice;
        this.font = font;
    }

    boolean letterchanged=false;
    int lastlen=0;

    void update()
    {
        time+= Gdx.graphics.getDeltaTime();
            String str = getStr();
            boolean soundless=false;
            if(str.length()>0) {
                char lastlet = str.toCharArray()[str.length() - 1];
                soundless = (lastlet == '.' || lastlet == ' ' || lastlet == '?' || lastlet == ',' || lastlet == '!' || lastlet == '*' || lastlet == '\n');
            }

            letterchanged=((int)Math.min((int)(lps*time),str.length())>lastlen)&&!soundless;
            lastlen=(int)Math.min((int)(lps*time),str.length());

            if(face!=null)
            if((lps*time)<this.str.length()&&!soundless) {
                face.update();
            }else face.setFrame(0);

    }

    String getStr()
    {
       return str.substring(0,Math.min((int)(lps*time),str.length()));
    }

    void close()
    {
        isOpened=false;
    }


    public boolean omoristyle=false;
    public Texture omoface;
    public String name;
    public Dialog(String str, Texture face, int lps, String name)
    {
        this.str = str;
        omoface = face;
        this.lps = lps;
        omoristyle=true;
        this.name = name;
    }
}
