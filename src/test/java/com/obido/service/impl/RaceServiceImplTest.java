package com.obido.service.impl;

import com.obido.domain.RaceBean;
import com.obido.entity.Race;
import com.obido.entity.User;
import com.obido.exception.BadRequestException;
import com.obido.repository.RaceRepository;
import com.obido.repository.UserRepository;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RaceServiceImplTest {

    @InjectMocks
    private RaceServiceImpl raceService;

    @Mock
    private RaceRepository raceRepository;

    private RaceBean raceBean;

    private Race race;

    @Before
    public void setUp() throws Exception {
        User user = new User(1l);

        raceBean = new RaceBean();
        race = new Race();

        race.setId(1l);
        race.setDate(new Timestamp(100));
        race.setDistance(55D);
        race.setDuration(10l);
        race.setUser(user);

        raceBean.setId(1l);
        raceBean.setDate(new Date(100));
        raceBean.setDistance(55D);
        raceBean.setDuration(10l);

        when(raceRepository.findById(anyLong())).thenReturn(Optional.ofNullable(race));
        when(raceRepository.save(any())).thenReturn(race);
    }

    @Test
    public void retrieveRace() {
        RaceBean actualRaceBean = raceService.retrieveRace(1l);
        compareToExpectedRaceBean(actualRaceBean);
    }

    @Test(expected = BadRequestException.class)
    public void retrieveRace_null() {
        when(raceRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        raceService.retrieveRace(1l);
    }

    @Test
    public void save() {
        long savedId = raceService.save(1l, raceBean);
        assertEquals(1l, savedId);
    }

    @Test(expected = BadRequestException.class)
    public void save_null() {
        raceService.save(1l, null);
    }

    @Test
    public void deleteRace() {
        raceService.deleteRace(1l);
    }

    @Test(expected = BadRequestException.class)
    public void deleteRace_null() {
        raceService.deleteRace(null);
    }

    @Test
    public void update() {
        raceService.update(1l, raceBean);
    }

    @Test(expected = BadRequestException.class)
    public void update_userId_null() {
        raceService.update(null, raceBean);
    }

    @Test(expected = BadRequestException.class)
    public void update_raceBean_null() {
        raceService.update(1l, null);
    }

    private void compareToExpectedRaceBean(RaceBean actualRaceBean) {
        assertEquals(actualRaceBean.getId(), raceBean.getId());
        assertEquals(actualRaceBean.getDistance(), raceBean.getDistance());
        assertEquals(actualRaceBean.getDate(), raceBean.getDate());
        assertEquals(actualRaceBean.getDuration(), raceBean.getDuration());
    }
}