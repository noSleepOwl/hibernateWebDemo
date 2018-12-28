package com.cn.sleep.study.example.shigongwen.adminlte.sys.service;

import com.cn.sleep.study.example.shigongwen.adminlte.sys.model.Museum;
import com.cn.sleep.study.example.shigongwen.adminlte.sys.repository.MuseumRepository;
import com.cn.sleep.study.example.shigongwen.adminlte.sys.vo.MuseumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MuseumService {
    @Autowired
    private MuseumRepository museumRepository;

    /**
     * 查询所有的根目录
     */
    @Cacheable(cacheNames = "museum_cache")
    public List<Museum> getMuseums() {
        return museumRepository.findRoot();
    }

    private List<MuseumVo> createVo(List<Museum> museumList) {
        return museumList.stream().map(this::createVo).collect(Collectors.toList());
    }

    private MuseumVo createVo(Museum museum) {

        return null;
    }

}
