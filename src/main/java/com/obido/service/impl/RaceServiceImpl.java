package com.obido.service.impl;

import com.obido.domain.RaceBean;
import com.obido.entity.Race;
import com.obido.entity.User;
import com.obido.exception.BadRequestException;
import com.obido.repository.RaceRepository;
import com.obido.repository.UserRepository;
import com.obido.service.RaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RaceService.class);

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RaceBean retrieveRace(Long raceId) {
        Race raceEntity = raceRepository.findById(raceId).orElse(null);

        if (raceEntity == null) {
            String message = String.format("Invalid userId : %s", raceId.toString());
            LOGGER.error(message);
            throw new BadRequestException(message);
        }

        return mapToRaceBean(raceEntity);
    }

    @Override
    public Long save(Long userId, RaceBean raceBean) {
        if (!validateRaceBean(raceBean)) {
            throw new BadRequestException("Invalid raceBean provided");
        }

        return raceRepository.save(mapToRaceEntity(userId, raceBean)).getId();
    }

    @Override
    public void deleteRace(Long raceId) {
        if (raceId == null) {
            throw new BadRequestException(String.format("No raceId in request!"));
        }

        raceRepository.deleteById(raceId);
    }

    @Override
    public void update(Long userId, RaceBean raceBean) {
        if (userId == null) {
            throw new BadRequestException(String.format("No userId in request!"));
        }
        if (!validateRaceBean(raceBean)) {
            throw new BadRequestException("Invalid raceBean provided");
        }
        raceRepository.save(mapToRaceEntity(userId, raceBean));
    }

    @Override
    public List<RaceBean> readAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        List<RaceBean> raceList = new ArrayList<>();
        if (user != null) {
            Optional.ofNullable(user.getRaceEntities())
                    .ifPresent(raceEntities -> raceList.addAll(raceEntities
                            .stream()
                            .map(this::mapToRaceBean)
                            .collect(Collectors.toList()))
                    );
        } else {
            throw new BadRequestException(String.format("Invalid userId : %s", userId.toString()));
        }
        return raceList;
    }

    private Date timestampToDate(Timestamp timestamp) {
        final long timestampTimeLong = timestamp.getTime();
        return new Date(timestampTimeLong);
    }

    private boolean validateRaceBean(RaceBean raceBean) {
        return raceBean != null
                && raceBean.getDate() != null
                && raceBean.getDistance() != null
                && raceBean.getDuration() != null;
    }

    private Race mapToRaceEntity(Long userId, RaceBean raceBean) {
        Race raceEntity = new Race();
        raceEntity.setUser(new User(userId));
        raceEntity.setId(raceBean.getId());
        raceEntity.setDistance(raceBean.getDistance());
        raceEntity.setDuration(raceBean.getDuration());
        raceEntity.setDate(new Timestamp(raceBean.getDate().getTime()));
        return raceEntity;
    }

    private RaceBean mapToRaceBean(Race raceEntity) {
        RaceBean raceBean = new RaceBean();
        raceBean.setId(raceEntity.getId());
        raceBean.setDistance(raceEntity.getDistance());
        raceBean.setDuration(raceEntity.getDuration());
        raceBean.setDate(timestampToDate(raceEntity.getDate()));
        return raceBean;
    }
}