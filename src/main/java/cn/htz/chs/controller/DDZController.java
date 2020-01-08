package cn.htz.chs.controller;

import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.ddz.Card;
import cn.htz.chs.ddz.Desk;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class DDZController {
    private Desk desk1 = new Desk(1);

    @GetMapping("request_chair")
    public CommonResult requestChair(String unionId, int deskId, int chairId) {
        System.out.println("requestChair unionId:" + unionId + " deskId:" + deskId + " chairId:" + chairId);
        /*if (desk1.isChairEmpty(chairId)) {
            desk1.requestChair(chairId, unionId);
            return CommonResult.success("成功", desk1.getChairInfo());
        }*/
        desk1.requestChair(chairId, unionId);
        //System.out.println("requestChair error!! deskId:" + deskId + " info:" + desk1.getChairInfo());
        if (desk1.isChairFully()) {
            // 椅子坐满了，开始准备游戏
            desk1.init();
        }
        return CommonResult.success("成功", desk1.getChairInfo());
    }

    @GetMapping("get_chair_info")
    public CommonResult getChairInfo(int deskId) {
        //System.out.println("getChairInfo deskId:" + deskId + " info:" + desk1.getChairInfo());
        return CommonResult.success("成功", desk1.getChairInfo());
    }

    @GetMapping("get_current_desk_card")
    public CommonResult getCurrentDeskCard(int deskId) {
        JSONObject object = new JSONObject();
        object.put("card", desk1.getCurrentCard());
        object.put("chairInfo", desk1.getChairInfo());
        object.put("buyaoInfo", desk1.getBuyaoInfo());
        System.out.println("getCurrentDeskCard desk1.getCurrentCard():" + desk1.getCurrentCard());
        //System.out.println("getCurrentDeskCard deskId:" + deskId + " result:" + object.toString());
        return CommonResult.success("成功", object.toString());
    }

    @GetMapping("get_hand_card")
    public CommonResult getHandCard(int deskId) {
        JSONObject object = new JSONObject();
        object.put("pos0", desk1.getPesonCard(0));
        object.put("pos1", desk1.getPesonCard(1));
        object.put("pos2", desk1.getPesonCard(2));
        object.put("pos3", desk1.getThreePokes());
        object.put("bossId", desk1.getBossId());
        System.out.println("getHandCard deskId:" + deskId + " result:" + object.toString());
        return CommonResult.success("成功", object.toString());
    }

    @GetMapping("chupai")
    public CommonResult chupai(int deskId, int chairId, String pokes) {
        System.out.println("chupai deskId:" + deskId + " chairId:" + chairId + " pokes:" + pokes);
        List<Integer> chupai = new ArrayList<>();
        if (!pokes.contains(",")) {
            chupai.add(Integer.parseInt(pokes));
        } else {
            String[] splite = pokes.split(",");
            for (int i = 0; i < splite.length; i++) {
                chupai.add(Integer.parseInt(splite[i]));
            }
        }
        System.out.println("chupai:" + chupai);
        Card card = new Card(chupai, chairId);
        desk1.setCurrentCard(card);
        return CommonResult.success("成功");
    }

    @GetMapping("buyao")
    public CommonResult buyao(int deskId, int chairId) {
        System.out.println("buyao deskId:" + deskId + " chairId:" + chairId);
        desk1.setBuyaoInfo(chairId);
        return CommonResult.success("成功");
    }
}
