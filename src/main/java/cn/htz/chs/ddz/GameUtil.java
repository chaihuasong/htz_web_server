package cn.htz.chs.ddz;

public class GameUtil {
    //card1 < card2 then return true
    public static boolean compareById(int card1, int card2) {
        if (card2 == 53) return true;
        if (card1 == 53) return false;
        if (card2 == 52 && card1 != 53) return true;
        if (card1 == 52 && card2 != 53) return false;
        int value1 = card1 % 13;
        int value2 = card2 % 13;
        //console.log("[",this.getPokeById(card1), "]:",value1,"[",this.getPokeById(card2), "]:",value2);

        if (value1 > 1 && value1 < value2) return true;

        if (value1 == 0 && (value2 == 1 || card2 == 52)) return true;

        if (value1 == 1 && card2 == 52) return true;

        if (value2 == 0 && value1 != 1 && value1 != 52 && value1 != 53) return true;

        if (value2 == 1 && value1 != 52 && value1 != 53) return true;

        return false;
    }

    public static int getLastPerson(int person) {
        int last = person - 1;
        if (last < 0) last = 2;
        return last;
    }

    public static int getNextPerson(int person) {
        int next = person + 1;
        if (next > 2) next = 0;
        return next;
    }
}
