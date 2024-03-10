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
public class JobServiceTest {

    @Autowired
    JobService jobService;

    @Test
    public void indexJob() {
        jobService.indexJob("job1", "a");
        jobService.indexJob("job2", "a", "b");
        jobService.indexJob("job3", "a", "b", "c");
    }

    @Test
    public void searchJob() {
        jobService.searchJob(Arrays.asList("a", "b"));
    }
}