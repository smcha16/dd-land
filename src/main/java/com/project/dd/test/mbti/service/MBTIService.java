package com.project.dd.test.mbti.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.dd.test.mbti.domain.MBTIDTO;

@Service
public interface MBTIService {

    List<MBTIDTO> getAllMBTI();

}