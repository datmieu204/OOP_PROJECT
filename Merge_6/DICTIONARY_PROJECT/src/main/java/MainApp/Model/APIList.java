package MainApp.Model;

import java.util.ArrayList;

public class APIList {

    private ArrayList<String> list = new ArrayList<String>(); 

    public APIList() {

    }

    public APIList(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getListAPI() {
        return this.list;
    }

    public void setListAPI(ArrayList<String> list) {
        this.list = list;
    }

    public void addAPIString(String api) {
        this.list.add(api);
    }

}
