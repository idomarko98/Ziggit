import java.util.List;

public class Schedule {

    public void build(String h, Lesson[][] s, List<combination> l){
        if(h.equals("")) {
            combination c=new combination(s);
            l.add(c);
            return;
        }
        String[] lines= h.split("\n");
        String[] strings=lines[0].split("\\*");
        String name=strings[0];
        int course=Integer.parseInt(strings[1].split("\\[")[0]);
        String[] lectures=lines[0].split("\\[")[1].split("/");
        lectures[lectures.length-1]=lectures[lectures.length-1].substring(0,lectures[lectures.length-1].length()-1);
        for(int i=0;i<lectures.length;i++){
            Lesson[][] s1=new Lesson[s.length][s[0].length];
            for(int i1=0;i1<s.length;i1++){
                for (int i2=0;i2<s[0].length;i2++){
                    s1[i1][i2]=new Lesson(s[i1][i2]);
                }
            }
            int day=Integer.parseInt(lectures[i].charAt(0)+"");
            String[] m=lectures[i].split("\\.");
            String k=m[1].split("\\(")[0];
            int startH=Integer.parseInt(k.split("-")[0]);
            int endH=Integer.parseInt(k.split("-")[1]);
            for(;startH<endH;startH++) {
                if (s1[startH - 8][day - 1].isTaken())
                    break;
                else
                    s1[startH - 8][day - 1].setCourse(course,name,0);
            }
            if(!(startH==endH))
                continue;
            if(lectures[i].contains("(")){
                String[] t=lectures[i].split("\\|");
                t[0]=t[0].split("\\(")[1];
                t[t.length-1]=t[t.length-1].split("\\)")[0];
                for(int j=0;j<t.length; j++) {
                    Lesson[][] s2 = new Lesson[s1.length][s1[0].length];
                    for (int i1 = 0; i1 < s1.length; i1++) {
                        for (int i2 = 0; i2 < s1[0].length; i2++) {
                            s2[i1][i2] = new Lesson(s1[i1][i2]);
                        }
                    }
                    day=Integer.parseInt(t[j].charAt(0)+"");
                    m=t[j].split("\\.");
                    k=m[1];
                    startH=Integer.parseInt(k.split("-")[0]);
                    endH=Integer.parseInt(k.split("-")[1]);
                    for(;startH<endH;startH++) {
                        if (s2[startH - 8][day - 1].isTaken())
                            break;
                        else
                            s2[startH - 8][day - 1].setCourse(course,name,1);
                    }
                    if(!(startH==endH))
                        continue;
                    String n="";
                    for(int v=1;v<lines.length;v++){
                        n+=lines[v];
                        n+="\n";
                    }
                    build(n,s2,l);
                }

            }
            else{
                String n="";
                for(int j=1;j<lines.length;j++){
                    n+=lines[j];
                    n+="\n";
                }
                build(n,s1,l);
            }
        }
    }




}
