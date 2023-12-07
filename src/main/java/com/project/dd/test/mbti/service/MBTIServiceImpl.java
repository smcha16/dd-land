package com.project.dd.test.mbti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.repository.MBTIDAO;

@Service
@Primary
public class MBTIServiceImpl implements MBTIService {

    @Autowired
    private MBTIDAO mbtiDAO;
    
    @Override
    public List<MBTIDTO> getAllMBTI() {
        return mbtiDAO.getAllMBTI();
    }
    
}
