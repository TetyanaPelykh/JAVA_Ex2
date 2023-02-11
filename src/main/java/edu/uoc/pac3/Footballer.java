package edu.uoc.pac3;

public class Footballer {
    private int id;
    private static int nextId; //Podriamos quitar el bloque "static" y aqui poner nextId = 0;
    private String name;
    private int number;

    private Team team;

    static {
        nextId = 0;
    }

    public Footballer() throws Exception{
        this("Foo", 10);
    }

    public Footballer(String name, int number) throws Exception{
        setId();
        setName(name);
        setNumber(number);
    }

    public int getId() {
        return id;
    }

    private void setId() {
        this.id = Footballer.nextId;
        incNextId();
    }

    public static int getNextId() {
        return nextId;
    }

    private void incNextId(){
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws Exception{
        if(number < 1 || number > 99) {
            throw new Exception("[ERROR] Footballer's number must be in range [1,99]");
        }
        this.number = number;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) throws Exception{
        if(team!=null) {
            if(team.getGapByFootballer(this)==-1) {
                team.addFootballer(this);
            }
        }

        if(this.team!=null && this.team != team && this.team.getGapByFootballer(this) != -1) {
            this.team.removeFootballer(this);
        }

        this.team = team;
    }

    public String getInfo() {
        return getNumber()+"-"+getName();
    }

}
