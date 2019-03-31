import java.io.*;
import java.util.*;

public class Model {

    private List<String> courses;
    private List<String> chosenCourses;
    private boolean wasFound = true;

    public Model(){
        courses=new LinkedList<>();
        chosenCourses = new LinkedList<>();
        try {
            int i=0;
            InputStream inputStream = this.getClass().getResourceAsStream("/read.txt");
            //FileInputStream fstream = new FileInputStream("C:\\Users\\Ido\\Desktop\\new 2.txt");
            DataInputStream in=new DataInputStream(/*fstream*/inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String string=strLine;
                courses.add(string);
            }
        }catch (IOException e){e.printStackTrace();}
    }

    public void searchCourse(String course){
        if(Character.isDigit(course.charAt(0))){
            searchByCode(course);
        }
        else{
            searchByName(course.replace(" ","_"));
        }
    }

    private void searchByName(String course) {
        if(CheckIfAdded(course))
            return;
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).contains(course)){
                chosenCourses.add(courses.get(i));
                wasFound = true;
                return;
            }
        }
        wasFound = false;
    }

    private void searchByCode(String course) {
        if(CheckIfAdded(course))
            return;
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).contains(course)){
                chosenCourses.add(courses.get(i));
                wasFound = true;
                return;
            }
        }
        wasFound = false;
    }

    private boolean CheckIfAdded(String courseSearch) {
        for(String course: chosenCourses) {
            if (course.contains(courseSearch))
                return true;
        }
        return false;
    }

    public String getLastAddedSearch() {
        if(!wasFound)
            return "";
        else{
            String temp = chosenCourses.get(chosenCourses.size()-1).split("\\*")[0];
            return temp.replace("_", " ").replace("~","");
        }
    }

    public void setSearchFound(boolean b) {
        wasFound = b;
    }

    public Queue<combination> createSchedule(boolean morning, boolean evening, boolean windows) {
        List<combination> c=new LinkedList<>();

        String string="";
        for(String s:chosenCourses){
            string += s;
            string+="\n";
        }
        Schedule s=new Schedule();
        Lesson[][] l=new Lesson[12][5];
        for(int i=0;i<12;i++){
            for(int j=0;j<5; j++){
                l[i][j]=new Lesson();
            }
        }
        s.build(string,l,c);
        if(morning){
            if(windows)
                return sortByMorningAndWindows(c,false);
            else
                return sortByMorning(c,false);
        }
        else if(evening)
            return sortByMorning(c,true);
        else if(windows)
            return sortByWindow(c,false);
        else{
            Queue<combination> topFive=new LinkedList<>();
            int i=0;
            for(combination d:c){
                if(i==5)
                    break;
                i++;
                ((LinkedList<combination>) topFive).add(d);
            }
            return topFive;
        }
    }

    private Queue<combination> sortByMorningAndWindows(List<combination> c, boolean desc) {
        Queue<combination> q=new LinkedList<>();
        Map<Double, combination> map=new LinkedHashMap<>();
        for (int i=0; i<c.size(); i++){
            double d=c.get(i).getWindowScore()+c.get(i).getMorningScore();
            while (map.containsKey(d))
                d+=0.01;
            map.put(d,c.get(i));
        }
        TreeSet<Double> t=new TreeSet<Double>(map.keySet());
        if(desc)
            t=(TreeSet)t.descendingSet();
        int i=0;
        for(double d:t){
            if(i==5)
                break;
            i++;
            ((LinkedList<combination>) q).add(map.get(d));
        }
        return q;
    }

    public Queue<combination> sortByWindow(List<combination> c, boolean desc){
        Queue<combination> q=new LinkedList<>();
        Map<Double, combination> map=new LinkedHashMap<>();
        for (int i=0; i<c.size(); i++){
            double d=c.get(i).getWindowScore();
            while (map.containsKey(d))
                d+=0.01;
            map.put(d,c.get(i));
        }
        TreeSet<Double> t=new TreeSet<Double>(map.keySet());
        if(desc)
            t=(TreeSet)t.descendingSet();
        int i=0;
        for(double d:t){
            if(i==5)
                break;
            i++;
            ((LinkedList<combination>) q).add(map.get(d));
        }
        return q;
    }

    public Queue<combination> sortByMorning(List<combination> c, boolean desc){
        Queue<combination> q=new LinkedList<>();
        Map<Double, combination> map=new LinkedHashMap<>();
        for (int i=0; i<c.size(); i++){
            double d=c.get(i).getMorningScore();
            while (map.containsKey(d))
                d+=0.01;
            map.put(d,c.get(i));
        }
        TreeSet<Double> t=new TreeSet<Double>(map.keySet());
        if(desc)
            t=(TreeSet)t.descendingSet();
        int i=0;
        for(double d:t){
            if(i==5)
                break;
            i++;
            ((LinkedList<combination>) q).add(map.get(d));
        }
        return q;
    }
}
