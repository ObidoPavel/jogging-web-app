package com.obido.service.impl;

import com.obido.domain.JogBean;
import com.obido.entity.Jog;
import com.obido.entity.User;
import com.obido.exception.BadRequestException;
import com.obido.repository.JogRepository;
import com.obido.repository.UserRepository;
import com.obido.service.JogService;
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
public class JogServiceImpl implements JogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JogService.class);

    @Autowired
    private JogRepository jogRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JogBean retrieveJog(Long jogId) {
        Jog jogEntity = jogRepository.findById(jogId).orElse(null);

        if (jogEntity == null) {
            String message = String.format("Invalid userId : %s", jogId.toString());
            LOGGER.error(message);
            throw new BadRequestException(message);
        }

        return mapToJogBean(jogEntity);
    }

    @Override
    public Long save(Long userId, JogBean jogBean) {
        if (!validateJogBean(jogBean)) {
            throw new BadRequestException("Invalid jogBean provided");
        }

        return jogRepository.save(mapToJogEntity(userId, jogBean)).getId();
    }

    @Override
    public void deleteJog(Long jogId) {
        if (jogId == null) {
            throw new BadRequestException(String.format("No jogId in request!"));
        }

        jogRepository.deleteById(jogId);
    }

    @Override
    public void update(Long userId, JogBean jogBean) {
        if (userId == null) {
            throw new BadRequestException(String.format("No userId in request!"));
        }
        if (!validateJogBean(jogBean)) {
            throw new BadRequestException("Invalid jogBean provided");
        }
        jogRepository.save(mapToJogEntity(userId, jogBean));
    }

    @Override
    public List<JogBean> readAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        List<JogBean> jogBeanList = new ArrayList<>();
        if (user != null) {
            Optional.ofNullable(user.getJogEntities())
                    .ifPresent(jogEntities -> jogBeanList.addAll(jogEntities
                            .stream()
                            .map(this::mapToJogBean)
                            .collect(Collectors.toList()))
                    );
        } else {
            throw new BadRequestException(String.format("Invalid userId : %s", userId.toString()));
        }
        return jogBeanList;
    }

    private Date timestampToDate(Timestamp timestamp) {
        final long timestampTimeLong = timestamp.getTime();
        return new Date(timestampTimeLong);
    }

    private boolean validateJogBean(JogBean jogBean) {
        return jogBean != null
                && jogBean.getDate() != null
                && jogBean.getDistance() != null
                && jogBean.getDuration() != null;
    }

    private Jog mapToJogEntity(Long userId, JogBean jogBean) {
        Jog jogEntity = new Jog();
        jogEntity.setUser(new User(userId));
        jogEntity.setId(jogBean.getId());
        jogEntity.setDistance(jogBean.getDistance());
        jogEntity.setDuration(jogBean.getDuration());
        jogEntity.setDate(new Timestamp(jogBean.getDate().getTime()));
        return jogEntity;
    }

    private JogBean mapToJogBean(Jog jogEntity) {
        JogBean jogBean = new JogBean();
        jogBean.setId(jogEntity.getId());
        jogBean.setDistance(jogEntity.getDistance());
        jogBean.setDuration(jogEntity.getDuration());
        jogBean.setDate(timestampToDate(jogEntity.getDate()));
        return jogBean;
    }
}