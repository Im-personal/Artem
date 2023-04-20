package artem.semmich.cookie;

public class Question {
    public String q1;
    public String q2;
    public int event1;
    public int event2;
    public boolean isReplied = false;
    public int eventchoosen=0;

    public Question(String q1, String q2,int event1, int event2)
    {
        this.q1=q1;
        this.q2=q2;
        this.event1=event1;
        this.event2 = event2;
    }

}
