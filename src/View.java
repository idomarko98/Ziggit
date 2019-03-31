import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;

public class View implements Initializable {

    private Model m=new Model();

    public VBox hours;
    public Button nextSched, create_btn;
    public Button prevSched;
    public CheckBox morning;
    public CheckBox evening;
    public CheckBox windows;
    public TextField courseSearch;
    public ListView<String> courses_lst;
    public ImageView search_img, logo_img;
    public Text txt00;
    public Text txt10;
    public Text txt20;
    public Text txt30;
    public Text txt40;
    public Text txt50;
    public Text txt60;
    public Text txt70;
    public Text txt80;
    public Text txt90;
    public Text txt100;
    public Text txt110;
    public Text txt01;
    public Text txt11;
    public Text txt21;
    public Text txt31;
    public Text txt41;
    public Text txt51;
    public Text txt61;
    public Text txt71;
    public Text txt81;
    public Text txt91;
    public Text txt101;
    public Text txt111;
    public Text txt02;
    public Text txt12;
    public Text txt22;
    public Text txt32;
    public Text txt42;
    public Text txt52;
    public Text txt62;
    public Text txt72;
    public Text txt82;
    public Text txt92;
    public Text txt102;
    public Text txt112;
    public Text txt03;
    public Text txt13;
    public Text txt23;
    public Text txt33;
    public Text txt43;
    public Text txt53;
    public Text txt63;
    public Text txt73;
    public Text txt83;
    public Text txt93;
    public Text txt103;
    public Text txt113;
    public Text txt04;
    public Text txt14;
    public Text txt24;
    public Text txt34;
    public Text txt44;
    public Text txt54;
    public Text txt64;
    public Text txt74;
    public Text txt84;
    public Text txt94;
    public Text txt104;
    public Text txt114;
    public GridPane dafna;
    Queue<combination> scheds;
    int num;



    public void search(){
        String course = courseSearch.getText();
        m.searchCourse(course);
        String listItem = m.getLastAddedSearch();
        if(listItem.equals(""))
            showErrorNotFound();
        else{
            if(!courses_lst.getItems().contains(listItem))
                courses_lst.getItems().add(listItem);
            else
                showAlert("Course Was already added");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setTitle(message);
        alert.show();
    }

    private void showErrorNotFound() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Course Not Found");
        alert.setTitle("Course Not Found");
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image magnify = new Image(this.getClass().getResourceAsStream("/images/mag.png"));
        search_img.setImage(magnify);

        Image logo = new Image(this.getClass().getResourceAsStream("/images/logo.png"));
        logo_img.setImage(logo);

        prevSched.setDisable(true);
        //dafna.setHalignment( HPos.CENTER);
    }

    public void searchClicked(MouseEvent mouseEvent) {
        search();
    }

    public void eveningClick(){
        if(evening.isSelected()) {
            morning.setDisable(true);
            windows.setDisable(true);
        }
        else{
            morning.setDisable(false);
            windows.setDisable(false);
        }
        //morning.setVisible(false);
        //windows.setVisible(false);
    }

    public void morningClick(){
        if(morning.isSelected())
            evening.setDisable(true);
        else
            evening.setDisable(false);
        //evening.setVisible(false);
    }

    public void windowClick(){
        //evening.setVisible(false);
        if(windows.isSelected())
            evening.setDisable(true);
        else
            evening.setDisable(false);
    }

    public void createSchedule(MouseEvent mouseEvent) {

        scheds=m.createSchedule(morning.isSelected(), evening.isSelected(), windows.isSelected());
        num=0;
        show();
    }

    public void nextSched(){

        if(num == 3){
            nextSched.setDisable(true);
        }
        if(prevSched.isDisable())
            prevSched.setDisable(false);

        num++;
        show();
    }

    public void prevSched(){
        if(num == 1){
            prevSched.setDisable(true);
        }
        if(nextSched.isDisable()){
            nextSched.setDisable(false);
        }
        num--;
        show();
    }

    private void show() {
        List<combination> l=(List<combination>)scheds;
        Lesson[][] schedule=l.get(num).getSchedule();

            txt00.setText(schedule[0][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt10.setText(schedule[1][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt20.setText(schedule[2][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt30.setText(schedule[3][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt40.setText(schedule[4][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt50.setText(schedule[5][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt60.setText(schedule[6][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt70.setText(schedule[7][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt80.setText(schedule[8][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt90.setText(schedule[9][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt100.setText(schedule[10][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt110.setText(schedule[11][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt01.setText(schedule[0][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt11.setText(schedule[1][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt21.setText(schedule[2][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt31.setText(schedule[3][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt41.setText(schedule[4][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt51.setText(schedule[5][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt61.setText(schedule[6][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt71.setText(schedule[7][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt81.setText(schedule[8][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt91.setText(schedule[9][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt101.setText(schedule[10][0].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt110.setText(schedule[11][1].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt02.setText(schedule[0][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt12.setText(schedule[1][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt22.setText(schedule[2][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt32.setText(schedule[3][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt42.setText(schedule[4][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt52.setText(schedule[5][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt62.setText(schedule[6][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt72.setText(schedule[7][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt82.setText(schedule[8][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt92.setText(schedule[9][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt102.setText(schedule[10][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt112.setText(schedule[11][2].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt03.setText(schedule[0][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt13.setText(schedule[1][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt23.setText(schedule[2][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt33.setText(schedule[3][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt43.setText(schedule[4][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt53.setText(schedule[5][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt63.setText(schedule[6][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt73.setText(schedule[7][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt83.setText(schedule[8][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt93.setText(schedule[9][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt103.setText(schedule[10][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt113.setText(schedule[11][3].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt04.setText(schedule[0][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt14.setText(schedule[1][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt24.setText(schedule[2][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt34.setText(schedule[3][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt44.setText(schedule[4][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt54.setText(schedule[5][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt64.setText(schedule[6][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt74.setText(schedule[7][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt84.setText(schedule[8][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt94.setText(schedule[9][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt104.setText(schedule[10][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
            txt114.setText(schedule[11][4].getName().replaceAll("_"," ").replaceAll("~","\n"));
    }

}
