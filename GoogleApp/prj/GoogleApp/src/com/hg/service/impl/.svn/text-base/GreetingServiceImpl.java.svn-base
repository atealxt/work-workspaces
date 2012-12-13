package com.hg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.users.User;
import com.hg.core.dao.BaseDao;
import com.hg.core.service.BaseServiceImpl;
import com.hg.dao.GreetingDao;
import com.hg.dto.GreetingDto;
import com.hg.dto.GreetingInfo;
import com.hg.pojo.Greeting;
import com.hg.service.GreetingService;
import com.hg.util.DateUtil;
import com.hg.util.GaeUtil;
import com.hg.util.RoleUtil;
import com.hg.util.StringUtil;

@Service("GreetingService")
public class GreetingServiceImpl extends BaseServiceImpl<Greeting, String> implements GreetingService {

    @Autowired
    private GreetingDao greetingDao;

    @Override
    protected BaseDao<Greeting, String> getDao() {
        return greetingDao;
    }

    @Override
    public void addGreeting(final String content, final String guestName) {
        final User user = GaeUtil.getCurrentUser();
        if (user != null) {
            greetingDao.insert(new Greeting(user, content));
        } else {
            if (!StringUtil.isEmpty(guestName, true)) {
                greetingDao.insert(new Greeting(guestName, content));
            } else {
                greetingDao.insert(new Greeting((String) null, content));
            }
        }
    }

    @Override
    public void removeGreeting(final String id) {
        if (RoleUtil.isMaster()) {
            greetingDao.deleteById(id);
        }
    }

    @Override
    public GreetingDto makeInfo(int page) {
        final GreetingDto dto = new GreetingDto();

        final int maxP = getMaxPage();
        dto.setMaxP(maxP);

        if (page > maxP) {
            page = maxP;
        }
        dto.setGreetings(getGreetings(page));
        dto.setCurrentP(page);

        return dto;
    }

    @Override
    public int getMaxPage() {
        final int count = (int) greetingDao.count();
        int maxP = count / Greeting.getPagingSize();
        if (count % Greeting.getPagingSize() == 0) {
            maxP--;
        }
        return maxP;
    }

    @Override
    public List<GreetingInfo> getList(final int pageNo) {
        final List<Greeting> greetings = greetingDao.findAll(pageNo * Greeting.getPagingSize(), Greeting.getPagingSize());
        final List<GreetingInfo> vos = new ArrayList<GreetingInfo>();
        for (final Greeting c : greetings) {
            final GreetingInfo vo = new GreetingInfo();
            BeanUtils.copyProperties(c, vo);
            if (c.getAuthor() != null) {
                vo.setName(c.getAuthor().getNickname());
                vo.setContact(StringUtil.getContactUrl(c.getAuthor().getEmail()));
            } else {
                vo.setName(c.getGuestName());
                vo.setContact("###");
            }
            vo.setTime(DateUtil.format(c.getDate(), "yyyy-MM-dd HH:mm:ss"));
            vos.add(vo);
        }
        return vos;
    }

    private List<Greeting> getGreetings(final int page) {
        return greetingDao.findAll(page * Greeting.getPagingSize(), Greeting.getPagingSize());
    }
}
