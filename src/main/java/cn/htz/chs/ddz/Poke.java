package cn.htz.chs.ddz;

import java.util.*;

public class Poke {
    public static boolean inRect(int x, int y, int rectX, int rectY, int rectW, int rectH) {
        if (x < rectX || x > rectX + rectW || y < rectY || y > rectY + rectH) {
            return false;
        }
        return true;
    }

    // 0-53表示54张牌
    public static void shuffle(List<Integer> pokes) {
        int len = pokes.size();
        // 对于54张牌中的任何一张，都随机找一张和它互换，将牌顺序打乱。
        for (int l = 0; l < len; l++) {
            int des = new Random().nextInt(54);
            int temp = pokes.get(l);
            pokes.set(l, pokes.get(des));
            pokes.set(des, temp);
        }
    }

    public static int getDZ() {
        return new Random().nextInt(3);
    }

    public static List<Integer> sort(List<Integer> pokes) {
        for (int i = 0; i < pokes.size(); i++) {
            for (int j = i + 1; j < pokes.size(); j++) {
                if (GameUtil.compareById(pokes.get(i), pokes.get(j))) {
                    int temp = pokes.get(i);
                    pokes.set(i, pokes.get(j));
                    pokes.set(j, temp);
                }
            }
        }
        return pokes;
    }

    public static int getPokeValue(int poke) {
        // 当扑克值为52时，是小王
        if (poke == 52) {
            return 16;
        }
        // 当扑克值为53时，是大王
        if (poke == 53) {
            return 17;
        }
        int result = poke % 13 + 1;
        if (result == 1) {
            return 14;
        }
        if (result == 2) {
            return 15;
        }
        // 其它情况下返回相应的值(3,4,5,6,7,8,9,10,11(J),12(Q),13(K),14(A),15(2))
        return poke % 13 + 1;
    }

    public static int getImageRow(int poke) {
        return poke / 13;
    }

    public static int getImageCol(int poke) {
        return poke % 13;
    }

    /**
     * 是不是一个有效的牌型
     *
     * @param pokes
     * @return
     */
    public static boolean isCard(List<Integer> pokes) {
        if (getPokeType(pokes) == PokeType.error)
            return false;
        return true;
    }

    /**
     * pokes中的牌的顺序要按照牌的值排列,顺牌中不包含2
     *
     * @param pokes
     * @return
     */
    public static int getPokeType(List<Integer> pokes) {
        int len = pokes.size();
        // 当牌数量为1时,单牌
        if (len == 1) {
            return PokeType.danpai;
        }
        // 当牌数量为2时,可能是对牌和火箭
        if (len == 2) {
            if (pokes.get(0) == 52 && pokes.get(1) == 53 || pokes.get(0) == 53 && pokes.get(1) == 52) {
                return PokeType.huojian;
            }
            if (getPokeValue(pokes.get(0)) == getPokeValue(pokes.get(1))) {
                return PokeType.duipai;
            }
        }
        // 当牌数为3时,只可能是三张
        if (len == 3) {
            if (getPokeValue(pokes.get(0)) == getPokeValue(pokes.get(1))
                    && getPokeValue(pokes.get(2)) == getPokeValue(pokes.get(1))) {
                return PokeType.sanzhang;
            }
        }
        // 当牌数为4时,可能是三带一或炸弹
        if (len == 4) {
            int firstCount = getPokeCount(pokes, pokes.get(0));
            System.out.println("firstCount" + firstCount);
            if (firstCount == 3 || getPokeCount(pokes, pokes.get(1)) == 3) {
                return PokeType.sandaiyi;
            }
            if (firstCount == 4) {
                return PokeType.zhadan;
            }
        }
        // 当牌数等于5时,判断是不是三带二
        if (len == 5) {
            if (sandaier(pokes)) {
                return PokeType.sandaier;
            }

        }
        // 当牌数大于5时,判断是不是单顺
        if (len >= 5) {
            if (shunzi(pokes)) {
                return PokeType.danshun;
            }

        }
        // 当牌数为6时,四带二
        if (len == 6) {
            boolean have4 = false;
            boolean have1 = false;
            for (int i = 0; i < len; i++) {
                int c = getPokeCount(pokes, pokes.get(i));
                if (c == 4) {
                    have4 = true;
                }
                if (c == 1 || c == 2) {
                    have1 = true;
                }
            }

            if (have4 && have1) {
                return PokeType.sidaier;
            }
        }
        // 当牌数为8时,四带二对
        if (len == 8) {
            boolean have4 = false;
            boolean have1 = false;
            boolean have2 = false;
            int firstValue = 0;
            for (int i = 0; i < len; i++) {
                int c = getPokeCount(pokes, pokes.get(i));
                if (c == 4) {
                    have4 = true;
                }
                if (c == 2) {
                    have1 = true;
                    if (getPokeValue(pokes.get(i)) != firstValue && firstValue != 0) {
                        have2 = true;
                    }
                    firstValue = getPokeValue(pokes.get(i));

                }
            }

            if (have4 && have1 && have2) {
                return PokeType.sidaierdui;
            }
        }
        // 当牌数大于等于6时,先检测是不是双顺和三顺
        if (len >= 6) {
            // 双顺
            boolean shuangshunflag = true;
            for (int i = 0; i < len; i++) {
                if (getPokeCount(pokes, pokes.get(i)) != 2) {
                    shuangshunflag = false;
                    break;
                }
            }
            if (shuangshunflag) {
                List<Integer> tempPokes = new ArrayList<>();
                for (int i = 0; i < len / 2; i++) {
                    tempPokes.add(pokes.get(i * 2));
                }
                if (shunzi(tempPokes)) {
                    return PokeType.shuangshun;
                }
            }
            System.out.println("shuangshun:" + shuangshunflag);
            // 三顺
            boolean sanshunflag = true;
            for (int i = 0; i < len; i++) {
                if (getPokeCount(pokes, pokes.get(i)) != 3) {
                    sanshunflag = false;
                    break;
                }
            }
            if (sanshunflag) {
                List<Integer> tempPokes = new ArrayList<>();
                for (int i = 0; i < len / 3; i++) {
                    tempPokes.add(pokes.get(i * 3));
                }
                if (shunzi(tempPokes)) {
                    return PokeType.sanshun;
                }
            }

        }

        // 当牌数大于等于8,且能够被4整除时,判断是不是飞机
        if (len >= 8 && len % 4 == 0 || (len >= 8 && len % 5 == 0)) {
            int have1 = 0;
            Map<Integer, Integer> sanzhang = new HashMap<>();
            Map<Integer, Integer> duizi = new HashMap<>();
            for (int i = 0; i < pokes.size(); i++) {
                int c = getPokeCount(pokes, pokes.get(i));
                if (c == 3) {
                    if (sanzhang.containsKey(pokes.get(i))) {
                        sanzhang.put(pokes.get(i), sanzhang.get(pokes.get(i)) + 1);
                    } else {
                        sanzhang.put(pokes.get(i), 1);
                    }
                } else if (c == 1) {
                    have1++;
                } else if (c == 2) {
                    if (duizi.containsKey(pokes.get(i))) {
                        duizi.put(pokes.get(i), duizi.get(pokes.get(i)) + 1);
                    } else {
                        duizi.put(pokes.get(i), 1);
                    }
                }
            }
            List<Integer> mapKeyList = new ArrayList<Integer>(sanzhang.keySet());
            //sort(tempArray);
            if (shunzi(mapKeyList)) {
                if (((sanzhang.size() == have1) && duizi.size() == 0) || ((sanzhang.size() == duizi.size()) && have1 == 0)) {
                    return PokeType.feiji;
                }
            }

        }
        // 如果不是可知牌型,返回错误型
        return PokeType.error;
    }

    public static List<Integer> intArrayToIntegerArray(Integer[] array) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            result.set(i, array[i]);
        }
        return result;
    }

    /**
     * 判断是不是三带二
     *
     * @param pokes
     * @return
     */
    public static boolean sandaier(List<Integer> pokes) {
        int firstCount = getPokeCount(pokes, pokes.get(0));
        int secondCount = getPokeCount(pokes, pokes.get(4));
        return (firstCount == 2 && secondCount == 3) || (firstCount == 3 && secondCount == 2);
    }

    /**
     * 判断是不是顺子
     *
     * @param pokes
     * @return
     */
    public static boolean shunzi(List<Integer> pokes) {
        int start = getPokeValue(pokes.get(0));
        // 顺子中不能包含2,king
        if (start >= 15) {
            return false;
        }
        int next = 0;
        for (int i = 1; i < pokes.size(); i++) {
            next = getPokeValue(pokes.get(i));
            if (start - next != 1) {
                return false;
            }
            start = next;
        }
        if (next >= 15) {
            return false;
        }
        return true;
    }

    // 统计一手牌中同值的牌出现的次数来判断是对牌,三顺,三带一,炸弹,四代二等
    public static int getPokeCount(List<Integer> pokes, int poke) {
        int count = 0;
        for (int i = 0; i < pokes.size(); i++) {
            if (getPokeValue(pokes.get(i)) == getPokeValue(poke)) {
                count++;
            }
        }
        return count;
    }

    // 通过给给出的一手牌,来返回它的牌值大小，pokes中的顺序是排列好的
    public static int getPokeTypeValue(List<Integer> pokes, int pokeType) {
        // 这几种类型直接返回第一个值
        if (pokeType == PokeType.danpai || pokeType == PokeType.duipai
                || pokeType == PokeType.danshun || pokeType == PokeType.sanshun
                || pokeType == PokeType.shuangshun
                || pokeType == PokeType.sanzhang || pokeType == PokeType.zhadan) {
            return getPokeValue(pokes.get(0));
        }
        // 三带一和飞机返回数量为3的牌的最大牌值
        if (pokeType == PokeType.sandaiyi || pokeType == PokeType.feiji) {
            for (int i = 0; i <= pokes.size() - 3; i++) {
                if (getPokeValue(pokes.get(i)) == getPokeValue(pokes.get(i + 1))
                        && getPokeValue(pokes.get(i + 1)) == getPokeValue(pokes.get(i + 2))) {
                    return getPokeValue(pokes.get(i));
                }
            }
        }
        // 四带二返回数量为4的牌值
        if (pokeType == PokeType.sidaier) {
            for (int i = 0; i < pokes.size() - 3; i++) {
                if (getPokeValue(pokes.get(i)) == getPokeValue(pokes.get(i + 1))
                        && getPokeValue(pokes.get(i + 1)) == getPokeValue(pokes.get(i + 2))
                        && getPokeValue(pokes.get(i + 2)) == getPokeValue(pokes.get(i + 3))) {
                    return getPokeValue(pokes.get(i));
                }
            }
        }
        return 0;
    }

    /**
     * true 第一个大
     *
     * @param f
     * @param s
     * @return
     */
    public static boolean compare(Card f, Card s) {
        // 当两种牌型相同时
        if (f.getPokeType() == s.getPokeType()) {
            // 两手牌牌型相同时，数量不同将无法比较，默认为第二个大，使s不能出牌
            if (f.getPokes().size() != s.getPokes().size())
                return false;
            // 牌型相同，数量相同时，比较牌值
            System.out.println("huasongc" + f.getValue() + " :" + s.getValue());
            return f.getValue() > s.getValue();
        }
        // 在牌型不同的时候,如果f的牌型是火箭,则返回true
        if (f.getPokeType() == PokeType.huojian) {
            return true;
        }
        if (s.getPokeType() == PokeType.huojian) {
            return false;
        }
        if (f.getPokeType() == PokeType.zhadan && s.getPokeType() == PokeType.zhadan) {
            return Poke.getPokeValue(f.getPokes().get(0)) > Poke.getPokeValue(s.getPokes().get(0));
        }
        // 排除火箭的类型，炸弹最大
        if (f.getPokeType() == PokeType.zhadan) {
            return true;
        }
        if (s.getPokeType() == PokeType.zhadan) {
            return false;
        }
        // 无法比较的情况，默认为s大于f
        return true;
    }

    public int[] findBigThanCard(Card card, List<Integer> pokes) {
        return null;

    }
}
