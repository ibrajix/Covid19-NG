package models;

public class States {
    private String state_name;
    private String state_cases;

    public States(){

    }

    public States(String state_name, String state_cases) {
        this.state_name = state_name;
        this.state_cases = state_cases;
    }

    public String getState_name() {
        return state_name;
    }

    public String getState_cases() {
        return state_cases;
    }


    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public void setState_cases(String state_cases) {
        this.state_cases = state_cases;
    }

}
