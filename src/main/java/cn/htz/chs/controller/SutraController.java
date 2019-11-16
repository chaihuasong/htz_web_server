package cn.htz.chs.controller;

import cn.htz.chs.common.page.PageResult;
import cn.htz.chs.common.result.CommonResult;
import cn.htz.chs.model.Sutra;
import cn.htz.chs.service.SutraService;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SutraController {
    @Autowired
    private SutraService sutraService;

    @PostMapping("put/sutra/item")
    public CommonResult insertSutraItems(String name, String cover, String description, long playedCount, int itemTotal,
                                    @Nullable String createBy, @Nullable String createTime, @Nullable String lastUpdateBy, @Nullable String lastUpdateTime) {
        Sutra sutra = sutraService.findByName(name);
        if (sutra != null) {
            sutra.setName(name);
            sutra.setCover(cover);
            sutra.setDescription(description);
            sutra.setPlayedCount(playedCount);
            sutra.setItemTotal(itemTotal);
            sutra.setCreateBy(createBy);
            sutra.setCreateTime(new Date());
            sutra.setLastUpdateBy(lastUpdateBy);
            sutra.setLastUpdateTime(new Date());
            sutraService.updateById(sutra);
            return CommonResult.success("Update OK");
        } else {
            sutra = new Sutra();
        }
        sutra.setName(name);
        sutra.setCover(cover);
        sutra.setDescription(description);
        sutra.setPlayedCount(playedCount);
        sutra.setItemTotal(itemTotal);
        sutra.setCreateBy(createBy);
        sutra.setCreateTime(new Date());
        sutra.setLastUpdateBy(lastUpdateBy);
        sutra.setLastUpdateTime(new Date());

        sutraService.save(sutra);

        return CommonResult.success("Insert OK");
    }

    @PostMapping("get/sutra/items")
    public CommonResult getSutraItems() {
        PageResult result = sutraService.queryItems();
        return CommonResult.success("OK", result.getContent());
    }

    @PostMapping("put/sutra/delete")
    public CommonResult deleteSutraItems(String name) {
        sutraService.deleteByName(name);
        return CommonResult.success("OK");
    }
}
