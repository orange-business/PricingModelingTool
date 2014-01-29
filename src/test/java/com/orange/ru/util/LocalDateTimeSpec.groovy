package com.orange.ru.util

import spock.lang.Specification
import org.joda.time.LocalDateTime
import java.util.regex.Pattern
import java.util.regex.Matcher

class LocalDateTimeSpec extends Specification {
  def "Какое время в данный момент?"() {
    LocalDateTime dateTime = new LocalDateTime()
    println dateTime

    String strDate = dateTime.toString("yyyy.MM.dd HH:mm")
    println "input  -> " +  strDate

    String input = strDate
    Pattern pattern = Pattern.compile("(\\d\\d\\d\\d)\\.(\\d\\d)\\.(\\d\\d) (\\d\\d)\\:(\\d\\d)");
    Matcher matcher = pattern.matcher(input);

    int year = 0, monthOfYear = 0, dayOfMonth = 0, hourOfDay = 0, minuteOfHour = 0;
    if (matcher.find()){
      year = Integer.parseInt(matcher.group(1));
      monthOfYear = Integer.parseInt(matcher.group(2));
      dayOfMonth = Integer.parseInt(matcher.group(3));
      hourOfDay = Integer.parseInt(matcher.group(4));
      minuteOfHour = Integer.parseInt(matcher.group(5));
    }
    println "output -> " + new LocalDateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour);

    expect: true
  }
}