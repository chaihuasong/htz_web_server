package cn.htz.chs.controller;

import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.model.Sutra;
import cn.htz.chs.model.SutraItem;
import cn.htz.chs.service.SutraItemService;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SutraItemController {
    @Autowired
    private SutraItemService sutraItemService;

    @PostMapping("put/sutraitems/item")
    public CommonResult insertSutraItems(String sutraId, String title, String description, String original, String audioId,
                                         String lyricId, int lesson, long playCount, long duration, String hash,
                                         @Nullable String createBy, @Nullable String createTime,
                                         @Nullable String lastUpdateBy, @Nullable String lastUpdateTime) {
        SutraItem sutraItem = sutraItemService.findByTitle(title);
        if (sutraItem != null) {
            sutraItem.setSutraId(sutraId);
            sutraItem.setTitle(title);
            sutraItem.setDescription(description);
            sutraItem.setOriginal(original);
            sutraItem.setAudioId(audioId);
            sutraItem.setLyricId(lyricId);
            sutraItem.setLesson(lesson);
            sutraItem.setPlayedCount(playCount);
            sutraItem.setDuration(duration);
            sutraItem.setHash(hash);
            sutraItem.setCreateBy(createBy);
            sutraItem.setCreateTime(new Date());
            sutraItem.setLastUpdateBy(lastUpdateBy);
            sutraItem.setLastUpdateTime(new Date());
            return CommonResult.success("Update OK");
        } else {
            sutraItem = new SutraItem();
        }
        sutraItem.setSutraId(sutraId);
        sutraItem.setTitle(title);
        sutraItem.setDescription(description);
        sutraItem.setOriginal(original);
        sutraItem.setAudioId(audioId);
        sutraItem.setLyricId(lyricId);
        sutraItem.setLesson(lesson);
        sutraItem.setPlayedCount(playCount);
        sutraItem.setDuration(duration);
        sutraItem.setHash(hash);
        sutraItem.setCreateBy(createBy);
        sutraItem.setCreateTime(new Date());
        sutraItem.setLastUpdateBy(lastUpdateBy);
        sutraItem.setLastUpdateTime(new Date());

        sutraItemService.save(sutraItem);

        return CommonResult.success("Insert OK");
    }

    @PostMapping("get/sutraitems/items")
    public CommonResult getSutraItems() {
        PageResult result = sutraItemService.queryItems();
        return CommonResult.success("OK", result.getContent());
    }

    @PostMapping("put/sutraitems/delete")
    public CommonResult deleteSutraItems(String title) {
        sutraItemService.deleteByTitle(title);
        return CommonResult.success("OK");
    }
}
