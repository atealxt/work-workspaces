package com.hg.service;

import java.util.List;

import com.hg.dto.GreetingDto;
import com.hg.dto.GreetingInfo;

public interface GreetingService {

    List<GreetingInfo> getList(int pageNo);

    void addGreeting(String content, String guestName);

    void removeGreeting(String id);

    GreetingDto makeInfo(int page);

    int getMaxPage();
}
