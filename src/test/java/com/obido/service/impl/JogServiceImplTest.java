package com.obido.service.impl;

import com.obido.domain.JogBean;
import com.obido.entity.Jog;
import com.obido.entity.User;
import com.obido.exception.BadRequestException;
import com.obido.repository.JogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JogServiceImplTest {

    @InjectMocks
    private JogServiceImpl jogService;

    @Mock
    private JogRepository jogRepository;

    private JogBean jogBean;

    private Jog jog;

    @Before
    public void setUp() throws Exception {
        User user = new User(1l);

        jogBean = new JogBean();
        jog = new Jog();

        jog.setId(1l);
        jog.setDate(new Timestamp(100));
        jog.setDistance(55D);
        jog.setDuration(10l);
        jog.setUser(user);

        jogBean.setId(1l);
        jogBean.setDate(new Date(100));
        jogBean.setDistance(55D);
        jogBean.setDuration(10l);

        when(jogRepository.findById(anyLong())).thenReturn(Optional.ofNullable(jog));
        when(jogRepository.save(any())).thenReturn(jog);
    }

    @Test
    public void retrieveJogTest() {
        JogBean actualJogBean = jogService.retrieveJog(1l);
        verify(jogRepository).findById(anyLong());
        verifyNoMoreInteractions(jogRepository);
        compareToExpectedJogBean(actualJogBean);
    }

    @Test(expected = BadRequestException.class)
    public void retrieveJogNullTest() {
        when(jogRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        jogService.retrieveJog(1l);
        verify(jogRepository).findById(anyLong());
        verifyNoMoreInteractions(jogRepository);
    }

    @Test
    public void saveTest() {
        long savedId = jogService.save(1l, jogBean);
        verify(jogRepository).save(any(Jog.class));
        verifyNoMoreInteractions(jogRepository);
        assertEquals(1l, savedId);
    }

    @Test(expected = BadRequestException.class)
    public void saveNullTest() {
        jogService.save(1l, null);
        verifyNoMoreInteractions(jogRepository);
    }

    @Test
    public void deleteJogTest() {
        jogService.deleteJog(1l);
        verify(jogRepository).deleteById(anyLong());
        verifyNoMoreInteractions(jogRepository);
    }

    @Test(expected = BadRequestException.class)
    public void deleteJogNullTest() {
        jogService.deleteJog(null);
        verifyNoMoreInteractions(jogRepository);
    }

    @Test
    public void updateTest() {
        jogService.update(1l, jogBean);
        verify(jogRepository).save(any(Jog.class));
        verifyNoMoreInteractions(jogRepository);
    }

    @Test(expected = BadRequestException.class)
    public void updateUserIdNullTest() {
        jogService.update(null, jogBean);
        verifyNoMoreInteractions(jogRepository);
    }

    @Test(expected = BadRequestException.class)
    public void updateJogBeanNullTest() {
        jogService.update(1l, null);
        verifyNoMoreInteractions(jogRepository);
    }

    private void compareToExpectedJogBean(JogBean actualJogBean) {
        assertEquals(actualJogBean.getId(), jogBean.getId());
        assertEquals(actualJogBean.getDistance(), jogBean.getDistance());
        assertEquals(actualJogBean.getDate(), jogBean.getDate());
        assertEquals(actualJogBean.getDuration(), jogBean.getDuration());
    }
}