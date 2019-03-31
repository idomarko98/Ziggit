public class Lesson {

    private int course;
    private boolean taken=false;
    private String name="";
    private int type;

    public  Lesson(){
        taken=false;
        name="_";
    }
    public Lesson(Lesson o){
        if(o.isTaken()) {
            taken = true;
            course=o.getCourse();
            name=o.getName();
            type=o.getType();
        }
    }

    public boolean isTaken(){
        return taken;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setCourse(int c, String n, int type){
        taken=true;
        course= c;
        name=n;
        this.type=type;
    }

    public int getCourse(){
        return course;
    }
}
