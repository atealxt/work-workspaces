package com.hg.web.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hg.constant.CacheConstant;
import com.hg.core.EasyController;
import com.hg.dto.CalendarInfo;
import com.hg.dto.DateInfo;
import com.hg.service.ArticleService;
import com.hg.util.CacheUtil;
import com.hg.util.DateUtil;
import com.hg.util.StringUtil;

@Controller
@RequestMapping("/calendar")
public class A09CalendarController extends EasyController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping
    public void main(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException {
        makeCalendar(req, resp);
    }

    private void makeCalendar(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        boolean useCache = true;

        final Date calendarTime = CacheUtil.get(CacheConstant.CALENDAR_TIME);
        if (calendarTime == null || !DateUtil.isToday(calendarTime)) {
            // no cache exist
            useCache = false;
        } else if (!StringUtil.isEmpty(req.getParameter("showmonth"))//
                && req.getParameter("showmonth").length() == 6//
                && StringUtil.isNum(req.getParameter("showmonth"))//
                && !"y".equals(req.getParameter("usecache"))) {
            // 1.contains valid param(showmonth) condition
            // 2.param(usecache) != 'y'
            useCache = false;
        }

        String calendarHTML = null;
        if (useCache) {
            calendarHTML = CacheUtil.get(CacheConstant.CALENDAR);
        }

        logger.trace("useCache: " + useCache + ", calendarHTML != null: " + (calendarHTML != null));
        if (!useCache || calendarHTML == null) {
            final CalendarInfo calendar = getCalendarInfo(getDealCalendar(req));
            calendarHTML = getCalendarHTML(calendar);

            if (StringUtil.isEmpty(req.getParameter("showmonth"))) {
                // just save current month info
                CacheUtil.put(CacheConstant.CALENDAR, calendarHTML);
                CacheUtil.put(CacheConstant.CALENDAR_TIME, DateUtil.getCurrentCal().getTime());
            }
        }

        initResponse(resp);
        resp.getWriter().println(calendarHTML);
    }

    private Calendar getDealCalendar(final HttpServletRequest req) {
        final Calendar cal = DateUtil.getCurrentCal();
        if (!StringUtil.isEmpty(req.getParameter("showmonth"))) {
            cal.set(Calendar.YEAR, Integer.parseInt(req.getParameter("showmonth").substring(0, 4)));
            cal.set(Calendar.MONTH, Integer.parseInt(req.getParameter("showmonth").substring(4, 6)) - 1);
        }
        return cal;
    }

    private CalendarInfo getCalendarInfo(final Calendar now) {
        final CalendarInfo calendar = new CalendarInfo();
        calendar.setCaption(DateUtil.format(now.getTime(), "MMMM yyyy"));

        // month's info
        final Calendar previousMon = DateUtil.getCurrentCal();
        previousMon.setTime(now.getTime());
        previousMon.add(Calendar.MONTH, -1);
        calendar.setPreviousMon(DateUtil.format(previousMon.getTime(), "MMM"));
        final Calendar nextMon = DateUtil.getCurrentCal();
        nextMon.setTime(now.getTime());
        nextMon.add(Calendar.MONTH, 1);
        calendar.setNextMon(DateUtil.format(nextMon.getTime(), "MMM"));
        calendar.setCurrentTime(now);

        // date's info
        final int firstDate = now.getActualMinimum(Calendar.DATE);// month's first date
        final int lastDate = now.getActualMaximum(Calendar.DATE);// month's last date
        final int todayDate = now.get(Calendar.DATE);
        final int todayDay = now.get(Calendar.DAY_OF_WEEK);
        int firstDay = todayDay - ((todayDate % 7) - 1);// month's first day
        if (firstDay <= 0) {
            firstDay = 7 + firstDay;
        }

        for (int date = firstDate; date <= lastDate; date++) {
            final DateInfo info = new DateInfo();
            info.setDate(date);
            int day = (firstDay + date - 1) % 7;
            if (day == 0) {
                day = 7;
            }
            info.setDay(day);
            info.setToday(date == todayDate);

            final Calendar dateTemp = DateUtil.getCurrentCal();
            dateTemp.setTime(now.getTime());
            dateTemp.set(Calendar.DATE, date);
            info.setDateTime(dateTemp.getTime());

            calendar.getDates().add(info);
        }

        return calendar;
    }

    private String getCalendarHTML(final CalendarInfo calendar) {
        final StringBuilder sb = new StringBuilder();

        sb.append("<h2>Calendar</h2>");
        sb.append("<div id=\"calendar_wrap\">");
        sb.append("<table summary=\"Calendar\">");
        sb.append("<caption>");
        sb.append(calendar.getCaption());
        sb.append("</caption>");
        sb.append("<thead>");
        sb.append("<tr>");
        sb.append("<th abbr=\"Sunday\" scope=\"col\" title=\"Sunday\">S</th>");
        sb.append("<th abbr=\"Monday\" scope=\"col\" title=\"Monday\">M</th>");
        sb.append("<th abbr=\"Tuesday\" scope=\"col\" title=\"Tuesday\">T</th>");
        sb.append("<th abbr=\"Wednesday\" scope=\"col\" title=\"Wednesday\">W</th>");
        sb.append("<th abbr=\"Thursday\" scope=\"col\" title=\"Thursday\">T</th>");
        sb.append("<th abbr=\"Friday\" scope=\"col\" title=\"Friday\">F</th>");
        sb.append("<th abbr=\"Saturday\" scope=\"col\" title=\"Saturday\">S</th>");
        sb.append("</tr>");
        sb.append("</thead>");
        sb.append("<tfoot>");
        sb.append("<tr>");
        sb.append("<td colspan=\"3\" id=\"prev\"><a href='###' onclick=\"");
        sb.append("calendar('/calendar?showmonth=");// previous month url

        final Calendar previousMon = calendar.getCurrentTime();
        previousMon.add(Calendar.MONTH, -1);
        sb.append(DateUtil.format(previousMon.getTime(), "yyyyMM"));

        sb.append("')");
        sb.append("\" title=\"\">&laquo; ");
        sb.append(calendar.getPreviousMon());
        sb.append("</a></td>");
        sb.append("<td class=\"pad\" title=\"Current Month\"><a href='###' onclick=\"");
        sb.append("calendar('/calendar?usecache=y&showmonth=");// current month url

        final Calendar currentMon = DateUtil.getCurrentCal();
        sb.append(DateUtil.format(currentMon.getTime(), "yyyyMM"));
        sb.append("')\"><img src='/images/now.png'/></a></td>");

        sb.append("<td colspan=\"3\" id=\"next\"><a href='###' onclick=\"");
        sb.append("calendar('/calendar?showmonth=");// next month url

        final Calendar nextMon = calendar.getCurrentTime();
        nextMon.add(Calendar.MONTH, 1);
        sb.append(DateUtil.format(nextMon.getTime(), "yyyyMM"));

        sb.append("')");
        sb.append("\" title=\"\">");
        sb.append(calendar.getNextMon());
        sb.append(" &raquo;</a></td>");
        sb.append("</tr>");
        sb.append("</tfoot>");
        sb.append("<tbody>");

        final List<DateInfo> dates = calendar.getDates();
        for (int i = 0; i < dates.size(); i++) {

            final DateInfo info = dates.get(i);

            if (info.getDay() == Calendar.SUNDAY) {
                sb.append("<tr>");
            }

            if (i == 0 && info.getDay() - 1 != 0) {
                sb.append("<td colspan=\"");
                sb.append(info.getDay() - 1);
                sb.append("\" class=\"pad\">&nbsp;</td>");
            }

            sb.append("<td");
            if (info.isToday()) {
                sb.append(" id=\"today\">");
            } else {
                sb.append(">");
            }
            if (articleService.contains(DateUtil.getZeroClock(info.getDateTime()),//
                                        DateUtil.getTwentyFourClock(info.getDateTime()))) {
                sb.append("<a href=\"###\" onclick=changeContent('/blogc/");
                sb.append(DateUtil.format(info.getDateTime(), "yyyyMMdd"));
                sb.append("') >");
                sb.append(info.getDate());
                sb.append("</a>");
            } else {
                sb.append(info.getDate());
            }

            sb.append("</td>");

            if (i == dates.size() - 1 && info.getDay() != 7) {
                sb.append("<td colspan=\"");
                sb.append(7 - info.getDay());
                sb.append("\" class=\"pad\">&nbsp;</td>");
            }

            if (info.getDay() == Calendar.SATURDAY || i == dates.size() - 1) {
                sb.append("</tr>");
            }
        }

        sb.append("</tbody>");
        sb.append("</table>");
        sb.append("</div>");

        return sb.toString();
    }
}
