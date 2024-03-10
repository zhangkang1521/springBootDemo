package org.zk.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdUtilsTest {

    @Autowired
    private AdUtils adUtils;

    @Test
    public void indexAd() {
        adUtils.indexAd("1", Arrays.asList("sh"), Arrays.asList("apple"), 50);
        adUtils.indexAd("2", Arrays.asList("sh", "sz"), Arrays.asList("orange"), 30);
        adUtils.indexAd("3", Arrays.asList("sz"), Arrays.asList("banana"), 99);
        adUtils.indexAd("4", Arrays.asList("aq"), Arrays.asList("babimantou"), 8);
    }

    @Test
    public void targetAds() {
        adUtils.targetAds(Arrays.asList("sh", "aq"), Arrays.asList("apple"));
    }
}