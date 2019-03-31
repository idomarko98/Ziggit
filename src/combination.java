public class combination {

    private Lesson[][] schedule;
    private int morningScore;
    private int windowScore;

    public combination (Lesson[][] s){
        schedule=s;
        calculateMorningScore();
        calculateWindowScore();
    }

    public int getWindowScore(){
        return windowScore;
    }

    public Lesson[][] getSchedule() {
        return schedule;
    }

    public int getMorningScore(){
        return morningScore;
    }

    private void calculateWindowScore() {
        for(int j=0; j<schedule[0].length; j++){
            int w=0;
            boolean s=false;
            for (int i=0; i<schedule.length; i++){
                if(schedule[i][j].isTaken()){
                    windowScore+=w;
                    s=true;
                    w=0;
                }
                else if(s){
                    w++;
                }
            }
        }
    }


    private void calculateMorningScore() {
        for(int j=0; j<schedule[0].length; j++){
            int w=0;
            for (int i=0; i<schedule.length; i++){
                if(schedule[i][j].isTaken()){
                    morningScore+=w;
                    break;
                }
                else {
                    w++;
                }
            }
        }
    }

    public void print() {
        for(int i=0; i<=5; i++){
            if(i==0)
                System.out.print("|           |");
            else{
                System.out.print("|     " +(i)+ "    |");
            }
        }
        System.out.println();
        for(int i=0; i<schedule.length; i++){
            for(int j=0; j<=schedule[0].length; j++){
                if(j==0) {
                    if(i==0 || i==1)
                        System.out.print("|     0" + (8 + i) + "    |");
                    else
                        System.out.print("|     " + (8 + i) + "    |");
                }
                else{
                    if(schedule[i][j-1].isTaken())
                        System.out.print("| "+schedule[i][j-1].getCourse()+" |");
                    else{
                        System.out.print("|          |");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
