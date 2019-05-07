package com.mzkj.controller.process;

import com.mzkj.service.process.TallyManager;
import com.mzkj.vo.process.TallyVo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TallyControllerSpec {


    TallyController tallyController;
    TallyManager tallyManager;
    @Before
    public void before(){
        tallyController = spy(TallyController.class);
        tallyManager = mock(TallyManager.class);
    }

    @Test
    public void whenSaveTallyThenInvokeServiceSaveSpec() throws Exception {
        TallyVo tallyVo = new TallyVo();
        doReturn(tallyManager).when(tallyController).getTallyService();
        tallyController.saveTally(tallyVo);
        verify(tallyManager, times(1)).save(tallyVo);
        Assert.assertEquals(null, tallyManager);
    }
}
