package edu.uoc.pac3;

public class Team {
    private int id;
    private static int nextId  = 0;
    private String name;
    private String foundationYear;
    private String nif;
    private String email;
    private StatisticsTeam statisticsTeam;
    private int capacity;

    private Footballer[] footballers;

    public Team() throws Exception {
        this("Lorem club", "2000", "G12345678", "info@yourmail.com", 21);
    }

    public Team(String name, String foundationYear, String nif, String email, int capacity) throws Exception{
        setName(name);
        setFoundationYear(foundationYear);
        setNif(nif);
        setEmail(email);
        setCapacity(capacity);
        //we always initialize the statistics to 0
        this.statisticsTeam = new StatisticsTeam();
        setId();
    }

    public int getId() {
        return id;
    }

    private void setId() {
        this.id = getNextId();
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

    public void setName(String name) throws Exception{
        if(name.length()>50) {
            throw new Exception("[ERROR] Team's name cannot be longer than 50 characters");
        }else {
            this.name = name;
        }
    }

    public String getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(String foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) throws Exception{
        String regex = "[ABG][0-9]{8}";
        if(!nif.matches(regex)) {
            throw new Exception("[ERROR] Team's NIF pattern is incorrect");
        }
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        if (email.contains("@") && (email.endsWith(".es") || email.endsWith(".com"))) {
            this.email = email;
        }else {
            throw new Exception("[ERROR] Team's email pattern is incorrect");
        }
    }

    public StatisticsTeam getStatisticsTeam() {
        return statisticsTeam;
    }

    public void resetStatistics() throws Exception{
        this.statisticsTeam = new StatisticsTeam();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) throws Exception{
        if(capacity > 0) {
            if(!isEmpty()) {
                throw new Exception("[ERROR] Team is not empty, therefore you cannot change its capacity");
            }else {
                this.capacity = capacity;
                footballers = new Footballer[capacity];
            }
        }else {
            throw new Exception("[ERROR] Team's capacity must be greater than 0");
        }
    }

    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Footballer f: getFootballers()){
            if(f != null){
                stringBuilder.append(f.getInfo());
                stringBuilder.append(",");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return "Name: "+getName()+" - Foundation year: "+ getFoundationYear()+" - NIF: "+getNif() + " - Footballers: "+stringBuilder.toString();
    }

    public Footballer[] getFootballers() {
        return footballers;
    }

    public int getFirstFreeGap() {
        for(int i = 0; i < getCapacity(); i++) {
            if(footballers[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public void addFootballer(Footballer footballer) throws Exception{

        if(footballer==null) {
            //footballer null...
            throw new Exception("[ERROR] The footballer cannot be null");
        }

        if(getGapByFootballer(footballer)!=-1){
            //footballer exists...
            throw new Exception("[ERROR] This footballer is already in this team");
        }

        if(isFull()) {
            //team full...
            throw new Exception("[ERROR] This team is full");
        }

        int index = -1;
        index = getFirstFreeGap();

        if(footballer.getTeam()!=null) {
            //If the footballer already has a team...
            footballer.getTeam().removeFootballer(footballer);
        }
        footballers[index] = footballer;
        footballer.setTeam(this);
    }

    //addFootballer version 'else'
    /*public void addFootballer(Footballer footballer) throws Exception{
        int index = -1;
        if(footballer!=null) {
            if(getGapByFootballer(footballer)==-1) {
                if(!isFull()) {
                    index = getFirstFreeGap();

                    if(footballer.getTeam()!=null) {
                        //If the footballer already has a team...
                        footballer.getTeam().removeFootballer(footballer);
                    }
                    footballers[index] = footballer;
                    footballer.setTeam(this);
                }else {
                    //team full...
                    throw new Exception("[ERROR] This team is full");
                }
            }else {
                //footballer exists...
                throw new Exception("[ERROR] This footballer is already in this team");
            }
        }else {
            throw new Exception("[ERROR] The footballer cannot be null");
        }
    }*/

    public void removeFootballer(Footballer footballer) throws Exception{
        int lot = getGapByFootballer(footballer);
        if(lot!=-1) {
            footballers[lot] = null;
            footballer.setTeam(null);
        }else { //else: Footballer doesn't exist, then throws an Exception
            throw new Exception("[ERROR] This footballer does not exist in this team");
        }

    }

    public boolean isFull() {
        return getNumFreeGaps() == 0;

        // Alternative without getNumFreeGaps() method
        /*if(getFootballers() != null) {
            for(var footballer : getFootballers()) {
                if(footballer==null)
                    return false;
            }
        }else {
            return false;
        }
        return true;*/
    }

    public boolean isEmpty() {
        return getNumFreeGaps() == this.getCapacity();

        // Alternative without getNumFreeGaps() method
        /*if(getFootballers()!=null) {
            for(var footballer : getFootballers()) {
                if(footballer != null)
                    return false;
            }
        }
        return true;*/
    }

    public int getNumFreeGaps() {
        int num = 0;
        if(getFootballers() != null) {
            for (var footballer : getFootballers()) {
                if (footballer == null)
                    num++;
            }
        }
        return num;
    }

    public int getGapByFootballer(Footballer footballer) {
        for(int i = 0; i < getCapacity(); i++) {
            if(footballers[i] == footballer)
                return i;
        }
        return -1;
    }
}
