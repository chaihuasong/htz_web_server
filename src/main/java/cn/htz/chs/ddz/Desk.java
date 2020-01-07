package cn.htz.chs.ddz;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Desk {

    private static final int POKE_COUNT = 54;
    private int deskId;
    private int[] chair = {-1, -1, -1};
    private String[] chairPersion = {"", "", ""};

    private int[] personScore = {0, 0, 0};
    private int[] threePokes = {0, 0, 0};

    private List<Person> person = new ArrayList<>();
    private List<Integer> deskPokes = new ArrayList<>();
    private Card currentCard;
    private int currentCircle;
    private int currentPerson;

    private int bossId = 0;

    public static int currentScore = 3;

    public Desk(int id) {
        deskId = id;
        person.clear();
        for (int i = 0; i < 3; i++) {
            person.add(new Person());
        }
    }


    public void init() {
        System.out.println("init");
        Desk.currentScore = 3;
        this.currentCard = null;
        this.currentCircle = 0;
        this.currentPerson = 0;
        deskPokes.clear();

        for (int i = 0; i < Desk.POKE_COUNT; i++) {
            deskPokes.add(i);
        }
        person.clear();
        for (int i = 0; i < 3; i++) {
            person.add(new Person());
        }
        Poke.shuffle(deskPokes);
        fenpai(deskPokes);
        randDZ();
        for (int i = 0; i < 3; i++) {
            person.get(i).sortPokers();
        }
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    // 随机地主，将三张底牌给地主
    private void randDZ() {
        bossId = Poke.getDZ();
        this.currentPerson = bossId;
        person.get(bossId).setBoss(true);
        /*List<Integer> newPersonPokes = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            newPersonPokes.set(i, person.get(bossId).getPokes().get(i));
        }*/
        System.out.println("randDZ:" + bossId + " three pokes:" + this.threePokes[0] + "/" + this.threePokes[1] + "/" + this.threePokes[2]);
        person.get(bossId).getPokes().add(this.threePokes[0]);
        person.get(bossId).getPokes().add(this.threePokes[1]);
        person.get(bossId).getPokes().add(this.threePokes[2]);
    }

    public int getBossId() {
        return bossId;
    }

    private void fenpai(List<Integer> pokes) {
        //console.log("fenpai pokes length:",pokes.length);
        for (int i = 0; i < 51; i++) {
            //console.log("fenpai",Math.floor(i / 17),i % 17,pokes[i]);
            //this.personPokes[(int) Math.floor(i / 17)][i % 17] = pokes[i++];
            Person p = person.get(i % 3);
            //System.out.println("p.getPokes():" + p.getPokes() + " pokes.get(" + i + "):" + pokes.get(i));
            p.getPokes().add(pokes.get(i));
        }
        threePokes[0] = pokes.get(51);
        threePokes[1] = pokes.get(52);
        threePokes[2] = pokes.get(53);
    }

    public String getThreePokes() {
        return threePokes[0] + "," + threePokes[1] + "," + threePokes[2];
    }

    public String getPesonCard(int chairId) {
        return person.get(chairId).getPokes().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");
    }

    public boolean isChairFully() {
        for (int i = 0; i < 3; i++) {
            if (chair[i] != 1 || person.get(i) == null) {
                System.out.println("ChairEmpty id:" + i);
                return false;
            }
        }
        return true;
    }

    public boolean isChairEmpty(int chairId) {
        if (chairId > chair.length || chairId < 1) {
            System.out.println("isChairEmpty wrong id request:" + chairId);
            return false;
        }
        return chair[chairId] == -1;
    }

    public void requestChair(int chairId, String unionId) {
        if (chairId >= chair.length || chairId < 0) {
            System.out.println("requestChair wrong id request:" + chairId);
            return;
        }
        for (int i = 0; i < chairPersion.length; i++) {
            if (chairPersion[i] != null && chairPersion[i].equals(unionId)) {
                if (i == chairId) {
                    chair[chairId] = -1;
                    chairPersion[chairId] = "";
                    person.set(chairId, null);
                    System.out.println("double click request unionId:" + unionId + " chairId:" + chairId);
                    return;
                } else {
                    // 用户换桌了
                    if (chair[chairId] == 1) {
                        //请求的桌子已经有人了
                        System.out.println("requestChair fail already has people:" + chairPersion[chairId]);
                    } else {
                        chair[i] = -1;
                        chairPersion[i] = "";

                        chair[chairId] = 1;
                        chairPersion[chairId] = unionId;
                        Person p = new Person();
                        p.setId(chairId);
                        p.setUnionId(unionId);
                        person.set(chairId, p);
                    }
                    return;
                }
            }
        }
        chair[chairId] = 1;
        chairPersion[chairId] = unionId;
        Person p = new Person();
        p.setUnionId(unionId);
        person.set(chairId, p);
    }

    public String getChairInfo() {
        return arrayTransformString(chair) + arrayTransformString2(chairPersion);
    }

    public String getCurrentCard() {
        return currentCard == null ? null : JSON.toJSON(currentCard).toString();
    }

    //采用字符串拼接形式转换，";"分号隔开
    private String arrayTransformString(int[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i] + ";");
        }
        return sb.toString();
    }

    //采用字符串拼接形式转换，";"分号隔开
    private String arrayTransformString2(String[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i] + ";");
        }
        return sb.toString();
    }

}
