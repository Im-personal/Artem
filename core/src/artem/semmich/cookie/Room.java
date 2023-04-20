package artem.semmich.cookie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Room {

    public Animation [][] bgmatrix;
    public Animation[] map;
    public boolean [][] wall;
    public ArrayList<Thing> objcts = new ArrayList<>();
    static int numall=0;
    int id=0;
    public boolean isSolid = false;
    public Room(String folder, Animation[] map)
    {
        FileHandle file = Gdx.files.internal(folder+"/data.map");

        char[] data = file.readString().toCharArray();
        int w=0,h=1;

        for(;data[w]!='\n';w++);
        w--;
        for(int i = 0; i<data.length;i++)if(data[i]=='\n')h++;
        bgmatrix = new Animation[w][h];
        wall = new boolean[w][h];
        w=0;h=0;
        for(int i = 0; i<data.length;i++)
        {
            if(data[i]=='\n') {
                h++;w=0;
            }else if((data[i] - '0')<map.length&&(data[i] - '0')>=0){
               // System.out.println("dfghjkjoijlhkguthrsgdgtfhyjj;0ufdsaadfghjkljuythgrdesfvnhjk: "+data[i]);
                bgmatrix[w][h] = map[(int) (data[i] - '0')];
                w++;
            }
        }

        file = Gdx.files.internal(folder+"/data.wall");

        data = file.readString().toCharArray();
        w=0;h=0;
        for(int i = 0; i<data.length;i++)
        {
            if(data[i]=='\n') {
                h++;w=0;
            }else if((data[i] - '0')<map.length&&(data[i] - '0')>=0){
                wall[w][h] = data[i] == '1';
                w++;
            }
        }

        this.map = map;
        id = numall;
        numall++;
    }
    public Room(String folder, Animation[] map, boolean isSolid)
    {
        this(folder,map);
        this.isSolid=isSolid;
    }

    public void addThing(Thing t)
    {
        objcts.add(t);
    }

    public static class ThingComparator implements Comparator<Thing> {
        @Override
        public int compare(Thing o1, Thing o2) {
            return (int)((o1.y+o1.h)-(o2.y+o2.h));
        }
    }

    public void update()
    {

       Collections.sort(objcts, new ThingComparator());
        for(Animation a: map)a.update();
    }

    public boolean get(int x, int y)
    {
        if(x< wall.length&&x>=0&&y>=0&&y< wall[x].length)
            return wall[x][y];
        else
            return false;
    }

    public boolean notIntersect(float x, float y, float w, float h)
    {
        for(int i = 0; i<objcts.size();i++)
        {
            if(objcts.get(i).isSolid)
                if(objcts.get(i).isFull)
                {
                    if(objcts.get(i)!=Graph.artem&&ri(x,y,w,h,objcts.get(i).x,objcts.get(i).y,objcts.get(i).w,objcts.get(i).h))
                        return false;
                }else
                if(objcts.get(i)!=Graph.artem&&ri(x,y,w,h,objcts.get(i).x,objcts.get(i).y+(objcts.get(i).h-90),objcts.get(i).w,90))
                    return false;
        }
        return true;
    }

    public int getFromArea(float x, float y, float w, float h)
    {
        for(int i = 0; i<objcts.size();i++)
            if(!objcts.get(i).isNothing)
            if(objcts.get(i).isFull)
            {
                if(objcts.get(i)!=Graph.artem&&ri(x,y,w,h,objcts.get(i).x,objcts.get(i).y,objcts.get(i).w,objcts.get(i).h)) {

                    return objcts.get(i).event;

                }
            }else
            if(objcts.get(i)!=Graph.artem&&ri(x,y,w,h,objcts.get(i).x,objcts.get(i).y+(objcts.get(i).h-90),objcts.get(i).w,90)) {

                return objcts.get(i).event;
            }
        return 0;
    }
    private boolean
    ri(
            float x1, float y1, float w1, float h1,
            float x2, float y2, float w2, float h2 ) {

        return (x1<=x2+w2&&x1+w1>=x2)&&(y1<=y2+h2&&y1+h1>=y2);
    }
}
