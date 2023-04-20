package artem.semmich.cookie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;

public class Mistener implements InputProcessor {
    static int tx=0,ty=0,ux=0,uy=0,dx=0,dy=0,mx=0,my=0;
    static int[] txa=new int[10];
    static int[] tya=new int[10];
    static int[] uxa=new int[10];
    static int[] uya=new int[10];
    static int[] mxa=new int[10];
    static int[] mya=new int[10];
    static boolean onScreen = false;
    static boolean[] onScreena = new boolean[10];

    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
     

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        tx = screenX;
        ty = screenY;
        mx = screenX;
        my = screenY;
        dx=0;
        dy=0;
       txa[pointer] = screenX;
       tya[pointer] = screenY;
        mxa[pointer] = screenX;
        mya[pointer] = screenY;

       onScreena[pointer] = true;

        onScreen = true;

        if(Graph.event==147)
        {
            Graph.nextEvent();
        }

        if(Graph.nowdialog!=null&&Graph.nowdialog.isOpened)
        {
            Dialog d = Graph.nowdialog;
            if(d.str.length()>d.lps*d.time)
                d.time = (float)d.str.length()/d.lps;
            else
                d.isFinished = true;

        }

        if(!Graph.blockmove)
        if(Graph.btn_Z.isPushed())
        {
            float areax=0,areay=0;
            Thing a = Graph.artem;
            if(Graph.artem.w==40)
            {
                switch (Graph.artem.nowanim)
                {
                    case 0://down
                        areax=a.x;
                        areay=a.y+55;
                        break;
                    case 1://up
                        areax=a.x;
                        areay=a.y;
                        break;
                    case 2://left
                        areax=a.x-40;
                        areay=a.y+55/2f;
                        break;
                    case 3://right
                        areax=a.x+40;
                        areay=a.y+55/2f;
                        break;
                }
            }else
            switch (Graph.artem.nowanim)
            {
                case 0://down
                    areax=a.x;
                    areay=a.y+90*2;
                    break;
                case 1://up
                    areax=a.x;
                    areay=a.y;
                    break;
                case 2://left
                    areax=a.x-90;
                    areay=a.y+90;
                    break;
                case 3://right
                    areax=a.x+90;
                    areay=a.y+90;
                    break;
            }

            Graph.nextEvent(Graph.nowroom.getFromArea(areax,areay,(Graph.artem.w==40?40:90), (Graph.artem.w==40?40:90)));
        }



        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(Graph.nowquestion!=null&&!Graph.nowquestion.isReplied)
        {
            if(dx>50) {
                Graph.nowquestion.isReplied = true;
                Graph.nowquestion.eventchoosen=Graph.nowquestion.event2;
            }else if(dx<-50) {
                Graph.nowquestion.isReplied = true;
                Graph.nowquestion.eventchoosen=Graph.nowquestion.event1;
            }
        }
        if(Graph.isBattleStarted&&!Graph.attack&&Graph.event==56)
        if(Graph.btn_battle[0].isPushed())
        {
            Graph.nextEvent(60);
        }else
        if(Graph.btn_battle[1].isPushed())
        {
            switch (Graph.numattack)
            {
                case 0:
                    Graph.nextEvent(58);
                    break;
                case 1:
                    Graph.nextEvent(62);
                    break;
                case 2:
                    Graph.nextEvent(64);
                    break;
                case 3:
                    Graph.nextEvent(67);
                    break;
            }
            Graph.numattack++;

        }else
        if(Graph.btn_battle[2].isPushed())
        {
           Graph.nextEvent(57);

        }else
        if(Graph.btn_battle[3].isPushed())
        {
            Graph.nextEvent(60);
        }else

        onScreen = false;
        ux = screenX;
        uy = screenY;

        uxa[pointer] = screenX;
        uya[pointer] = screenY;
        onScreena[pointer] = false;



        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        dx = screenX-tx;
        dy = screenY-ty;
        mx = screenX;
        my = screenY;

        mxa[pointer] = screenX;
        mya[pointer] = screenY;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
