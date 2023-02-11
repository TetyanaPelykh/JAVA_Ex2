package edu.uoc.pac3;

public class StatisticsTeam {
    private int won;
    private int drawn;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;

    private static final int WON_POINTS = 3;
    private static final int DRAWN_POINTS = 1;
    private static final int LOST_POINTS = 0;

    public StatisticsTeam() throws Exception {
        setWon(0);
        setDrawn(0);
        setLost(0);
        incGoalsFor(0);
        incGoalsAgainst(0);
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) throws Exception {
        if(won<0) {
            throw new Exception("[ERROR] StatisticsTeam's won must be greater than or equal to 0");
        }else {
            this.won = won;
        }
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) throws Exception {
        if(drawn<0) {
            throw new Exception("[ERROR] StatisticsTeam's drawn must be greater than or equal to 0");
        }else {
            this.drawn = drawn;
        }
    }


    public int getLost() {
        return lost;
    }

    public void setLost(int lost) throws Exception {
        if(lost<0) {
            throw new Exception("[ERROR] StatisticsTeam's lost must be greater than or equal to 0");
        }else {
            this.lost = lost;
        }
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void incGoalsFor(int goalsFor) throws Exception {
        if(goalsFor<0) {
            throw new Exception("[ERROR] The number of goalsFor that you want to add must be greater than or equal to 0");
        }else {
            this.goalsFor += goalsFor;
        }
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void incGoalsAgainst(int goalsAgainst) throws Exception {
        if(goalsAgainst<0) {
            throw new Exception("[ERROR] The number of goalsAgainst that you want to add must be greater than or equal to 0");
        }else {
            this.goalsAgainst += goalsAgainst;
        }
    }

    //values calculated from attributes

    public int getGamesPlayed(){
        return won + drawn + lost;
    }

    public int getGoalsDifference() {
        return goalsFor - goalsAgainst;
    }

    public int getPoints() {
        return (this.won * WON_POINTS) + (this.drawn * DRAWN_POINTS) + (this.lost * LOST_POINTS);
    }
}
