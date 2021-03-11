package trivia.assignment.appscrip.Modal;

public class Quiz_Modal {

    int id;
    String userName, date, time, best_cricketer, colors;

    public Quiz_Modal(int id, String userName, String best_cricketer, String colors, String date, String time)
    {
        this.id = id;
        this.userName = userName;
        this.best_cricketer = best_cricketer;
        this.colors = colors;
        this.date = date;
        this.time = time;
    }

    public Quiz_Modal(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBest_cricketer() {
        return best_cricketer;
    }

    public void setBest_cricketer(String best_cricketer) {
        this.best_cricketer = best_cricketer;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }
}
