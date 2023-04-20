package artem.semmich.cookie;

import com.badlogic.gdx.graphics.Texture;

public class Button {
    private int x,y,w,h,depth=0;
    private Texture img;
    private Texture pushedImg;


    public Button(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public Button(Texture img, int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.img = img;
        pushedImg=img;
    }

    public Button(int x, int y, int w, int h,int depth)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.depth = depth;
    }

    public Button(Texture img, int x, int y, int w, int h,int depth)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.img = img;
        this.depth = depth;
        pushedImg=img;
    }


    public boolean isPushed()
    {

        boolean res = false;
        for(int i = 0; i<10;i++)
        if(Mistener.onScreena[i])
        {
            if( ((Mistener.txa[i]>=x*Graph.mnX&&Mistener.txa[i]<=(x+w)*Graph.mnX)&&
                    (Mistener.tya[i]>=y*Graph.mnY&&Mistener.tya[i]<=(y+h)*Graph.mnY)) )
            res = true;
        }


        return res;
    }




    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getW()
    {
        return w;
    }

    public int getH()
    {
        return h;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setW(int w)
    {
        this.w = w;
    }

    public void setH(int h)
    {
        this.h = h;
    }

    public void setXY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setWH(int w, int h)
    {
        this.w = w;
        this.h = h;
    }

    public void setTexture(Texture txtr)
    {
        img = txtr;
    }

    public void setPushedTexture(Texture txtr)
    {
        pushedImg = txtr;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public int getDepth()
    {
        return depth;
    }

    public Texture getTexture()
    {
        return isPushed()? pushedImg:img;
    }

    public void setXYWH(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }
}
