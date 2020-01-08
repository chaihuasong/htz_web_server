package cn.htz.chs.ddz;

import com.alibaba.fastjson.JSON;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Desk {

    private static final int POKE_COUNT = 54;
    private static final int DESK_PERSON = 3;
    private int mDeskId;

    private int[] personScore = {0, 0, 0};
    private int[] threePokes = {0, 0, 0};

    private List<Integer> deskPokes = new ArrayList<>();
    private Card currentCard;
    private int currentCircle;
    private int currentPerson;
    //key: pos+id
    private Map<String, Integer> mBuyaoInfo = new HashMap<>();
    private Map<String, Person> mChairInfo = new HashMap<>();

    private int bossId = 0;

    public static int currentScore = 3;

    public Desk(int id) {
        mDeskId = id;
        for (int i = 0; i < 3; i++) {
            mBuyaoInfo.put("pos" + i, -1);
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
        for (int i = 0; i < 3; i++) {
            mBuyaoInfo.put("pos" + i, -1);
        }
        Poke.shuffle(deskPokes);
        fenpai(deskPokes);
        randDZ();
        for (int i = 0; i < 3; i++) {
            mChairInfo.get("pos" + i).sortPokers();
        }
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public void setBuyaoInfo(int chairId) {
        mBuyaoInfo.put("pos" + chairId, 1);
    }

    public String getBuyaoInfo() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(mBuyaoInfo);
        return jsonArray.get(0).toString();
    }

    // 随机地主，将三张底牌给地主
    private void randDZ() {
        bossId = Poke.getDZ();
        this.currentPerson = bossId;
        mChairInfo.get("pos" + bossId).setBoss(true);
        /*List<Integer> newPersonPokes = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            newPersonPokes.set(i, mChairInfo.get("pos" + bossId).getPokes().get(i));
        }*/
        System.out.println("randDZ:" + bossId + " three pokes:" + this.threePokes[0] + "/" + this.threePokes[1] + "/" + this.threePokes[2]);
        mChairInfo.get("pos" + bossId).getPokes().add(this.threePokes[0]);
        mChairInfo.get("pos" + bossId).getPokes().add(this.threePokes[1]);
        mChairInfo.get("pos" + bossId).getPokes().add(this.threePokes[2]);
    }

    public int getBossId() {
        return bossId;
    }

    private void fenpai(List<Integer> pokes) {
        //console.log("fenpai pokes length:",pokes.length);
        for (int i = 0; i < 51; i++) {
            //console.log("fenpai",Math.floor(i / 17),i % 17,pokes[i]);
            //this.personPokes[(int) Math.floor(i / 17)][i % 17] = pokes[i++];
            Person p = mChairInfo.get("pos" + i % 3);
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
        return mChairInfo.get("pos" + chairId).getPokes().toString()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");
    }

    public boolean isChairFully() {
        for (int i = 0; i < DESK_PERSON; i++) {
            if (mChairInfo.get("pos" + i) == null) {
                System.out.println("ChairEmpty id:" + i);
                return false;
            }
        }
        return true;
    }

    public boolean isChairEmpty(int chairId) {
        if (chairId > DESK_PERSON || chairId < 1) {
            System.out.println("isChairEmpty wrong id request:" + chairId);
            return false;
        }
        return mChairInfo.get("pos" + chairId) == null;
    }

    public void requestChair(int chairId, String unionId) {
        if (chairId >= DESK_PERSON || chairId < 0) {
            System.out.println("requestChair wrong id request:" + chairId);
            return;
        }
        for (int i = 0; i < DESK_PERSON; i++) {
            Person person = mChairInfo.get("pos" + i);
            if (person != null && person.getUnionId() != null && person.getUnionId().equals(unionId)) {
                if (i == chairId) {
                    mChairInfo.put("pos" + chairId, null);
                    System.out.println("double click request unionId:" + unionId + " chairId:" + chairId);
                    return;
                } else {
                    // 用户换桌了
                    if (person != null && person.getUnionId() != null) {
                        //请求的桌子已经有人了
                        System.out.println("requestChair fail already has people:" + person.getUnionId());
                    } else {
                        mChairInfo.put("pos" + i, null);

                        Person p = new Person();
                        p.setId(chairId);
                        p.setUnionId(unionId);
                        mChairInfo.put("pos" + chairId, p);
                    }
                    return;
                }
            }
        }
        Person p = new Person();
        p.setUnionId(unionId);
        mChairInfo.put("pos" + chairId, p);
    }

    public String getChairInfo() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(mChairInfo);
        return jsonArray.get(0).toString();
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
