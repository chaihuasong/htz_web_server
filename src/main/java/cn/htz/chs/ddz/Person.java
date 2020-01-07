package cn.htz.chs.ddz;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    private int id;
    private String unionId;
    private List<Integer> pokes = new ArrayList<>();// = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private List<Boolean> pokesFlag = new ArrayList<>();// = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

    private boolean isBoss;

    public void sortPokers() {
        pokes = Poke.sort(pokes);
    }
}
