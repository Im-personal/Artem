package artem.semmich.cookie;

import java.util.ArrayList;
import java.util.Comparator;

public class Thing{

    private static final Animation[] nothing;

    static{
        nothing = new Animation[]{new Animation("nothing",0)};
    }
    public boolean isNothing=false;
    public float x, y, w, h;
    public Animation[] anim;
    int nowanim=0;
    int event = 0;
    public boolean isSolid = false;
    public boolean isFull = false;

    public Thing(float x, float y, float w, float h, Animation[] anim, int event)
    {
        this.x =x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.anim = anim;
        this.event = event;
    }

    public Thing(float x, float y, float w, float h, Animation[] anim, int event,boolean isSolid)
    {
        this(x,y,w,h,anim,event);
        this.isSolid=isSolid;

    }

    public Thing(float x, float y, float w, float h, Animation[] anim, int event,boolean isSolid,boolean isFull)
    {
        this(x,y,w,h,anim,event);
        this.isSolid=isSolid;
        this.isFull=isFull;
    }

    public Thing(float x, float y, float w, float h)
    {
        this.x =x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.event = 0;
        this.anim=nothing;
        this.isSolid=true;
        this.isFull=true;
        isNothing=true;
    }

    public Animation getAnimation()
    {
        return anim[nowanim];
    }


    public void setSize(float x, float y, float w, float h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setFrame(int f)
    {
        anim[nowanim].setFrame(f);
    }

    public void update()
    {
        getAnimation().update();
    };

}
