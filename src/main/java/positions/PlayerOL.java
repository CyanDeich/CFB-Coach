package positions;

import java.util.ArrayList;

import simulation.Team;

/**
 * Class for the OL player. 5 on field at a time.
 *
 * @author Achi
 */
public class PlayerOL extends Player {

    //OLPow affects how strong he is against defending DL
    public int ratStrength;
    //OLBkR affects how well he blocks for running plays
    public int ratRunBlock;
    //OLBkP affects how well he blocks for passing plays
    public int ratPassBlock;
    public int ratAwareness;

    public int statsSacksAllowed;
    public int statsRushYards;
    public int statsPassYards;
    public int statsRushSnaps;
    public int statsPassSnaps;

    //Size Config
    private final int hAvg = 76;
    private final int hMax = 5;
    private final int hMin = -3;
    private final int wAvg = 310;
    private final int wMax = 40;
    private final int wMin = -35;

    public PlayerOL(Team t, String nm, int yr, int reg, int trait, int iq, int scout, boolean transfer, boolean wasRS, int pot, int dur, boolean rs, int pow, int bkr, int bkp, int awr, int h, int w) {
        position = "OL";
        team = t;
        name = nm;
        year = yr;
        ratPot = pot;
        ratFootIQ = iq;
        ratDur = dur;
        ratStrength = pow;
        ratRunBlock = bkr;
        ratPassBlock = bkp;
        ratAwareness = awr;
        isRedshirt = rs;
        wasRedshirt = wasRS;
        height = h;
        weight = w;
        ratOvr = getOverall();

        homeState = reg;
        personality = trait;
        recruitRating = scout;

        resetSeasonStats();
        resetCareerStats();
    }


    public PlayerOL(Team t, String nm, int yr, int reg, int trait, int iq, int scout, boolean transfer, boolean wasRS, boolean wo, int pot, int dur, boolean rs, int cGamesPlayed, int cWins, int cHeismans, int cAA, int cAC, int cTF, int cAF,
                         int pow, int bkr, int bkp, int awr, int h, int w) {
        position = "OL";
        team = t;
        name = nm;
        year = yr;
        ratPot = pot;
        ratFootIQ = iq;
        ratDur = dur;
        ratStrength = pow;
        ratRunBlock = bkr;
        ratPassBlock = bkp;
        ratAwareness = awr;
        isRedshirt = rs;
        wasRedshirt = wasRS;

        isTransfer = transfer;
        isWalkOn = wo;
        homeState = reg;
        personality = trait;
        troubledTimes = 0;
        recruitRating = scout;
        height = h;
        weight = w;
        ratOvr = getOverall();

        resetSeasonStats();

        careerGames = cGamesPlayed;
        careerHeismans = cHeismans;
        careerAllAmerican = cAA;
        careerAllConference = cAC;
        careerTopFreshman = cTF;
        careerAllFreshman = cAF;
        careerWins = cWins;
    }

    public PlayerOL(Team t, String nm, int yr, int reg, int trait, int iq, int scout, boolean transfer, boolean wasRS, boolean wo, int pot, int dur, boolean rs, int cGamesPlayed, int cWins, int cHeismans, int cAA, int cAC, int cTF, int cAF,
                    int pow, int bkr, int bkp, int awr, int h, int w,
                    int seaGames, int seaGPlayed, int seaWins, boolean inj, boolean medRS, int tt, int ratImp, boolean wonHeis, boolean wonAA, boolean wonAC, boolean wonTF, boolean wonAF) {
        position = "OL";
        team = t;
        name = nm;
        year = yr;
        ratPot = pot;
        ratFootIQ = iq;
        ratDur = dur;
        ratStrength = pow;
        ratRunBlock = bkr;
        ratPassBlock = bkp;
        ratAwareness = awr;
        isRedshirt = rs;
        wasRedshirt = wasRS;

        isTransfer = transfer;
        isWalkOn = wo;
        homeState = reg;
        personality = trait;
        troubledTimes = 0;
        recruitRating = scout;
        height = h;
        weight = w;
        ratOvr = getOverall();

        resetSeasonStats();

        careerGames = cGamesPlayed;
        careerHeismans = cHeismans;
        careerAllAmerican = cAA;
        careerAllConference = cAC;
        careerTopFreshman = cTF;
        careerAllFreshman = cAF;
        careerWins = cWins;

        gamesStarted = seaGames;
        gamesPlayed = seaGPlayed;
        statsWins = seaWins;

        isInjured = inj;
        isMedicalRS = medRS;
        troubledTimes = tt;
        ratImprovement = ratImp;

        wonHeisman = wonHeis;
        wonAllAmerican = wonAA;
        wonAllConference = wonAC;
        wonTopFreshman = wonTF;
        wonAllFreshman = wonAF;

    }


    public PlayerOL(String nm, int yr, int stars, Team t) {
        position = "OL";
        height = hAvg + (int) (Math.random() * ((hMax - hMin) + 1)) + hMin;
        weight = wAvg + (int) (Math.random() * ((wMax - wMin) + 1)) + wMin;
        name = nm;
        year = yr;
        team = t;

        wasRedshirt = getWasRedshirtStatus();

        createGenericAttributes();

        ratStrength = (int) (ratBase + year * yearFactor + stars * starFactor - ratTolerance * Math.random());
        ratRunBlock = (int) (ratBase + year * yearFactor + stars * starFactor - ratTolerance * Math.random());
        ratPassBlock = (int) (ratBase + year * yearFactor + stars * starFactor - ratTolerance * Math.random());
        ratAwareness = (int) (ratBase + year * yearFactor + stars * starFactor - ratTolerance * Math.random());
        ratOvr = getOverall();

        recruitRating = getScoutingGrade();

        recruitTolerance = (int) ((60 - team.teamPrestige) / olImportance);
        cost = getInitialCost();
        cost = (int) (cost / olImportance);
        cost = getLocationCost();
        if (cost < 0) cost = (int) Math.random() * 5 + 1;

        resetSeasonStats();
        resetCareerStats();

    }

    public PlayerOL(String nm, int yr, int stars, Team t, boolean custom) {
        position = "OL";
        height = hAvg + (int) (Math.random() * ((hMax - hMin) + 1)) + hMin;
        weight = wAvg + (int) (Math.random() * ((wMax - wMin) + 1)) + wMin;
        name = nm;
        year = yr;
        team = t;

        wasRedshirt = getWasRedshirtStatus();

        ratPot = (int) (attrBase + 50 * Math.random());
        ratFootIQ = (int) (attrBase + 50 * Math.random());
        ratDur = (int) (attrBase + 50 * Math.random());
        ratStrength = (int) (ratBase + stars * customFactor - ratTolerance * Math.random());
        ratRunBlock = (int) (ratBase + stars * customFactor - ratTolerance * Math.random());
        ratPassBlock = (int) (ratBase + stars * customFactor - ratTolerance * Math.random());
        ratAwareness = (int) (ratBase + stars * customFactor - ratTolerance * Math.random());
        ratOvr = getOverall();
        homeState = (int) (Math.random() * 5);
        personality = (int) (attrBase + 50 * Math.random());

        if (custom) isWalkOn = true;
        recruitRating = getScoutingGrade();

        resetSeasonStats();
        resetCareerStats();
    }

    public void midSeasonProgression() {
        final int ratOvrStart = ratOvr;
        progression = getProgressionOff();
        double games = getMidSeasonBonus();

        ratFootIQ += (int) (Math.random() * games);
        ratStrength += (int) (Math.random() * games);
        ratRunBlock += (int) (Math.random() * games);
        ratPassBlock += (int) (Math.random() * games);
        ratAwareness += (int) (Math.random() * games);

        ratOvr = getOverall();
        ratImprovement = ratOvr - ratOvrStart;
    }


    @Override
    public void advanceSeason() {
        double games = getGamesBonus();

        if (!isMedicalRS) {
            if (wonAllConference) ratPot += (int) Math.random() * allConfPotBonus;
            if (wonAllAmerican) ratPot += (int) Math.random() * allAmericanBonus;
            if (wonAllFreshman) ratPot += (int) Math.random() * allFreshmanBonus;
            if (wonTopFreshman) ratPot += (int) Math.random() * topBonus;
            if (wonHeisman) ratPot += (int) Math.random() * topBonus;
            progression = getProgressionOff();

            if (year > 2 && games < minGamesPot) ratPot -= (int) (Math.random() * 15);

            ratFootIQ += (int) (Math.random() * (progression + games - endseason)) / endseasonFactor;
            ratStrength += (int) (Math.random() * (progression + games - endseason)) / endseasonFactor;
            ratRunBlock += (int) (Math.random() * (progression + games - endseason)) / endseasonFactor;
            ratPassBlock += (int) (Math.random() * (progression + games - endseason)) / endseasonFactor;
            ratAwareness += (int) (Math.random() * (progression + games - endseason)) / endseasonFactor;
            if (Math.random() * 100 < progression) {
                //breakthrough
                ratStrength += (int) (Math.random() * (progression + games - endseasonBonus)) / endseasonFactor;
                ratRunBlock += (int) (Math.random() * (progression + games - endseasonBonus)) / endseasonFactor;
                ratPassBlock += (int) (Math.random() * (progression + games - endseasonBonus)) / endseasonFactor;
                ratAwareness += (int) (Math.random() * (progression + games - endseasonBonus)) / endseasonFactor;
            }

            durabilityProgression();

        }

        ratOvr = getOverall();
        ratImprovement = ratOvr - ratOvrStart;

        careerGames += gamesPlayed;
        careerWins += statsWins;

        addSeasonAwards();
        checkRedshirt();
    }

    private void resetSeasonStats() {
        gamesStarted = 0;
        gamesPlayed = 0;
        isInjured = false;
        troubledTimes = 0;

        wonHeisman = false;
        wonAllAmerican = false;
        wonAllConference = false;
        wonAllFreshman = false;
        wonTopFreshman = false;
        statsWins = 0;
    }

    private void resetCareerStats() {
        careerGames = 0;
        careerHeismans = 0;
        careerAllAmerican = 0;
        careerAllConference = 0;
        careerAllFreshman = 0;
        careerTopFreshman = 0;
        careerWins = 0;
    }


    @Override
    public int getHeismanScore() {
        int teamFactor = 0;
        if(gamesPlayed < 0) return 0;
        teamFactor = (int)( (gamesPlayed*50 + gamesStarted*250) + (+100*getYardsPerPass() +200*getYardsPerRush() -75*statsSacksAllowed) );
        return ratOvr * 100 + teamFactor + getConfPrestigeBonus();
    }

    @Override
    public int getCareerScore() {
        return ratOvr * (year) * 50;
    }

    @Override
    public ArrayList<String> getDetailAllStatsList() {
        ArrayList<String> pStats = stringPlayerInfo();
        pStats.add(" > ");
        pStats.add("[B]PLAYER RATINGS");
        ArrayList<String> attributes = stringPlayerAttributes();
        for(String a : attributes) {
            pStats.add(a);
        }
        pStats.add("Strength: " + getLetterGrade(ratStrength) + ">Run Block: " + getLetterGrade(ratRunBlock));
        pStats.add("Awareness: " + getLetterGrade(ratAwareness) + ">Pass Block: " + getLetterGrade(ratPassBlock));
        pStats.add(" > ");
        pStats.add("[B]SEASON STATS");
        pStats.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesStarted - statsWins) + ")" + "> ");
        pStats.add("Run Snaps: " + statsRushSnaps + ">Run Yards: " + statsRushYards);
        pStats.add("Pass Snaps: " + statsPassSnaps + ">Pass Yards: " + statsPassYards);
        pStats.add("Run YPD: " + df2.format(getYardsPerRush()) + " YPD>Pass YPD: " + df2.format(getYardsPerPass()) + " YPD");
        pStats.add("Sacks Allowed: " + statsSacksAllowed + "> ");
        pStats.add(" > ");
        pStats.add("[B]CAREER STATS");
        pStats.addAll(getCareerStatsList());
        return pStats;
    }

    @Override
    public String getInfoForLineup() {
        if (injury != null)
            return getInitialName() + " [" + getYrStr() + "] " + ratOvr + "/" + getPotRating(team.HC.get(0).ratTalent) + " " + injury.toString();
        return getInitialName() + " [" + getYrStr() + "] " + ratOvr + "/" + getPotRating(team.HC.get(0).ratTalent) + " (" +
                getLetterGrade(ratStrength) + ", " + getLetterGrade(ratRunBlock) + ", " + getLetterGrade(ratPassBlock) + ", " + getLetterGrade(ratAwareness) + ")";
    }

    public int getOverall() {
        int ovr;
        ovr = (ratStrength * 3 + ratRunBlock * 2 + ratPassBlock * 2 + ratAwareness) / 8;
        return ovr;
    }

    public float getYardsPerRush() {
        if (statsRushSnaps < 1) {
            return 0;
        } else {
            float ypa = (float)(statsRushYards) / (statsRushSnaps);
            return ypa;
        }
    }

    public float getYardsPerPass() {
        if (statsPassSnaps < 1) {
            return 0;
        } else {
            float ypa = (float)(statsPassYards) / (statsPassSnaps);
            return ypa;
        }
    }

}
