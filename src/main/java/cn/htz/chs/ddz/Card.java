package cn.htz.chs.ddz;

import lombok.Data;

import java.util.List;

@Data
public class Card {
    private int value;
    private int pokeType;
    private List<Integer> pokes;
    private int personId;

    public Card(List<Integer> pokes, int id) {
        this.pokes = pokes;
        personId = id;
        pokeType = Poke.getPokeType(pokes);
        value = Poke.getPokeTypeValue(pokes, this.pokeType);


        if (this.pokeType == PokeType.huojian || this.pokeType == PokeType.zhadan) {
            Desk.currentScore *= 2;
        }
    }
}
